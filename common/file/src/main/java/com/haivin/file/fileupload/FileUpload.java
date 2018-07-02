/**
 * All rights reserved.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.haivin.file.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * <pre>
 * Title:文件上传处理类 
 * Description: 文件上传处理 
 * </pre>
 * @author zwyl
 * @since 2016年6月28日
 * @version 1.0
 */
public class FileUpload {
	/**
	 * 图片大小 
	 */
	private int targetWidth = 100;
	private int targetHeight = 100;
	
	/**
	 * Logger对象 
	 */
	private Logger log = LoggerFactory.getLogger(FileUpload.class);
	
	/**
	 * 文件列表 
	 */
	private LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	
	/**
	 * 文件处理实体对象 
	 */
	private FileMeta fileMeta = null;
	
	/**
	 * 图片类型列表 
	 */
	private static final List<String> IMAGE_TYPES = Arrays.asList(
            "image/jpg","image/jpeg","image/png"
    );



	/**
	 * Description: 上传文件处理 
	 * @param request  MultipartHttpServletRequest对象 
	 * @param filePath 文件路径 
	 * @return 文件处理实体对象的链表 
	 * @author zwyl
	 * @since 2016年6月28日 下午2:02:29
	 */
	public LinkedList<FileMeta> doUpload(MultipartHttpServletRequest request, String filePath){
		return this.doUpload(request, filePath, new FileUploadOperator());
	}

	/**
	 * Description: 上传文件处理 
	 * @param request  MultipartHttpServletRequest对象  
	 * @param filePath 文件路径 
	 * @param operator 文件上传操作对象 
	 * @return 文件处理实体对象的链表 
	 * @author zwyl
	 * @since 2016年6月28日 下午2:04:01
	 */
	public LinkedList<FileMeta> doUpload(MultipartHttpServletRequest request, String filePath,
			FileUploadOperatorInter operator) {
		
		//创建迭代器
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		// 得到每个文件
		while (itr.hasNext()) {

			// 获取下一个MultipartFile
			mpf = request.getFile(itr.next());
			if(log.isDebugEnabled()){
				log.debug(mpf.getOriginalFilename() + " uploaded! " + files.size());
			}
			
//			if (files.size() >= 10)
//				files.pop();

			// 创建FileMeta 用于返回数据
			fileMeta = new FileMeta();
			fileMeta.setFileSize(mpf.getSize());
			if(mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf(".")).equalsIgnoreCase(".amr")){
				fileMeta.setFileType("audio/amr");
			}else if(mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf(".")).equalsIgnoreCase(".mp4")){
				fileMeta.setFileType("video/mp4");
			}else{
				fileMeta.setFileType(mpf.getContentType());
			}
			String newFilePath = filePath;
			try {
//				fileMeta.setBytes(mpf.getBytes());

				// 文件重命名 & 生成文件路径
				String originalFilename = mpf.getOriginalFilename();
				if(StringUtils.isEmpty(originalFilename)){//如果用户未上传文件
					break;
				}
				fileMeta.setFileUri(originalFilename);
				if (operator != null) {
					originalFilename = operator.rename(originalFilename);
					String generatedPath = operator.generatePath();
					newFilePath = filePath+generatedPath;
					fileMeta.setFileUri(generatedPath+originalFilename);
				}
				fileMeta.setFileName(originalFilename);
				// 复制文件到指定文件夹
				File file = new File(newFilePath);
				@SuppressWarnings("unused")
				
				//获取输出流
				OutputStream outputStream = null;
				if(operator != null&&operator.isSaveFile()){
					Object result = operator.saveFile(mpf.getInputStream(),newFilePath , originalFilename);
					fileMeta.setFileUri(result.toString());
				}else{
					boolean f = file.exists() ? false : file.mkdirs();
					outputStream = new FileOutputStream(newFilePath + originalFilename);
					FileCopyUtils.copy(mpf.getInputStream(), outputStream);
				}
				
				fileMeta.setFileName(mpf.getOriginalFilename());
				//创建图片缩略图
				if(operator != null && this.allowImgUpload(mpf.getContentType())){
					operator.thumbnail(new File(newFilePath + originalFilename), filePath,targetWidth,targetHeight);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
			}finally{
				
			}
			files.add(fileMeta);
		}
		if(operator != null){
			operator.saveUploadInfo(files);
		}
		// 返回的结果 [{"fileName":"app_engine-85x77.png","fileSize":"8Kb","fileType":"image/png"},...]
		return files;
	}
	
	/**
	 * Description: 判断指定的文件后缀是否是允许上传的图片文件类型 
	 * @param postfix 指定的文件后缀 
	 * @return 是否是允许上传的图片文件类型的标志位 
	 * @author zwyl
	 * @since 2016年6月28日 下午2:16:32
	 */
	private boolean allowImgUpload(String postfix){
        return IMAGE_TYPES.contains(postfix);
    }

	/**
	 * Description: 设置图片大小 
	 * @param targetWidth 图片宽度
	 * @param targetHeight 图片高度
	 * @author zwyl
	 * @since 2016年6月28日 下午2:18:35
	 */
	public void setImgSize(int targetWidth,int targetHeight) {
		this.targetWidth = targetWidth;
		this.targetHeight = targetHeight;
	}
}