package com.ggh.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;
@Component
public class UploadImgUtils {

	/**
	 * 文件存放位置
	 */
	@Value("${FILE_ADDRESS}")
	private String pathReal ;

	/**
	 * 文件拼接地址
	 */
	@Value("${HOST_PORT}")
	private String hostPort ;
	
	private static final Logger logger = LoggerFactory
			.getLogger(UploadImgUtils.class);

	/**
	 * 上传图片
	 * 
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public String uploadFiles(MultipartFile[] files) throws Exception {
		
		String path = "";
		String urls = "";
		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					MultipartFile mf = files[i];
					if (!mf.isEmpty()) {
						// 生成uuid作为文件名称
						String uuid = UUID.randomUUID().toString()
								.replaceAll("-", "");
						// 获得文件类型（可以判断如果不是图片，禁止上传）
						String contentType = mf.getContentType();
						logger.info("= = = > 图片类型：{}", contentType);
						// 获得文件后缀名称
						String imageAfterName = contentType
								.substring(contentType.indexOf("/") + 1);
						path = "/images/" + uuid + "." + imageAfterName;
						File newFile = new File(pathReal + path);
						logger.info("= = = > 图片创建位置：{}", pathReal + path);
						// 如果文件夹不存在则创建
//						if (!newFile.exists() && !newFile.isDirectory()) {
//							newFile.mkdirs();
//						}
						if(!newFile.getParentFile().exists()) {
							newFile.getParentFile().mkdir();
						}
						mf.transferTo(newFile);
						if (i == files.length - 1) {
							urls += hostPort + path;
						} else {
							urls += hostPort + path + ",";
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("#### 上传图片异常");
		}
		logger.info("= =  > 生成图片URL：{}", urls);
		return urls;
	}
	/**
	 * 上传图片
	 * 
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public String uploadFilesMp3(MultipartFile[] files) throws Exception {
		String path = "";
		String urls = "";
		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					MultipartFile mf = files[i];
					if (!mf.isEmpty()) {
						// 生成uuid作为文件名称
						String uuid = UUID.randomUUID().toString()
								.replaceAll("-", "");
						// 获得文件类型（可以判断如果不是图片，禁止上传）
						String contentType = mf.getContentType();
						logger.info("= = = > 图片类型：{}", contentType);
						// 获得文件后缀名称
						String imageAfterName = contentType
								.substring(contentType.indexOf("/") + 1);
						path = "/images/" + uuid + "." + "amr";
						File newFile = new File(pathReal + path);
						logger.info("= = = > 图片创建位置：{}", pathReal + path);
						// 如果文件夹不存在则创建
						if (!newFile.exists() && !newFile.isDirectory()) {
							newFile.mkdirs();
						}
						mf.transferTo(newFile);
						if (i == files.length - 1) {
							urls += hostPort + path;
						} else {
							urls += hostPort + path + ",";
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("#### 上传图片异常");
		}
		logger.info("= =  > 生成图片URL：{}", urls);
		return urls;
	}

}