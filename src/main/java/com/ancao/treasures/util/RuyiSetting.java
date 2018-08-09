/*********************************************************************/
/*  文件名  RuyiSetting.java    　                                   */
/*  程序名  如医配置信息工具                     						 */
/*  版本履历   2014/10/28  修改                 刘伟    			 */
/*         Copyright 2014 DILIN. All Rights Reserved.                */
/*********************************************************************/
package com.ancao.treasures.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 如医配置信息工具
 */
public class RuyiSetting {

	/**
	 * 如医配置文件
	 */
	private static final String RUYI_SETTING_FILE = "/application.properties";

	/**
	 * 如医配置信息
	 */
	private static Properties ruyiSetting = null;

	/**
	 * 默认日期格式
	 */
	private static final SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy/MM/dd");

	/**
	 * 取得文件上传路径
	 * 
	 * @param path
	 *            文件上传路径KEY
	 * @return 文件上传路径
	 * @throws Exception
	 *             处理异常
	 */
	private static String getUploadFilePath(String path) throws IOException {
		if (ruyiSetting == null) {
			ruyiSetting = new Properties();
			ruyiSetting.load(RuyiSetting.class.getResourceAsStream(RUYI_SETTING_FILE));
		}
		return ruyiSetting.getProperty(path);
	}

	/**
	 * 取得文件上传根路径
	 * 
	 * @return
	 */
	public static String getUploadFileRootPath() {
		String path = null;
		try {
			path = getUploadFilePath("UPLOAD_FILE_PATH");
		} catch (IOException ex) {
			if ("\\".equals(File.separator)) { // Windows
				path = "D:\\BanLvFile";
			} else if ("/".equals(File.separator)) { // Linux
				path = "/BanLvFile";
			}
		}
		return path;
	}

	/**
	 * 取得图片文件上传路径
	 * 
	 * @return
	 */
	public static String getVideoPath(Date date) {
		return getUploadFileRootPath() + getVideoURI(date);
	}

	/**
	 * 取得图片文件下载路径
	 * 
	 * @return
	 */
	public static String getVideoURI(Date date) {
		return "img/" + formatYMD.format(date) + "/";
	}

	/**
	 * 取得首页图片文件上传路径
	 * 
	 * @return
	 */
	public static String getHomePagePath(Date date) {
		return getUploadFileRootPath() + getHomePageURI(date);
	}

	/**
	 * 取得首页图片文件下载路径
	 * 
	 * @return
	 */
	public static String getHomePageURI(Date date) {
		return "homePage/" + formatYMD.format(date) + "/";
	}

}
