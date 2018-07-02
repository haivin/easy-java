package com.haivin.file.fileupload;

import com.haivin.file.sftp.SftpClient;

import java.io.InputStream;

public class SftpFileUploadOperator extends FileUploadOperator implements FileUploadOperatorInter {

	private String ip;
	private String user;
	private String pwd;
	private int port;

	public SftpFileUploadOperator(String ip, String user, String pwd, int port) {
		this.ip = ip;
		this.user = user;
		this.pwd = pwd;
		this.port = port;
	}

	@Override
	public boolean isSaveFile() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object saveFile(InputStream in,String filePath,String fileName) {
		// TODO Auto-generated method stub
		SftpClient client = SftpClient.getInstance(ip, user, pwd, port);
		return client.uploadFile(in, filePath,fileName);
	}

	@Override
	public void destructor() {
		// TODO Auto-generated method stub
		
	}
}
