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
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <pre>
 * Title:文件上传操作接口 
 * Description: 文件上传操作接口 
 * </pre>
 * @author zwyl
 * @since 2016年6月28日
 * @version 1.0
 */
public interface FileUploadOperatorInter {

	/**
	 * Description: 重命名指定的文件名。 
	 * @param originalFileName 指定的文件名 
	 * @return
	 * @author zwyl
	 * @since 2016年6月28日 下午2:31:18
	 */
	String rename(String originalFileName);

	/**
	 * Description: 设置文件存储路径，默认按照时间存储。
	 * @return
	 * @author zwyl
	 * @since 2016年6月28日 下午2:31:50
	 */
	String generatePath();
	
	/**
	 * Description: 按指定大小生成指定文件的缩略图。 
	 * @param file
	 * @param filePath
	 * @param targetWidth
	 * @param targetHeight
	 * @return
	 * @throws IOException
	 * @author zwyl
	 * @since 2016年6月28日 下午2:32:09
	 */
	Object thumbnail(File file, String filePath, int targetWidth, int targetHeight) throws IOException;
	
	/**
	 * Description: 保存上传的文件信息。 
	 * @param fileMetas
	 * @author zwyl
	 * @since 2016年6月28日 下午2:32:31
	 */
	void saveUploadInfo(List<FileMeta> fileMetas);
	
	/**
	 * 功能说明：保存文件
	 * @return
	 * @author zhouran
	 * @since 2016年9月29日 下午11:07:16
	 */
	Object saveFile(InputStream in, String filePath, String fileName);

	/**
	 * 功能说明：是否处理保存文件
	 * @return
	 * @author zhouran
	 * @since 2016年9月29日 下午11:07:16
	 */
	boolean isSaveFile();
	
	/**
	 * 功能说明：处理回收
	 * @author zhouran
	 * @since 2016年10月29日 下午1:59:34
	 */
	void destructor();

}