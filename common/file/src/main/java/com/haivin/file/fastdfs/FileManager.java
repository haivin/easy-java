package com.haivin.file.fastdfs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSON;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.csource.fastdht.FastDHTClient;
import org.csource.fastdht.KeyInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ShenHuaJie
 * @version 2016年6月27日 上午9:51:06
 */
@SuppressWarnings("serial")
public class FileManager implements Config {
    private static Logger logger = LoggerFactory.getLogger(FileManager.class);
	private TrackerServer trackerServer;
	private StorageServer storageServer;
	private StorageClient storageClient;
	private FastDHTClient fastDHTClient;

	private ExecutorService searchService = Executors.newSingleThreadExecutor();

	static { // Initialize Fast DFS Client configurations
		try {
			String path = FileManager.class.getResource("/").toString().replace("file:/", "");
			ClientGlobal.init(path + "fastdfs_client.conf");
			org.csource.fastdht.ClientGlobal.init(path + "fastdht_client.conf");
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public static FileManager getInstance() {
		return new FileManager();
	}

	private FileManager() {
		try {
			TrackerClient trackerClient = new TrackerClient();
			this.trackerServer = trackerClient.getConnection();
			this.storageClient = new StorageClient(trackerServer, storageServer);
			fastDHTClient = new FastDHTClient(true);
		} catch (Exception ex) {
			logger.error("", ex);
		}
	}

	public void upload(final FileModel file) {
		logger.info("File Name: " + file.getFilename() + ". File Length: " + file.getContent().length);

		final NameValuePair[] meta_list = new NameValuePair[] { new NameValuePair("mime", file.getMime()),
				new NameValuePair("size", file.getSize()), new NameValuePair("filename", file.getFilename()) };

		long startTime = System.currentTimeMillis();
		String[] uploadResults = null;
		try {
			uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
		} catch (IOException e) {
			logger.error("IO Exception when uploadind the file: " + file.getFilename(), e);
		} catch (Exception e) {
			logger.error("Non IO Exception when uploadind the file: " + file.getFilename(), e);
		}

		if (uploadResults == null) {
			logger.error("upload file fail, error code: " + storageClient.getErrorCode());
		} else {
			for (String e:uploadResults) {
				System.out.println(e);
			}

			final String groupName = uploadResults[0];
			final String remoteFileName = uploadResults[1];

			String fileAbsolutePath = "/data/" + groupName + SEPARATOR
					+ remoteFileName;
			file.setRemotePath(fileAbsolutePath);
			logger.info("upload_file time used: " + (System.currentTimeMillis() - startTime) + " ms. group_name: "
					+ groupName + ", remoteFileName:" + " " + remoteFileName);

			searchService.execute(new Runnable() {
				public void run() {
					try {
						KeyInfo keyInfo = new KeyInfo(file.getNamespace(), file.getObjectId(), file.getKey());
						FastDfsFile fastDfsFile = new FastDfsFile();
						fastDfsFile.setGroupName(groupName);
						fastDfsFile.setFileName(remoteFileName);
						fastDfsFile.setNameValuePairs(meta_list);
						fastDHTClient.set(keyInfo, JSON.toJSONString(fastDfsFile));
					} catch (Exception e) {
						logger.error("", e);
					}
				}
			});
		}
	}

	public FileInfo getFile(String groupname, String objectId) {
		FileOutputStream fos = null;
		File file = new File("C:\\Users\\zr\\Desktop\\111.zip");
		try {
			FileInfo fileInfo = storageClient.get_file_info(groupname,objectId);
			byte[] fileByte = storageClient.download_file(groupname,objectId);
			System.out.println(fileByte.length);
			return fileInfo;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
		return null;
	}

	public FileInfo getFile(String namespace, String objectId, String key) {
		try {
			KeyInfo keyInfo = new KeyInfo(namespace, objectId, key);
			String info = fastDHTClient.get(keyInfo);
			FastDfsFile fastDfsFile = JSON.parseObject(info, FastDfsFile.class);
			return storageClient.get_file_info(fastDfsFile.getGroupName(), fastDfsFile.getFileName());
		} catch (IOException e) {
			logger.error("IO Exception: Get File from Fast DFS failed", e);
		} catch (Exception e) {
			logger.error("Non IO Exception: Get File from Fast DFS failed", e);
		}
		return null;
	}

	public void deleteFile(String groupName, String remoteFileName) throws Exception {
		storageClient.delete_file(groupName, remoteFileName);
	}

    public static void main(String[] args) {
	    FileModel fileModel = new FileModel("test","123","C:\\Users\\zr\\Desktop\\adduser.zip");
		FileManager fileManager = FileManager.getInstance();
		fileManager.upload(fileModel);
//		fileManager.getFile("group2","M00/00/00/rBAIZlo5GoGAdSGpABAfMS-mhu0668.zip");
    }
}