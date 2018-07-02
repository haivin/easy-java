/**
 * All rights reserved.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.haivin.file.fileupload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//ignore "bytes" when return json format
@JsonIgnoreProperties({ "bytes" })
/**
 * <pre>
 * Title:文件处理实体类 
 * Description: 文件处理实体 
 * </pre>
 * @author zwyl
 * @since 2016年6月23日
 * @version 1.0
 */
public class FileMeta {
	/**
	 * 文件ID 
	 */
	private String id;
	
	/**
	 * 文件名称 
	 */
	private String fileName;
	
	/**
	 * 文件大小 
	 */
	private long fileSize;
	
	/**
	 * 文件类型 
	 */
	private String fileType;
	
	/**
	 * 文件URI 
	 */
	private String fileUri;
	
	/**
	 * 字节数组 
	 */
	private byte[] bytes;
	
	/**
	 * 文件类型Code
	 */
	private Integer fileTypeCode;
	
	/**
	 * 取得id
	 * @return the id
	 */	
	public String getId() {
		return id;
	}
	
	/**
	 * 设置id
	 * @param id the id to set
	 */	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 取得fileName
	 * @return the fileName
	 */	
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * 设置fileName
	 * @param fileName the fileName to set
	 */	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	/**
	 * 取得fileSize
	 * @return the fileSize
	 */	
	public long getFileSize() {
		return fileSize;
	}

	
	/**
	 * 设置fileSize
	 * @param fileSize the fileSize to set
	 */	
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	
	/**
	 * 取得fileType
	 * @return the fileType
	 */	
	public String getFileType() {
		return fileType;
	}

	
	/**
	 * 设置fileType
	 * @param fileType the fileType to set
	 */	
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	
	/**
	 * 取得fileUri
	 * @return the fileUri
	 */	
	public String getFileUri() {
		return fileUri;
	}

	
	/**
	 * 设置fileUri
	 * @param fileUri the fileUri to set
	 */	
	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}

	
	/**
	 * 取得bytes
	 * @return the bytes
	 */	
	public byte[] getBytes() {
		return bytes;
	}

	
	/**
	 * 设置bytes
	 * @param bytes the bytes to set
	 */	
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	
	public Integer getFileTypeCode() {
		return fileTypeCode;
	}

	
	public void setFileTypeCode(Integer fileTypeCode) {
		this.fileTypeCode = fileTypeCode;
	}	
	
}