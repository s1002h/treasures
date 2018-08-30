package com.ancao.treasures.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ancao.treasures.bean.TreasuresCodename;
import com.ancao.treasures.bean.TreasuresOrder;
import com.ancao.treasures.bean.TreasuresOrderExample;
import com.ancao.treasures.bean.TreasuresUser;
import com.ancao.treasures.bean.TreasuresUserExample;
import com.ancao.treasures.bean.TreasuresVideo;
import com.ancao.treasures.bean.TreasuresVideoExample;
import com.ancao.treasures.bean.ex.ImageEx;
import com.ancao.treasures.bean.ex.TitleEx;
import com.ancao.treasures.bean.ex.VideoDataEx;
import com.ancao.treasures.common.BaseController;
import com.ancao.treasures.constant.APIConstant;
import com.ancao.treasures.constant.APIErrorMessage;
import com.ancao.treasures.util.DateUtils;
import com.ancao.treasures.util.StringUtil;

@RestController
@RequestMapping("video")
public class VideoAction extends BaseController {

	@Value("${UPLOAD_FILE_PATH}")
	private String UPLOAD_FILE_PATH;

	/**
	 * 
	 * @Description: (用户注册)
	 * @author sunhao
	 * @throws Exception
	 * @date 2018年1月25日 下午2:27:03
	 */
	@RequestMapping("/userlogon")
	public void userlogon() throws Exception {
		TreasuresUser addUser = JSON.parseObject(getParameter("user"), TreasuresUser.class);
		TreasuresUserExample userExample = new TreasuresUserExample();
		userExample.createCriteria().andTokenEqualTo(addUser.getToken());

		List<TreasuresUser> users = treasuresUserMapper.selectByExample(userExample);
		if (users.size() == 0) {
			Date date = new Date();
			addUser.setCreatetime(date);
			addUser.setUpdtime(date);
			treasuresUserMapper.insert(addUser);

			// 获取id
			userExample.createCriteria().andTokenEqualTo(addUser.getToken());
			users = treasuresUserMapper.selectByExample(userExample);
		}
		JSONObject jsonObject = getResultOKJSONObject();
		jsonObject.put("userId", users.get(0).getId());
		writeOkToResponse(jsonObject);
	}

	/**
	 * 获取首页数据
	 * 
	 * @author sh
	 * @version V1.0
	 * @throws Exception
	 */
	@RequestMapping("/getHomePageDatas")
	public void getHomePageDatas() throws Exception {
		String projectType = getParameter("projectType");
		if (StringUtil.isEmpty(projectType)) {
			projectType = APIConstant.STRING[1];
		}

		// 查询首页图片
		List<TreasuresCodename> codeNames = getTreasuresCodeNameByType("homePageImg", projectType);
		ArrayList<ImageEx> images = new ArrayList<ImageEx>();
		for (TreasuresCodename codeName : codeNames) {
			ImageEx imageEx = new ImageEx(codeName.getId(), codeName.getFiled1(), codeName.getContent(), codeName.getDescription(), codeName.getCode());
			images.add(imageEx);
		}
		JSONObject jsonObject = getResultOKJSONObject();
		jsonObject.put("images", images);
		// 查询title
		codeNames = getTreasuresCodeNameByType("titleType", projectType);
		ArrayList<TitleEx> titles = new ArrayList<TitleEx>();
		for (TreasuresCodename codeName : codeNames) {
			TitleEx titleEx = new TitleEx(codeName.getCode(), codeName.getContent());
			titles.add(titleEx);
		}
		jsonObject.put("titles", titles);

		// 查询视频数据
		TreasuresVideoExample videoExample = new TreasuresVideoExample();
		videoExample.createCriteria().andTypeEqualTo(APIConstant.TYPE_1).andProjecttypeEqualTo(projectType);// 精选类型
		List<TreasuresVideo> dataList = treasuresVideoMapper.selectByExample(videoExample);

		// 查询用户购买记录
		TreasuresOrderExample orderExample = new TreasuresOrderExample();
		orderExample.createCriteria().andUseridEqualTo(Integer.valueOf(getParameter("userId"))).andPayStatusEqualTo("2")
				.andCreatetimeGreaterThanOrEqualTo(DateUtils.addDays(new Date(), -2));
		List<TreasuresOrder> orders = treasuresOrderMapper.selectByExample(orderExample);

		ArrayList<VideoDataEx> videos = new ArrayList<VideoDataEx>();
		for (TreasuresVideo video : dataList) {
			VideoDataEx videoDataEx = JSON.parseObject(JSON.toJSONString(video), VideoDataEx.class);
			videoDataEx.setIsPurchased("0");
			for (TreasuresOrder treasuresOrder : orders) {
				if (videoDataEx.getId().equals(treasuresOrder.getVideoid())) {
					videoDataEx.setIsPurchased("1");
					videoDataEx.setExpirationTime(DateUtils.addDays(treasuresOrder.getCreatetime(), +2));
				}
			}
			videoDataEx.setCreatetime(null);
			videoDataEx.setWeburl(null);
			videoDataEx.setUpdtime(null);
			videoDataEx.setVideoflag(null);
			videoDataEx.setJointurl(null);
			videos.add(videoDataEx);
		}
		jsonObject.put("data", videos);
		writeOkToResponse(jsonObject);
	}

	/**
	 * 查询接口
	 * 
	 * @author sh
	 * @version V1.0
	 * @throws Exception
	 */
	@RequestMapping("/findByType")
	public void findByType() throws Exception {
		String type = getParameter("type");
		if (StringUtil.isEmpty(type)) {
			writeERRToResponse(APIErrorMessage.ERROR_00001);
			return;
		}

		String projectType = getParameter("projectType");
		if (StringUtil.isEmpty(projectType)) {
			projectType = APIConstant.STRING[1];
		}

		// 查询视频数据
		TreasuresVideoExample videoExample = new TreasuresVideoExample();
		videoExample.createCriteria().andTypeEqualTo(type).andProjecttypeEqualTo(projectType);// 精选类型
		List<TreasuresVideo> dataList = treasuresVideoMapper.selectByExample(videoExample);
		ArrayList<VideoDataEx> videos = new ArrayList<VideoDataEx>();

		// 查询用户购买记录
		TreasuresOrderExample orderExample = new TreasuresOrderExample();
		orderExample.createCriteria().andUseridEqualTo(Integer.valueOf(getParameter("userId"))).andPayStatusEqualTo("2")
				.andCreatetimeGreaterThanOrEqualTo(DateUtils.addDays(new Date(), -2));
		List<TreasuresOrder> orders = treasuresOrderMapper.selectByExample(orderExample);

		for (TreasuresVideo video : dataList) {
			VideoDataEx videoDataEx = JSON.parseObject(JSON.toJSONString(video), VideoDataEx.class);
			videoDataEx.setIsPurchased("0");
			for (TreasuresOrder treasuresOrder : orders) {
				if (videoDataEx.getId().equals(treasuresOrder.getVideoid())) {
					videoDataEx.setIsPurchased("1");
					videoDataEx.setExpirationTime(DateUtils.addDays(treasuresOrder.getCreatetime(), +2));
				}
			}
			videoDataEx.setCreatetime(null);
			videoDataEx.setWeburl(null);
			videoDataEx.setUpdtime(null);
			videoDataEx.setVideoflag(null);
			videoDataEx.setJointurl(null);
			videos.add(videoDataEx);
		}
		JSONObject jsonObject = getResultOKJSONObject();
		jsonObject.put("data", videos);
		writeOkToResponse(jsonObject);
	}

	/**
	 * 获取VIP链接接口
	 * 
	 * @author sh
	 * @version V1.0
	 * @throws Exception
	 */
	@RequestMapping("/getVipUrl")
	public void getVipUrl() throws Exception {
		String videoId = getParameter("videoId");
		// 查询是否购买
		TreasuresOrderExample example = new TreasuresOrderExample();
		example.createCriteria().andVideoidEqualTo(Integer.valueOf(videoId)).andUseridEqualTo(Integer.valueOf(getParameter("userId"))).andPayStatusEqualTo("2");
		List<TreasuresOrder> orders = treasuresOrderMapper.selectByExample(example);
		if (orders.size() > 0) {
			TreasuresVideo video = treasuresVideoMapper.selectByPrimaryKey(Integer.valueOf(videoId));
			JSONObject jsonObject = getResultOKJSONObject();
			// TODO 处理url
			jsonObject.put("videoUrl", video.getJointurl()+video.getVideourl());
			writeOkToResponse(jsonObject);
		} else {
			writeERRToResponse("未购买！");
		}
	}

	/**
	 * 获取分享VIP链接接口
	 * 
	 * @author sh
	 * @version V1.0
	 * @throws Exception
	 */
	@RequestMapping("/getShareVipUrl")
	public void getShareVipUrl() throws Exception {
		String videoId = getParameter("videoId");
		// 查询是否购买
		TreasuresOrderExample example = new TreasuresOrderExample();
		example.createCriteria().andVideoidEqualTo(Integer.valueOf(videoId)).andUseridEqualTo(Integer.valueOf(getParameter("shareUserId")))
				.andPayStatusEqualTo("2");
		List<TreasuresOrder> orders = treasuresOrderMapper.selectByExample(example);
		if (orders.size() > 0) {
			TreasuresVideo video = treasuresVideoMapper.selectByPrimaryKey(Integer.valueOf(videoId));
			JSONObject jsonObject = getResultOKJSONObject();
			// TODO 处理url
			jsonObject.put("videoUrl", video.getVideourl());
			writeOkToResponse(jsonObject);
		} else {
			writeERRToResponse("未购买！");
		}
	}

	/**
	 * 编辑上传
	 * 
	 * @author sh
	 * @version V1.0
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public void update(@RequestParam("file") MultipartFile file) throws Exception {
		String prmTreasuresVideo = getParameter("video");
		TreasuresVideo updateTreasuresVideo = JSON.parseObject(prmTreasuresVideo, TreasuresVideo.class);
		Date date = new Date();
		try {
			if (null != file) {
				// 保存图片
				String path = UPLOAD_FILE_PATH + DateUtils.dateToString(date, DateUtils.patternO) + File.separator;
				String fileName = StringUtil.makeRandom(15) + ".jpg";
				uploadFile(file.getBytes(), path, fileName);
				// 保存路径
				updateTreasuresVideo.setImgurl(path + fileName);
			} else {
				// 图片上传失败
				writeERRToResponse(APIErrorMessage.ERROR_00002);
				return;
			}
		} catch (Exception e) {
			writeERRToResponse(APIErrorMessage.ERROR_00001);
		}
		updateTreasuresVideo.setUpdtime(date);
		int count = treasuresVideoMapper.updateByPrimaryKeySelective(updateTreasuresVideo);
		if (count == 0) {
			writeERRToResponse(APIErrorMessage.ERROR_00001);
		} else {
			writeOkToResponse();
		}

	}

	/**
	 * 编辑授权接口
	 * 
	 * @author sh
	 * @version V1.0
	 * @throws Exception
	 */
	@RequestMapping("/editAuthorize")
	public void editAuthorize() throws Exception {
		String authorizeKey = getParameter("authorizeKey");
		String verificationKey = getParameter("verificationKey");
		JSONObject jsonObject = getResultOKJSONObject();
		if (authorizeKey.equals("99999999")) {
			// 返回结果
			String[] resultEditType = new String[] { "99" };
			jsonObject.put("editType", resultEditType);
			writeOkToResponse(jsonObject);
			return;
		}

		Integer ahKey = Integer.valueOf(authorizeKey);
		ahKey = ahKey + 31032317;
		System.out.println(ahKey);
		// 加密key
		String encryptionKey = String.valueOf(ahKey);
		// 倒序key
		StringBuffer invertedKey = new StringBuffer();
		// 循环遍历此字符串
		for (int i = 0; i < encryptionKey.length(); i++) {
			invertedKey.append(encryptionKey.substring(encryptionKey.length() - i - 1, encryptionKey.length() - i));
		}
		System.out.println(invertedKey.toString());
		if (!verificationKey.equals(String.valueOf(invertedKey))) {
			writeERRToResponse("授权key与验证key不匹配");
			return;
		}
		System.out.println(invertedKey);
		List<Integer> editTypes = new ArrayList<Integer>();
		for (int i = 0; i < authorizeKey.length(); i++) {
			if (!editTypes.contains(Integer.valueOf(authorizeKey.substring(i, i + 2)))) {
				editTypes.add(Integer.valueOf(authorizeKey.substring(i, i + 2)));
			}
			i++;
		}
		// 返回结果
		String[] resultEditType = new String[editTypes.size()];
		for (int i = 0; i < editTypes.size(); i++) {
			resultEditType[i] = String.valueOf(editTypes.get(i));
		}
		jsonObject.put("editType", resultEditType);
		writeOkToResponse(jsonObject);
	}

	/**
	 * 首页图片编辑
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/updateHomePageImg")
	public void updateHomePageImg(@RequestParam("file") MultipartFile file) throws Exception {
		// public void updateHomePageImg() throws Exception {
		String prmImage = getParameter("imageJson");
		ImageEx imageEx = JSON.parseObject(prmImage, ImageEx.class);
		TreasuresCodename updateTreasuresCodeName = new TreasuresCodename();
		updateTreasuresCodeName.setId(imageEx.getId());
		updateTreasuresCodeName.setDescription(imageEx.getDescription()); // 描述
		updateTreasuresCodeName.setFiled1(imageEx.getOpenUrl()); // 跳转链接
		Date date = new Date();
		if (null != file) {
			// 保存图片
			String path = UPLOAD_FILE_PATH + DateUtils.dateToString(date, DateUtils.patternO) + File.separator;
			String fileName = StringUtil.makeRandom(15) + ".jpg";
			uploadFile(file.getBytes(), path, fileName);
			// 保存路径
			updateTreasuresCodeName.setContent(path + fileName);// 图片路径
		}
		int i = treasuresCodenameMapper.updateByPrimaryKeySelective(updateTreasuresCodeName);
		if (i == 1) {
			writeOkToResponse();
		} else {
			writeERRToResponse(APIErrorMessage.ERROR_00001);
		}
	}

	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		out.write(file);
		out.flush();
		out.close();
	}

	/**
	 * 
	 * @Description: (加载图片)
	 * @author sunhao
	 * @date 2018年1月25日 下午4:16:47
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/initImage")
	public void initImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String imageUrl = request.getParameter("imageUrl");
		File file = new File(imageUrl);
		if (!file.exists()) {
			writeERRToResponse("图片不存在！");
			return;
		}
		InputStream inStream = new FileInputStream(file);
		// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
		byte[] data = readInputStream(inStream);
		// 写入数据
		OutputStream os = response.getOutputStream();
		os.write(data);
		// 关闭输出流
		os.flush();
		os.close();
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}
}
