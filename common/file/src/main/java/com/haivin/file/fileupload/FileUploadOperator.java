package com.haivin.file.fileupload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class FileUploadOperator implements FileUploadOperatorInter {
	
	protected String datePath = new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
	
	/* (non-Javadoc)
	 * @see com.chuangke.util.fileupload.FileUploadOperatorInter#rename(java.lang.String)
	 */
	@Override
	public String rename(String originalFileName) {
		String fileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
		String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
		String date = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		int random = (int)Math.random()*1000;
		return fileName+date+random+suffix;
	}

	/* (non-Javadoc)
	 * @see com.chuangke.util.fileupload.FileUploadOperatorInter#generatePath(java.lang.String)
	 */
	@Override
	public String generatePath() {
		return datePath;
	}

	@Override
	public Object thumbnail(File file, String filePath,int targetWidth,int targetHeight) throws IOException {
		// TODO Auto-generated method stub
		BufferedImage thumbnail = Scalr.resize(ImageIO.read(file), targetWidth,targetHeight);
		String fileName = file.getName();
		File fileDir = new File(filePath+"thumb/"+datePath);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
        String thumbnailFilename = filePath+"thumb/"+datePath+fileName;
        File thumbnailFile = new File(thumbnailFilename);
        ImageIO.write(thumbnail, "png", thumbnailFile);
		return null;
	}

	@Override
	public void saveUploadInfo(List<FileMeta> fileMetas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSaveFile() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object saveFile(InputStream in,String filePath,String fileName) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public void destructor() {
		// TODO Auto-generated method stub
		
	}
	
}
