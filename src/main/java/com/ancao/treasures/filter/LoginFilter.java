package com.ancao.treasures.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ancao.treasures.util.StringUtil;

@Component
@WebFilter(urlPatterns = "/*", filterName = "loginFilter")
public class LoginFilter implements Filter {

	private Logger logger = LogManager.getLogger(LoginFilter.class);
	/**
	 * 封装，不需要过滤的list列表
	 */
	protected static List<Pattern> patterns = new ArrayList<Pattern>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		logger.info(url);

		Enumeration enumeration = servletRequest.getParameterNames();
		String paraName = null;
		String paraValue = null;
		while (enumeration.hasMoreElements()) {
			paraName = (String) enumeration.nextElement();
			paraValue = servletRequest.getParameter(paraName);
			logger.info(paraName + ": " + paraValue);
		}

		if (url.startsWith("/") && url.length() > 1) {
			url = url.substring(1);
		}

		if (isInclude(url)) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		} else if (url.contains("video/userlogon") || url.contains("pay/alipayNotify")) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		} else {
			String verify = httpRequest.getParameter("verify");
			String userId = httpRequest.getParameter("userId");

			if (!StringUtil.isEmpty(verify)) {
				logger.info("decodeVerify:" + decodeVerify(verify));
			}
			if (!StringUtil.isEmpty(verify) && !StringUtil.isEmpty(userId) && Integer.valueOf(userId) == decodeVerify(verify)) {
				// session存在
				chain.doFilter(httpRequest, httpResponse);
				return;
			} else {
				// session不存在 准备跳转失败
				/*
				 * RequestDispatcher dispatcher =
				 * request.getRequestDispatcher(path);
				 * dispatcher.forward(request, response);
				 */
				// chain.doFilter(httpRequest, httpResponse);

				JSONObject json = new JSONObject();
				json.put("message", "ERR");
				json.put("messageContent", "接口调用参数异常");
				httpResponse.setContentType("text/json;charset=utf-8");
				httpResponse.setCharacterEncoding("UTF-8");
				PrintWriter out = httpResponse.getWriter();
				out.print(json);
				out.flush();
				out.close();
				return;
			}
		}

	}

//	public static void main(String[] args) {
//		int decodeVerify = decodeVerify("5769975779");
//		System.out.println(decodeVerify);
//	}
	/**
	 * 
	 * @Description: (解密verify)
	 * @author sunhao
	 * @date 2018年1月25日 下午4:40:54
	 * @param verify
	 * @return
	 */
	private static int decodeVerify(String verify) {
		String b = "";
		while (verify.length() > 0) {
			b = b + verify.substring(verify.length() - 1);
			verify = verify.substring(0, verify.length() - 1);
		}
//		System.out.println(b);
		String c = b.substring(0, 1) + b.substring(2, b.length() - 2)+ b.substring(b.length() - 1, b.length());
//		System.out.println(c);
//		System.out.println(Integer.valueOf(c) - 97568967);
		return (Integer.valueOf(c) - 97568967) / 9;
	}

	@Override
	public void destroy() {

	}

	/**
	 * 是否需要过滤
	 * 
	 * @param url
	 * @return
	 */
	private boolean isInclude(String url) {
		for (Pattern pattern : patterns) {
			Matcher matcher = pattern.matcher(url);
			if (matcher.matches()) {
				return true;
			}
		}
		return false;
	}

}
