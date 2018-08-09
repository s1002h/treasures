package com.ancao.treasures.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.ancao.treasures.bean.TreasuresCodename;
import com.ancao.treasures.bean.TreasuresCodenameExample;
import com.ancao.treasures.bean.UserBean;
import com.ancao.treasures.enums.CodeEnum;
import com.ancao.treasures.mapper.TreasuresCodenameMapper;
import com.ancao.treasures.mapper.TreasuresHotlinkMapper;
import com.ancao.treasures.mapper.TreasuresOrderMapper;
import com.ancao.treasures.mapper.TreasuresUserMapper;
import com.ancao.treasures.mapper.TreasuresVideoMapper;
import com.ancao.treasures.service.UserService;
import com.ancao.treasures.util.StringUtil;

/**
 * 
 * 封装action
 * 
 * @author hongxuewen
 * @version
 */
public abstract class BaseController {

	private static final Logger log = LogManager.getLogger(BaseController.class);

	@Resource
	protected UserService userService;

	@Autowired
	protected TreasuresCodenameMapper treasuresCodenameMapper;
	@Autowired
	protected TreasuresHotlinkMapper treasuresHotlinkMapper;
	@Autowired
	protected TreasuresVideoMapper treasuresVideoMapper;
	@Autowired
	protected TreasuresUserMapper treasuresUserMapper;
	@Autowired
	protected TreasuresOrderMapper treasuresOrderMapper;
	//
	// @Autowired
	// protected VideoDAOEx videoDAO;

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	/**
	 * 查询条件Map<br>
	 * 改参数需要手动调用方法设值
	 */
	protected Map<String, Object> queryMap;

	/**
	 * 当前页（分页请求使用）
	 */
	protected int currPage = 1;

	/**
	 * 页面数据大小（分页请求使用）
	 */
	protected int pageSize = 10;

	protected String getParameter(String key) {
		String param = request.getParameter(key);
		if (param == null) {
			return null;
		} else if (param.equals("null")) {
			return null;
		}
		return param;
	}

	public JSONObject getResultOKJSONObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "OK");
		return jsonObject;

	}

	/**
	 * 取得TreasuresCodeName表的Name值
	 * 
	 * @param type
	 * @param code
	 * @return name
	 */
	public String getTreasuresCodeNameByTypeCode(String type, String code) {

		TreasuresCodenameExample example = new TreasuresCodenameExample();
		example.createCriteria().andTypeEqualTo(type).andCodeEqualTo(code);
		List<TreasuresCodename> list = treasuresCodenameMapper.selectByExample(example);

		if (list.size() > 0) {
			return list.get(0).getContent();
		} else {
			return null;
		}
	}

	/**
	 * 取得TreasuresCodeName值
	 * 
	 * @param type
	 * @param code
	 * @return name
	 */
	public List<TreasuresCodename> getTreasuresCodeNameByType(String type) {

		TreasuresCodenameExample example = new TreasuresCodenameExample();
		example.createCriteria().andTypeEqualTo(type);
		List<TreasuresCodename> list = treasuresCodenameMapper.selectByExample(example);
		return list;
	}

	/**
	 * 将{'message':'OK'}以UTF-8编码发送到客户端
	 * 
	 * @throws IOException
	 *             网络链接异常
	 */
	public void writeOkToResponse(Object obj) throws Exception {
		writeToResponse(obj);
	}

	/**
	 * 取得页码
	 * 
	 * @return 页码
	 */
	public int getPageNum() throws Exception {

		if (StringUtil.isEmpty(getParameter("pageNum")) || !isIntegerValue(getParameter("pageNum"))) {
			return currPage;
		}

		return Integer.parseInt(getParameter("pageNum"));
	}

	/**
	 * 判断是否属于Integer类型
	 * 
	 * @param value
	 *            字符串类型的值
	 * @return true:Integer型 false:非Integer型
	 */
	public boolean isIntegerValue(String value) {

		try {
			int ret = Integer.parseInt(value);
		} catch (Exception ex) {
			return false;
		}

		return true;
	}

	/**
	 * 作成MySQL取分页数据用SQL
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            每页件数
	 * @return 取分页数据用SQL(放在ORDER BY后面)
	 */
	public String makeLimitSQL(int pageNum, int pageSize) {
		return " LIMIT " + String.valueOf((pageNum - 1) * pageSize) + "," + pageSize;
	}

	/**
	 * 取得TreasuresCodeName值
	 * 
	 * @param type
	 * @param code
	 * @return name
	 */
	public List<TreasuresCodename> getTreasuresCodeNameByType(String type, String projectType) {

		TreasuresCodenameExample example = new TreasuresCodenameExample();
		example.createCriteria().andTypeEqualTo(type).andProjecttypeEqualTo(projectType);
		List<TreasuresCodename> list = treasuresCodenameMapper.selectByExample(example);
		return list;
	}

	/**
	 * 将{'message':'ERR'}以UTF-8编码发送到客户端 接口异常
	 * 
	 * @throws IOException
	 *             网络链接异常
	 * @param msg
	 */
	public void writeERRToResponse(String msg) throws Exception {
		JSONObject json = new JSONObject();
		json.put("message", "ERR");
		json.put("messageContent", msg);
		writeToResponse(json);
	}

	/**
	 * 将json字符串以UTF-8编码发送到客户端
	 * 
	 * @param jsonBytes
	 *            发送给客户端的json字符串
	 * @throws IOException
	 *             网络链接异常
	 */
	private void writeToResponse(Object object) throws IOException {

		HttpServletResponse response = getResponse();
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(object);
		out.flush();
		out.close();
	}

	/**
	 * 将{'message':'OK'}以UTF-8编码发送到客户端
	 * 
	 * @throws IOException
	 *             网络链接异常
	 */
	public void writeOkToResponse() throws Exception {
		JSONObject json = new JSONObject();
		json.put("message", "OK");
		writeToResponse(json);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 获取查询条件Map对象 <br>
	 * 查询条件不存在数组，所以需要将请求中的数据进行转换
	 * 
	 * @return
	 * @since <IVersion>
	 */
	protected Map<String, Object> getQueryParamMap(HttpServletRequest request) {
		Enumeration<String> e = request.getParameterNames();
		if (queryMap == null || "undefined".equals(queryMap.get("CURRPAGE")) || queryMap.get("CURRPAGE") == null
				|| (null != queryMap.get("CURRPAGE") && !"".equals(queryMap.get("CURRPAGE")) && Integer.valueOf(queryMap.get("CURRPAGE").toString()) <= 1)) {
			queryMap = new HashMap<String, Object>();
		}
		queryMap.put("CURRPAGE", 1);// 若接受的URL请求参数中不包含 CURRPAGE
									// 参数，则进行的操作请求肯定不是翻页请求，故将当前页设为第一页。
		while (e.hasMoreElements()) {
			String key = e.nextElement().toString();
			String value = request.getParameter(key);
			if (StringUtil.isNotEmpty(value)) {
				queryMap.put(key.toUpperCase(), value);
			}
		}

		// 开始时间
		String beginDate = null;
		if (queryMap.get("BEGINDATE") != null && queryMap.get("BEGINDATE") != "") {
			beginDate = queryMap.get("BEGINDATE").toString();
			if (beginDate.length() < 11) {
				beginDate = beginDate + " 00:00:00";
				queryMap.put("BEGINDATE", beginDate);
			}
		}
		// 结束时间
		String endDate = null;
		if (queryMap.get("ENDDATE") != null && queryMap.get("ENDDATE") != "") {
			endDate = queryMap.get("ENDDATE").toString();
			if (endDate.length() < 11) {
				endDate = endDate + " 23:59:59";
				queryMap.put("ENDDATE", endDate);
			}
		}

		try {
			this.currPage = Integer.parseInt(queryMap.get("CURRPAGE").toString());// 若接受的URL请求参数中不包含
																					// CURRPAGE
																					// 参数，则进行的操作请求肯定不是翻页请求，故将当前页设为第一页。
		} catch (Exception excep) {
			// excep.printStackTrace();
			this.currPage = 1;
		}
		return queryMap;
	}

	/**
	 * 获取系统后台登录用户ID
	 * 
	 * @return
	 * @since <IVersion>
	 */
	protected String getLoginSysUserId(HttpServletRequest request) {
		UserBean userBean = (UserBean) request.getSession().getAttribute(CodeEnum.SESSION_USER_INFO.getMsg());
		return userBean.getUserId();
	}

	/**
	 * 设置后台登录用户session
	 * 
	 * @param userBean
	 */
	protected void setLoginSysUser(UserBean userBean) {
		request.getSession().setAttribute(CodeEnum.SESSION_USER_INFO.getMsg(), userBean);
	}

	/**
	 * 获取系统后台登录用户
	 * 
	 * @return
	 * @since <IVersion>
	 */
	protected UserBean getLoginSysUser(HttpServletRequest request) {
		UserBean userBean = (UserBean) request.getSession().getAttribute(CodeEnum.SESSION_USER_INFO.getMsg());
		return userBean;
	}

	/**
	 * 
	 * 输出错误信息到页面
	 * 
	 * @param message
	 * @since <IVersion>
	 */
	protected void outMessage(HttpServletResponse response, String message) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			ServletOutputStream out = response.getOutputStream();
			out.println(new String(message.getBytes("UTF-8"), "ISO-8859-1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public Map<String, Object> getQueryMap() {
		return queryMap;
	}

	public void setQueryMap(Map<String, Object> queryMap) {
		this.queryMap = queryMap;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	protected HttpSession getSession() {
		return request.getSession();
	}

	public void writeToOkResponse(JSONObject jsonObject) {
		jsonObject.put("code", CodeEnum.CODE_10000.getMsg());
		writeToResponse(jsonObject, response);
	}

	public void writeToErrorResponse(JSONObject jsonObject) {
		jsonObject.put("code", CodeEnum.CODE_20000.getMsg());
		writeToResponse(jsonObject, response);
	}

	public void writeToParamsErrorResponse(JSONObject jsonObject) {
		jsonObject.put("code", CodeEnum.CODE_30000.getMsg());
		writeToResponse(jsonObject, response);
	}

	/**
	 * 
	 * @Description: (输出json数据)
	 * @author sunhao
	 * @date 2017-8-7 下午1:59:27
	 * @param object
	 * @param response
	 * @throws IOException
	 */
	public void writeToResponse(Object object, HttpServletResponse response) {
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(object);
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @Description: (弹窗消息)
	 * @author sunhao
	 * @date 2017-8-7 下午1:59:49
	 * @param message
	 * @param response
	 * @throws IOException
	 */
	public void alertMessage(String message, HttpServletResponse response) throws IOException {
		String str = "<script type=\"text/javascript\">alert('" + message + "'); </script>";
		PrintWriter out = response.getWriter();
		out.print(str);
		out.flush();
		out.close();
	}
}
