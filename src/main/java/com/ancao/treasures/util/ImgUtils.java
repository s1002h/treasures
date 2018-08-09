/*********************************************************************/
/*  文件名  CheckPrivilegeInterceptor.java							 */
/*  程序名  图片工具类												 */
/*  改版记录 2014/10/16      创建           刘伟 		   	             */
/*         Copyright 2014 DLIN. All Rights Reserved.                 */
/*********************************************************************/

package com.ancao.treasures.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImgUtils {

	/**
	 * 日志输出实例
	 */
	private static Logger log = LoggerFactory.getLogger(ImgUtils.class);

	/**
	 * 判断目录或文件是否存在 不存在返回 false
	 */
	private static boolean flag;
	/**
	 * 文件地址
	 */
	private static File file;

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * 
	 * @param sPath
	 *            要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false
	 */
	public static boolean DeleteFolder(String sPath) {

		flag = false;
		file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) {
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sPath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		flag = false;
		file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	public static void copy(File file, String filePath) throws IOException {
		InputStream in = new FileInputStream(file);
		// 没有path文件夹则创建
		File uploadFile = new File(filePath);
		if (!uploadFile.getParentFile().exists()) {
			uploadFile.getParentFile().mkdirs();
		}
		OutputStream out = new FileOutputStream(uploadFile);
		byte[] buffer = new byte[(int) file.length()];
		int length;
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.close();
	}

	/**
	 * 上传图片
	 * 
	 * @param path
	 *            保存路径
	 * @param file
	 *            图片文件
	 * @param fileName
	 *            图片名
	 */
	public static void uploadFile(File file, String fileName, String path) throws IOException {
		InputStream in = new FileInputStream(file);
		File uploadFile = new File(path, fileName);
		if (!uploadFile.getParentFile().exists()) {
			uploadFile.getParentFile().mkdirs();
		}
		OutputStream out = new FileOutputStream(uploadFile);
		byte[] buffer = new byte[(int) file.length()];
		int length;
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.close();
	}

	/**
	 * 下载网络图片
	 * 
	 * @param fileUrl
	 *            网络资源地址
	 * @param savePath
	 *            本地保存地址
	 * @return true:下载成功,false:下载失败
	 */
	public static boolean saveUrlAs(String fileUrl, String savePath) {

		try {
			/* 将网络资源地址传给,即赋值给url */
			URL url = new URL(fileUrl);

			/* 此为联系获得网络资源的固定格式用法，以便后面的in变量获得url截取网络资源的输入流 */
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			DataInputStream in = new DataInputStream(connection.getInputStream());

			/* 此处也可用BufferedInputStream与BufferedOutputStream 需要保存的路径 */
			DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath));

			/* 将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址 */
			byte[] buffer = new byte[4096];
			int count = 0;
			while ((count = in.read(buffer)) > 0)/* 将输入流以字节的形式读取并写入buffer中 */
			{
				out.write(buffer, 0, count);
			}
			out.close();/* 后面三行为关闭输入输出流以及网络资源的固定格式 */
			in.close();
			connection.disconnect();
			return true;/* 网络资源截取并存储本地成功返回true */

		} catch (Exception e) {
			log.error(fileUrl, e);
			return false;
		}
	}
}
