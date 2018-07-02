package com.haivin.file.fileupload;

import com.haivin.file.aliyun.OssUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Title:
 * @Description:
 * @Author: zr
 * @Date: 2018/1/6 19:49
 */
public class AliyunOssFileUploadOperator  extends FileUploadOperator implements FileUploadOperatorInter {


    private final OssUtil ossUtil;

    public AliyunOssFileUploadOperator(OssUtil ossUtil) {
        this.ossUtil = ossUtil;
    }

    @Override
    public Object saveFile(InputStream in,String diskName, String fileName) {
        // TODO Auto-generated method stub
        return ossUtil.upload(in,diskName,fileName);
    }


    @Override
    public boolean isSaveFile() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Object thumbnail(File file, String filePath, int targetWidth, int targetHeight) throws IOException {
        return null;
    }
}
