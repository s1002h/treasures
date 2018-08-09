package com.ancao.treasures.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.ancao.treasures.bean.TreasuresOrder;
import com.ancao.treasures.bean.TreasuresOrderExample;
import com.ancao.treasures.bean.TreasuresVideo;
import com.ancao.treasures.common.BaseController;
import com.ancao.treasures.util.StringUtil;

@RestController
@RequestMapping("pay")
public class PayAction extends BaseController {

	private static final Logger logger = LogManager.getLogger(PayAction.class);

	private String CHARSET = "UTF-8";

	@Value("${Alipay.APP_ID}")
	private String APP_ID;

	@Value("${Alipay.SERVER_URL}")
	private String SERVER_URL;

	@Value("${Alipay.APP_PRIVATE_KEY}")
	private String APP_PRIVATE_KEY;

	@Value("${Alipay.ALIPAY_PUBLIC_KEY}")
	private String ALIPAY_PUBLIC_KEY;

	public File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * 购买接口
	 * 
	 * @author sh
	 * @version V1.0
	 * @throws Exception
	 */
	@RequestMapping("/buy")
	public void buy() throws Exception {
		// TODO
		String userId = getParameter("userId");
		String videoId = getParameter("videoId");

		// 查询视频是否存在
		TreasuresVideo video = treasuresVideoMapper.selectByPrimaryKey(Integer.valueOf(videoId));
		if (null == video) {
			writeERRToResponse("视频不存在！");
			return;
		}
		// 实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("我是测试数据");
		model.setSubject("App支付测试Java");

		String outtradeno = System.currentTimeMillis() + (System.nanoTime() + "").substring(7, 10);
		model.setOutTradeNo(outtradeno);
		model.setTimeoutExpress("30m");
		model.setTotalAmount("0.01");
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl("http://vipbanlv.com:8081/pay/alipayNotify.action");
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			System.out.println(response.getBody());// 就是orderString
													// 可以直接给客户端请求，无需再做处理。

			// 生成订单信息
			TreasuresOrder order = new TreasuresOrder();
			order.setOutTradeNo(outtradeno);
			order.setUserid(Integer.valueOf(userId));
			order.setVideoid(Integer.valueOf(videoId));
			order.setPrice(video.getPrice());
			Date date = new Date();
			order.setCreatetime(date);
			order.setUpdtime(date);
			treasuresOrderMapper.insertSelective(order);

			JSONObject jsonObject = getResultOKJSONObject();
			jsonObject.put("orderInfo", response.getBody());
			writeOkToResponse(jsonObject);
			return;
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		writeERRToResponse("生成订单失败！");
	}

	/**
	 * 购买成功确定接口
	 * 
	 * @author sh
	 * @version V1.0
	 * @throws Exception
	 */
	@RequestMapping("/buysucceed")
	public void buysucceed() throws Exception {
		String out_trade_no = getParameter("out_trade_no");
		String trade_no = getParameter("trade_no");

		AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2"); // 获得初始化的AlipayClient
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();// 创建API对应的request类
		// request.setBizContent("{" +
		// "   \"out_trade_no\":\"20150320010101001\"," +
		// "   \"trade_no\":\"2014112611001004680073956707\"" + "  }");// 设置业务参数

		JSONObject bizContent = new JSONObject();
		if (StringUtil.isNotEmpty(out_trade_no)) {
			bizContent.put("out_trade_no", out_trade_no);
		}
		if (StringUtil.isNotEmpty(trade_no)) {
			bizContent.put("trade_no", trade_no);
		}
		request.setBizContent(bizContent.toJSONString());// 设置业务参数
		AlipayTradeQueryResponse response = alipayClient.execute(request);// 通过alipayClient调用API，获得对应的response类
		logger.info("购买确认结果" + response.getBody());

		JSONObject jsonObject = new JSONObject();

		try {
			JSONObject bodyJsonObject = JSON.parseObject(response.getBody());
			JSONObject tradeJsonObject = JSON.parseObject(bodyJsonObject.getString("alipay_trade_query_response"));
			if ("TRADE_FINISHED".equals(tradeJsonObject.getString("trade_status")) || "TRADE_SUCCESS".equals(tradeJsonObject.getString("trade_status"))) {
				jsonObject.put("info", "订单支付成功！");
				TreasuresOrderExample example = new TreasuresOrderExample();
				example.createCriteria().andOutTradeNoEqualTo(tradeJsonObject.getString("out_trade_no"));
				List<TreasuresOrder> treasuresOrders = treasuresOrderMapper.selectByExample(example);
				if (treasuresOrders.size() > 0 && !"2".equals(treasuresOrders.get(0).getPayStatus())) {
					// 更新订单
					TreasuresOrder updateOrder = new TreasuresOrder();
					updateOrder.setId(treasuresOrders.get(0).getId());
					updateOrder.setTradeNo(tradeJsonObject.getString("trade_no"));
					updateOrder.setPayStatus("2");
					updateOrder.setUpdtime(new Date());
					treasuresOrderMapper.updateByPrimaryKeySelective(updateOrder);
				}
			} else {
				jsonObject.put("info", "订单支付异常，稍后查看订单是否成功购买或联系客服！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			jsonObject.put("info", "订单支付异常，稍后查看订单是否成功购买或联系客服！");
		}
		// 根据response中的结果继续业务逻辑处理
		writeToOkResponse(jsonObject);
	}

	/**
	 * 支付通知接口
	 * 
	 * @author sh
	 * @version V1.0
	 * @throws Exception
	 */
	@RequestMapping("/alipayNotify")
	public void alipayNotify() {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		// 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		// boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String
		// publicKey, String charset, String sign_type)
		try {
			boolean flag = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, "RSA2");
			logger.info("签名验证" + flag);

			if (flag) {
				if ("TRADE_FINISHED".equals(params.get("trade_status")) || "TRADE_SUCCESS".equals(params.get("trade_status"))) {
					logger.info("购买成功更新订单！");
					TreasuresOrderExample example = new TreasuresOrderExample();
					example.createCriteria().andOutTradeNoEqualTo(params.get("out_trade_no"));
					List<TreasuresOrder> treasuresOrders = treasuresOrderMapper.selectByExample(example);
					if (treasuresOrders.size() > 0 && !"2".equals(treasuresOrders.get(0).getPayStatus())) {
						// 更新订单
						TreasuresOrder updateOrder = new TreasuresOrder();
						updateOrder.setId(treasuresOrders.get(0).getId());
						updateOrder.setTradeNo(params.get("trade_no"));
						updateOrder.setPayStatus("2");
						updateOrder.setUpdtime(new Date());
						treasuresOrderMapper.updateByPrimaryKeySelective(updateOrder);
					} else if (treasuresOrders.size() == 0) {
						logger.info("订单不存在！");
					}
				}
			}
			// 返回数据
			PrintWriter pw;
			try {
				pw = response.getWriter();
				pw.write("success");
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
