package com.haivin.file.aliyun;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.activation.MimetypesFileTypeMap;


import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class OssUtil {
	protected final static Logger logger = LoggerFactory.getLogger(OssUtil.class);


	@Value("${aliyun.oss.endPoint}")
	private String endPoint;
	@Value("${aliyun.oss.accessKeyId}")
	private String accessKeyId;
	@Value("${aliyun.oss.accessKeySecret}")
	private String accessKeySecret;
	@Value("${aliyun.oss.bucketName}")
	private String bucketName;

	/**
	 * 获取阿里云OSS客户端对象
	 * */
	public OSSClient getOSSClient() {
		return new OSSClient(endPoint, accessKeyId, accessKeySecret);
	}

	/**
	 *  向阿里云的OSS存储中存储文件
	 * @param file 附件
	 * @param diskName fieldid
	 * @return
	 */
	public final String uploadObject2OSS(File file, String diskName,String fileName) {
		OSSClient client = getOSSClient();
		String resultStr = null;
		try {
			InputStream is = new FileInputStream(file);
			//String fileName = file.getName();
			Long fileSize = file.length();
			// 创建上传Object的Metadata
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(is.available());
			metadata.setCacheControl("no-cache");
			metadata.setHeader("Pragma", "no-cache");
			metadata.setContentEncoding("utf-8");
			metadata.setContentType(getContentType(fileName));
			metadata.setContentDisposition("filename/filesize=" + fileName
					+ "/" + fileSize + "Byte.");
			// 上传文件
			PutObjectResult putResult = client.putObject(bucketName, diskName
					+ fileName, is, metadata);
			// 解析结果
			resultStr = putResult.getETag();
		} catch (Exception e) {
			logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
		}
		return resultStr;
	}

	/**
	 * 向阿里云的OSS存储中存储文件
	 * @param inputStream 上传文件的输入流
	 * @param diskName 存储路径
	 * @param fileName 文件名称
	 * @return
	 */
	public String upload(InputStream inputStream,String diskName, String fileName) {
		if (inputStream == null) {
			return null;
		}
		// 创建OSS客户端
		OSSClient ossClient = getOSSClient();
		try {
			// 判断文件容器是否存在，不存在则创建
			if (!ossClient.doesBucketExist(bucketName)) {
				ossClient.createBucket(bucketName);
				CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
				createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
				ossClient.createBucket(createBucketRequest);
			}
			// 创建文件路径
			String fileUrl = diskName+fileName;
			// 上传文件
			PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, inputStream));
			if (null != result) {
				return fileUrl;
			}
		} catch (OSSException oe) {
			logger.error(oe.getMessage());
		} catch (ClientException ce) {
			logger.error(ce.getMessage());
		} finally {
			// 关闭OSS服务，一定要关闭
			ossClient.shutdown();
		}
		return null;

	}


	/**
	 * 根据key获取OSS服务器上的文件输入流
	 * @param diskName fieldid
	 * @param fileName 文件名称
	 * @return
	 */
	public final InputStream getOSS2InputStream(String diskName,
			String fileName) {
		OSSClient client = getOSSClient();
		OSSObject ossObj = client.getObject(bucketName, diskName + fileName);
		return ossObj.getObjectContent();
	}

	/**
	 *  根据key删除OSS服务器上的文件
	 * @param diskName fieldid
	 * @param fileName 文件名称
	 */
	public void deleteFile(String diskName, String fileName) {
		OSSClient client = getOSSClient();
		client.deleteObject(bucketName, diskName + fileName);
	}

	/**
	 * 通过文件名判断并获取OSS服务文件上传时文件的contentType
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件的contentType
	 */
	public final String getContentType(String fileName) {
		File f = new File(fileName);
		return new MimetypesFileTypeMap().getContentType(f);
	}

}