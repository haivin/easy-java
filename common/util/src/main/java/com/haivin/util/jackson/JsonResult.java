/**
 * All rights reserved.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
 
package com.haivin.util.jackson;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/** <pre>
 * Title:Json结果类 
 * Description: 用于保存对象与Json字符串之间的转换处理结果 
 * </pre>
 * @author xumf
 * @since 2016年6月30日
 * @version 1.0
 */
public class JsonResult implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(JsonResult.class);

	/**
	 * 结果消息 
	 */
	String resultMsg = "OK";

	/**
	 * 结果Code  
	 */
	Integer  resultCode = JsonCode.SUCCESS;

	/**
	 * 长度
	 */
	long len;
	
	/**
	 * 结果信息 
	 */
	Object resultInfo;

	/**
	 * 语言类型码
	 * 1 中文
	 * 2 英文
	 */
	public interface LanguageStatusCode{
		public static final Integer LAGUAGE_STATUS_CHINESE = 1;
		public static final Integer LAGUAGE_STATUS_ENGLISH = 2;
	}
	

	/**
	 * 取得结果消息 
	 * @return 结果消息 
	 */
	public String getResultMsg() {
		return resultMsg;
	}
      
    /**
	 * 设置结果消息 
	 * @param resultMsg 结果消息  
	 */
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	/**
	 * 取得结果Code 
	 * @return 结果Code  
	 */
	public Integer getResultCode() {
		return resultCode;
	}

	/**
	 * Description: 设置结果Code  
	 * @param resultCode JSON CODE
	 * @since 2016年10月28日 下午4:47:00
	 */
	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
		this.setResultMsg(JsonCode.getMsgMap().get(resultCode));
	}
	
	/**
	 * Description: 设置结果Code  
	 * @param resultCode JSON CODE
	 * @param languageType 语言类型 1中文 2英文
	 * @since 2016年10月28日 下午4:47:00
	 */
	public void setResultCode(Integer resultCode ,Integer languageType) {
		this.resultCode = resultCode;
		if(LanguageStatusCode.LAGUAGE_STATUS_ENGLISH == languageType){
			this.setResultMsg(JsonCode.getEnglishMsgMap().get(resultCode));
		}else{
			this.setResultMsg(JsonCode.getMsgMap().get(resultCode));
		}
	}

	/**
	 * 取得结果Code 
	 * @return 结果Code  
	 */
	public Object getResultInfo() {
		return resultInfo;
	}

	/**
	 * 设置结果信息 
	 * @param resultInfo 结果信息  
	 */
	public void setResultInfo(Object resultInfo) {
		this.resultInfo = resultInfo;
	}

	/**
	 * Description: 构造函数  
	 * @param r  GsonResult对象 
	 * @author xumf
	 * @since 2016年6月28日 下午2:02:29
	 */
	public JsonResult(JsonResult r) {
		resultMsg = r.getResultMsg();
		resultCode = r.getResultCode();
		resultInfo = r.getResultInfo();
	}

	/**
	 * Description: 构造函数  
	 * @author xumf
	 * @since 2016年6月28日 下午2:02:29
	 */
	public JsonResult() {
		
	}
	
	/**
	 * Description: 显示消息
	 * @param code  消息code
	 * @return      消息内容 
	 * @author zwyl
	 * @since 2016年6月30日 下午5:28:20
	 */
	public static String showMsg(Integer code){
		if(code != null && code > 0){
			JsonCode c = new JsonCode(); 
			Map<Integer, String> msgMap = c.getMsgMap();
			String msg = msgMap.get(code);
			if(!StringUtils.isBlank(msg))
				return msg;
		}
		return null;
	}
	
	/**
	 * Description: 构造函数  
	 * @param code 消息code
	 * @author zwyl
	 * @since 2016年6月28日 下午2:02:29
	 */
	public JsonResult(Integer code) {
		this.resultCode = code;
		if(code != null && code > 0){
			JsonCode c = new JsonCode(); 
			Map<Integer, String> msgMap = c.getMsgMap();
			String msg = msgMap.get(code);
			if(!StringUtils.isBlank(msg))
				this.resultMsg = msg;
		}
	}
	
	/**
	 * Description: 构造函数  
	 * @param code 消息code
	 * @param msg 消息内容
	 * @author zwyl
	 * @since 2016年6月28日 下午2:02:29
	 */
	public JsonResult(Integer code, String msg) {
		this.resultCode = code;
		this.resultMsg = msg;
	}

	public JsonResult(Integer code, Integer language) {
		this.resultCode = code;
		if(LanguageStatusCode.LAGUAGE_STATUS_ENGLISH == language){
			this.setResultMsg(JsonCode.getEnglishMsgMap().get(resultCode));
		}else{
			this.setResultMsg(JsonCode.getMsgMap().get(resultCode));
		}
	}
	
	/**
	 * Description: 构造函数  
	 * @param data 数据 
	 * @author zwyl
	 * @since 2016年6月28日 下午2:02:29
	 */
	public JsonResult(Object data) {
		this.resultInfo = data instanceof ArrayList ? JsonUtils.encode(data) : data;
	}
	
	/**
	 * Description: 构造函数  
	 * @param code 消息code
	 * @param msg 消息内容
	 * @param data 数据 
	 * @author zwyl
	 * @since 2016年6月28日 下午2:02:29
	 */
	public JsonResult( Integer code, String msg, Object data) {
		this.resultCode = code;
		this.resultMsg = msg;
		this.resultInfo = data instanceof ArrayList ? JsonUtils.encode(data) : data;
	}


	/**
	 * 设置数据
	 * @param data 数据
	 */
	public void setData(Object data) {
		this.resultInfo = data instanceof ArrayList ? JsonUtils.encode(data): data;
		
	}

	/**
	 * 取得长度
	 * @return 长度
	 */
	public long getLen() {
		return len;
	}

	/**
	 * 设置长度
	 * @param len 长度
	 */
	public void setLen(long len) {
		this.len = len;
	}

	/**
	 * Description: 将当前JsonResult对象转换成JSON字符串。
	 * @return 转换后的Json字符串  
	 * @author zwyl
	 * @since 2016年6月28日 下午2:02:29
	 */
	public String objectToJsonStr() {
		return JsonUtils.encode(this);
	}

	/**
	 * Description: 将指定的Json字符串转换成JsonResult对象。
	 * @param s 指定的Json字符串 
	 * @return 转换后的JsonResult对象
	 */
	public static JsonResult jsonStrToObject(String s) {
		return JsonUtils.decode(s, JsonResult.class);
	}
	
	
	/**
	 * Description: 判断是否成功。
	 * @return 是否成功的标志位 
	 */
	public boolean isSuccess() {
		return JsonCode.SUCCESS == this.resultCode;
	}


}
