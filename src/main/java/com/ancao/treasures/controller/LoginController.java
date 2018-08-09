package com.ancao.treasures.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ancao.treasures.bean.UserBean;
import com.ancao.treasures.common.BaseController;
import com.ancao.treasures.enums.ErrEnum;
import com.ancao.treasures.enums.KeyEnum;
import com.ancao.treasures.enums.SessionKeyEnum;
import com.ancao.treasures.util.MD5Util;
import com.google.code.kaptcha.impl.DefaultKaptcha;

@RestController
@RequestMapping("loginController")
public class LoginController extends BaseController {
	private static final Logger logger = LogManager.getLogger(BaseController.class);
	@Autowired
	private DefaultKaptcha defaultKaptcha;

	/**
	 * 
	 * @Description: (用户登陆)
	 * @author sunhao
	 * @date 2017年11月27日 上午9:29:15
	 * @param userBean
	 * @return
	 */
	// , method = RequestMethod.POST
	@RequestMapping("/login")
	public void login(UserBean userBean) {

		JSONObject jsonObject = new JSONObject();

		UsernamePasswordToken token = new UsernamePasswordToken(userBean.getLoginId(), MD5Util.getEncryptStr(userBean.getPassWord()), "login");
		Subject currentUser = SecurityUtils.getSubject();
		logger.info("对用户[" + userBean.getLoginId() + "]进行登录验证..验证开始");
		try {
			currentUser.login(token);
			// 验证是否登录成功
			if (currentUser.isAuthenticated()) {
				userBean = (UserBean) currentUser.getPrincipal();

				if (userBean.getEndDate().getTime() < new Date().getTime()) {
					jsonObject.put(ErrEnum.INFO.getMsg(), "用户名或密码不正确！");
					writeToErrorResponse(jsonObject);
					return;
				}
				List<Map<String, Object>> authList = userService.getAuthList(userBean.getUserId());
				for (Map<String, Object> map : authList) {
					if (("100".equals(map.get("AUTHID")))) {
						break;
					} else {
						jsonObject.put(ErrEnum.INFO.getMsg(), "该账号未配置数据查询权限！");
						writeToErrorResponse(jsonObject);
						return;
					}

				}

				logger.info("用户[" + userBean.getLoginId() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
				writeToOkResponse(jsonObject);
				return;
			} else {
				token.clear();
				jsonObject.put(ErrEnum.INFO.getMsg(), "用户[" + userBean.getLoginId() + "]登录认证失败,重新登陆");
				writeToErrorResponse(jsonObject);
				return;
			}
		} catch (UnknownAccountException uae) {
			logger.info("对用户[" + userBean.getLoginId() + "]进行登录验证..验证失败-username wasn't in the system");
		} catch (IncorrectCredentialsException ice) {
			logger.info("对用户[" + userBean.getLoginId() + "]进行登录验证..验证失败-password didn't match");
		} catch (LockedAccountException lae) {
			logger.info("对用户[" + userBean.getLoginId() + "]进行登录验证..验证失败-account is locked in the system");
		} catch (AuthenticationException ae) {
			logger.error(ae.getMessage());
		}

		jsonObject.put(ErrEnum.INFO.getMsg(), "用户名或密码不正确！");
		writeToErrorResponse(jsonObject);
		return;
	}

	/**
	 * 
	 * @Description: (创建验证码)
	 * @author sunhao
	 * @date 2017年11月28日 上午11:37:47
	 * @throws Exception
	 */
	@RequestMapping("/defaultKaptcha")
	public void defaultKaptcha() throws Exception {
		byte[] captchaChallengeAsJpeg = null;
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			// 生产验证码字符串并保存到session中
			String createText = defaultKaptcha.createText();
			request.getSession().setAttribute(SessionKeyEnum.VERIFY_CODE.getMsg(), createText);
			// 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
			BufferedImage challenge = defaultKaptcha.createImage(createText);
			ImageIO.write(challenge, "jpg", jpegOutputStream);
		} catch (IllegalArgumentException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = response.getOutputStream();
		responseOutputStream.write(captchaChallengeAsJpeg);
		responseOutputStream.flush();
		responseOutputStream.close();
	}

	/**
	 * 
	 * @Description: (验证验证码)
	 * @author sunhao
	 * @date 2017年11月28日 上午11:38:05
	 */
	@RequestMapping("/imgvrifyControllerDefaultKaptcha")
	public void imgvrifyControllerDefaultKaptcha() {
		String captchaId = (String) request.getSession().getAttribute(SessionKeyEnum.VERIFY_CODE.getMsg());
		String verifyCode = request.getParameter(KeyEnum.VERIFY_CODE.getMsg());
		logger.info("Session  vrifyCode " + captchaId + " user vrifyCode " + verifyCode);

		JSONObject jsonObject = new JSONObject();
		if (!captchaId.equalsIgnoreCase(verifyCode)) {
			jsonObject.put(ErrEnum.INFO.getMsg(), "错误的验证码");
			writeToErrorResponse(jsonObject);
		} else {
			jsonObject.put(ErrEnum.INFO.getMsg(), "正确的验证码");
			writeToOkResponse(jsonObject);
		}
	}
}
