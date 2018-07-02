package com.haivin.file.sftp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

public class SftpClient {
	static Logger logger = LoggerFactory.getLogger(SftpClient.class);
	JSch jsch = new JSch();
	Session session = null;
	Channel channel = null;
	ChannelSftp sftp;
	String ip;
	String user;
	String psw;
	int port;
//	private SftpClient client;

	public static SftpClient getInstance(String ip, String user, String psw, int port) {
//		if (client == null || client.sftp == null || !client.sftp.isConnected()) {
//			if (client != null) {
//				client.disconnect();
//				client = null;
//			}
//			client = new SftpClient(ip, user, psw, port);
//			client.connect();
//		}
		SftpClient client = new SftpClient(ip, user, psw, port);
		client.connect();
		return client;
	}

	private SftpClient(String ip, String user, String psw, int port) {
		this.ip = ip;
		this.user = user;
		this.psw = psw;
		this.port = port;
	}

	/**
	 * 功能说明：
	 * 
	 * @author zhouran
	 * @since 2016年10月29日 下午2:51:17
	 */
	private void connect() {
		try {
			if (port <= 0) {
				// 连接服务器，采用默认端口
				session = jsch.getSession(user, ip);
			} else {
				// 采用指定的端口连接服务器
				session = jsch.getSession(user, ip, port);
			}

			// 如果服务器连接不上，则抛出异常
			if (session == null) {
				throw new JSchException("session is null");
			}

			// 设置登陆主机的密码
			session.setPassword(psw);// 设置密码
			// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
			session.setConfig("StrictHostKeyChecking", "no");
			// 设置登陆超时时间
			session.connect(3000);
//			session.setDaemonThread(true);
			// 创建sftp通信通道
			channel = (Channel) session.openChannel("sftp");
			channel.connect(1000);
			this.sftp = (ChannelSftp) channel;
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
//			try {
//				System.out.println("sftp connect error!!!");
//				disconnect();
//				Thread.sleep(2000);
//				connect();
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		}
	}

	public int uploadFile(InputStream in, String filePath, String fileName) {
		if (sftp != null && sftp.isConnected()) {
			OutputStream out = null;
			try {
				createDir(filePath,sftp);
				out = sftp.put(filePath + fileName);
				return FileCopyUtils.copy(in, out);
			} catch (SftpException | IOException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						logger.error(e.getMessage(),e);
					}
				}
				disconnect();
			}
		} else {
			disconnect();
//			client = null;
		}
		return -1;
	}

	private void createDir(String createpath, ChannelSftp sftp) throws SftpException {
		if (isDirExist(createpath,sftp)) {
			sftp.cd(createpath);
		}
		String pathArry[] = createpath.split("/");
		StringBuffer filePath = new StringBuffer("/");
		for (String path : pathArry) {
			if (path.equals("")) {
				continue;
			}
			filePath.append(path + "/");
			if (isDirExist(filePath.toString(),sftp)) {
				sftp.cd(filePath.toString());
			} else {
				// 建立目录
				sftp.mkdir(filePath.toString());
				// 进入并设置为当前目录
				sftp.cd(filePath.toString());
			}
		}
		sftp.cd(createpath);
	}

	private boolean isDirExist(String directory, ChannelSftp sftp) {
		boolean isDirExistFlag = false;
		try {
			SftpATTRS sftpATTRS = sftp.lstat(directory);
			isDirExistFlag = true;
			return sftpATTRS.isDir();
		} catch (Exception e) {
			if (e.getMessage().toLowerCase().equals("no such file")) {
				isDirExistFlag = false;
			}
		}
		return isDirExistFlag;
	}
	
	public long getFileSize(String src){
		if (this.sftp != null && this.sftp.isConnected()) {
			try {
				SftpATTRS attrs = this.sftp.stat(src);
				return attrs.getSize();
			} catch (SftpException e) {
				// TODO Auto-generated catch block
				logger.error("sftp file not exist:::"+src,e);
			}
		}
		return 0;
	}
	
	public void getFile(String src,OutputStream dst,long skip){
		if (this.sftp != null && this.sftp.isConnected()) {
			try {
				this.sftp.get(src, dst, new MyProgressMonitor(), ChannelSftp.RESUME, skip);
			} catch (SftpException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			}finally {
				disconnect();
			}
		} else {
			disconnect();
		}
	}
	
	
	public void getFile(String src,OutputStream dst){
		getFile(src, dst, 0);
	}
	
	public void getFile(String src, File dst){
		if (this.sftp != null && this.sftp.isConnected()) {
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(dst);
				this.sftp.get(src, fileOutputStream);
			} catch (SftpException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			}finally {
				disconnect();
			}
		} else {
			disconnect();
//			client = null;
		}
	}
	
	
	public BufferedImage getImgFile(String src){
		if (this.sftp != null && this.sftp.isConnected()) {
			InputStream in = null;
			try {
				in = this.sftp.get(src);
				BufferedImage img = ImageIO.read(in);
				return img;
			} catch (SftpException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			}finally {
				try {
					if(in != null){
						in.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage(),e);
				}
				disconnect();
			}
		} else {
			disconnect();
//			client = null;
		}
		return null;
	}

	@Deprecated
	public InputStream getInputStream(String filePath) {
		
		if (this.sftp != null && this.sftp.isConnected()) {
			try {
				
				InputStream in = this.sftp.get(filePath);	
				logger.info("************get in success, in="+in+"*****************");
				return in;
			} catch (SftpException e) {
				logger.error(e.getMessage(),e);
				// TODO Auto-generated catch block
			}finally {
//				disconnect();
			}
		} else {
			disconnect();
//			client = null;
		}
		return null;
	}

	public void disconnect() {
		if (sftp != null) {
			try {
				if(sftp.getSession() != null && sftp.getSession().isConnected()){
					sftp.getSession().disconnect();
				}
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			}
			sftp.disconnect();
			sftp = null;
		}
		if (channel != null) {
			channel.disconnect();
			channel = null;
		}
		if (session != null) {
			session.disconnect();
			session = null;
		}
	}

	public static void main(String[] args) {
		InputStream in = null;
		try {
			SftpClient client = SftpClient.getInstance("192.168.4.134", "sftptest", "sftptest", 22);
			in = new FileInputStream(new File("D:/administrator.vtp"));
			int i = client.uploadFile(in, "/upload/retention/test/test/", "administrator.vtp");
			System.out.println(i);
			SftpClient client2 = SftpClient.getInstance("192.168.4.134", "sftptest", "sftptest", 22);
			in = new FileInputStream(new File("D:/administrator.vtp"));
			int i2 = client2.uploadFile(in, "/upload/retention/test/test/", "administrator2.vtp");
			System.out.println(i2);
			System.out.println(client);
			System.out.println(client2);
			client2.disconnect();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		}finally {
			try {
				if(in != null)in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			}
		}
	}
}

class MyProgressMonitor implements SftpProgressMonitor {
    private long transfered;
	Logger logger = LoggerFactory.getLogger(MyProgressMonitor.class);

    @Override
    public boolean count(long count) {
        transfered = transfered + count;
        if(logger.isDebugEnabled()){
        	logger.debug("Currently transferred total size: " + transfered + " bytes");
        }
        return true;
    }

    @Override
    public void end() {
    	if(logger.isDebugEnabled()){
    		logger.debug("Transferring done.");
    	}
    }

    @Override
    public void init(int op, String src, String dest, long max) {
    	if(logger.isDebugEnabled()){
    		logger.debug("Transferring begin. src:::"+src+" dest:::"+dest+" op:::"+op+" max:::"+max);
    	}
    }
}
