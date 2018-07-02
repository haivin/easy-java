/**
 * All rights reserved.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
 
package com.haivin.util.jackson;

import java.util.HashMap;
import java.util.Map;

/** <pre>
 * Title:JsonCode类 
 * Description: Json结果Code编码 
 * </pre>
 * @author xumf
 * @since 2016年6月30日
 * @version 1.0
 */
public class JsonCode {
	/**
	 * 操作成功
	 */
	public static final int SUCCESS = 200;
	
	/**
	 * 帐号已经登录
	 */
	public static final int ACCOUNT_ALREADY_LOGIN = 201;
	
	/**
	 * 操作失败 
	 */
	public static final int BAD_REQUEST = 400;
	/**
	 * 系统异常
	 */
	public static final int SERVER_ERROR = 500;
	
	/**
	 * 未通过用户认证
	 */
	public static final int UNAUTHORIZED = 401;
	/**
	 * 账号或密码重复
	 */
	public static final int ACCOUNT_REPEAT = 402;
	/**
	 * 两次密码输入不一致
	 */
	public static final int TWO_PWDS_DIFFER = 403;
	/**
	 * 未找到资源
	 */
	public static final int NOT_FOUND = 404;
	/**
	 * 帐号或密码不存在
	 */
	public static final int ACCOUNT_NO_EXIT = 405;
	/**
	 * 原密码错误
	 */
	public static final int OLD_PWDS_ERROR = 406;
	/**
	 * 账号已被注册
	 */
	public static final int ACCOUNT_REGISTERED = 407;
	/**
	 * 您还未登录，请先登录
	 */
	public static final int ACCOUNT_NOT_LOGIN = 408;
	/**
	 * 请先选择需要筛选的内容
	 */
	public static final int NO_SCREEN_CONT = 409;
	/**
	 * 参数丢失
	 */
	public static final int PARAM_LOSS = 410;
	
	/**
	 * 签名错误
	 */
	public static final int SIGN_ERROR = 411;
	/**
	 * 没有权限
	 */
	public static final int NOT_PERMISSION = 412;
	/**
	 * 该账号未注册
	 */
	public static final int ACCOUNT_NOT_REG = 413;
	
	/**
	 * 验证码错误
	 */
	public static final int VERIFICATION_ERROR = 414;

	/**
	 * 非法请求
	 */
	public static final int ILLEGAL_REQUEST  = 415;
	
	/**
	 * 参数错误 
	 */
	public static final int PARAM_ERROR = 416;	
	
	/**
	 * 此连接已过期 
	 */
	public static final int LINK_EXPIRED = 417;
	
	/**
	 * 卡是"未卖出"状态，不能使用 
	 */
	public static final int CARD_UNSOLD_ERROR = 418;
		
	/**
	 * 卡已使用 
	 */
	public static final int CARD_USED_ERROR = 419;	
	
	/**
	 * 手机号格式错误  
	 */
	public static final int MOBILE_ERROR = 420;	
	
	/**
	 * 验证码当天请求次数超过上限值   
	 */
	public static final int REQUEST_EXCEED_LIMITS = 421;
	/**
	 * 聊天会话已被他人接受
	 */
	public static final int ACCEPT_BY_OTHER = 422;
	/**
	 * 聊天会话已失效
	 */
	public static final int CHART_SESSION_INVALID = 423;
	/**
	 * 预约邀请已失效
	 */
	public static final int APPOINTMENT_INVITE_INVALID = 424;
	
	/**
	 * 密码已过有效期
	 */
	public static final int PASSWORD_EXPIRED = 425;
	
	/**
	 * 部分导入成功 (存在导入失败的行)
	 */
	public static final int IMPORT_PARTLY_SUCCESSFUL = 426;

	/**
	 * 会话过期
	 */
	public static final int SESSION_TIMEOUT = 427;
	
	/**
	 * 客户的经度或纬度信息不存在 
	 */
	public static final int LNG_LAN_NOT_EXIST = 428;
	
	/**
	 * 当前会话已有经销商接入，不能重复邀请经销商 
	 */
	public static final int DEALER_USER_HAS_JOINED = 429;
	
	/**
	 * 账号被禁用
	 */
	public static final int ACCOUNT_DISABLED = 430;
	
	/**
	 * 培训资料不存在
	 */
	public static final int DOCUMENT_NO_EXIST = 431;
	/**
	 * 会话邀请已超时
	 */
	public static final int SESSION_INVITE_TIMEOUT = 432;
	
	public static final int DATA_COMPARE = 433;
	/**
	 * 未开始测试提示
	 */
	public static final int NO_ANSWER = 435;
	/**
	 * 培训课程不存在 
	 */
	public static final int COURSE_NO_EXIST = 437;
	/**
	 *  您已进行过点踩操作，不可继续点赞
	 */
	public static final int THUMB_DOWN = 438;
		
	/**
	 * 重复操作
	 */
	public static final int REPETITIVE_OPERATION = 439;	
	
	/**
	 * 您已进行过点赞操作，不可继续点踩
	 */
	public static final int THUMB_UP = 440;
	
	/**
	 * 请完善游客信息再操作
	 */
	public static final int VISITOR_REMINDER = 450;
	/**
	 * 产品使用过期
	 */
	public static final int PRODUCT_EXPIRES = 451;
	/**
	 * 系统配置中
	 */
	public static final int SETTING_SYSTEM= 452;
	
	/**
	 * 客服会话高峰提示
	 */
	public static final int CSAGENT_BUSY = 453;
	/**
	 * 客服结束提示
	 */
	public static final int SESSION_END = 454;
	
	/**
	 * 系统异常
	 */
	public static final String SYSTEM_ERROR = "系统异常";
	
	/**
	 * 消息Map对象 
	 */
	private static Map<Integer, String> msgMap = new HashMap<>();
	
	/**
	 * Description: 取得消息Map对象 
	 * @return      消息Map对象  
	 * @author zwyl
	 * @since 2016年6月30日 下午6:04:38
	 */
	public static Map<Integer, String> getMsgMap() {
		msgMap.put(200, "OK");
		msgMap.put(201, "账户已经登录");
		msgMap.put(400, "操作失败");
		msgMap.put(401, "未通过用户认证");
		msgMap.put(402, "帐号或密码重复");
		msgMap.put(403, "两次密码输入不一致");
		msgMap.put(404, "未找到资源");
		msgMap.put(405, "帐号或密码不存在");
		msgMap.put(406, "原密码错误");
		msgMap.put(407, "帐号已被注册");
		msgMap.put(408, "您还未登录，请先登录");
		msgMap.put(409, "请先选择需要筛选的内容");
		msgMap.put(410, "参数丢失");
		msgMap.put(411, "签名错误");
		msgMap.put(412, "没有权限");
		msgMap.put(413, "该帐号未注册");
		msgMap.put(414, "验证码错误");
		msgMap.put(415, "非法请求");	
		msgMap.put(416, "参数错误");	
		msgMap.put(417, "此链接已过期");	
		msgMap.put(418, "卡是\"未卖出\"状态，不能使用 ");	
		msgMap.put(419, "卡已使用");	
		msgMap.put(420, "手机号格式错误");
		msgMap.put(421, "验证码当天请求次数超过上限值");
		msgMap.put(422, "会话已被他人接入");
		msgMap.put(423, "会话邀请已失效");
		msgMap.put(424, "预约邀请已失效");
		msgMap.put(425, "密码已过有效期");
		msgMap.put(426, "部分导入成功");		
		msgMap.put(427, "会话已超时");
		msgMap.put(428, "客户的经度或纬度信息不存在");
		msgMap.put(429, "当前会话已有经销商接入，不能重复邀请经销商 ");
		msgMap.put(430, "账号已被禁用，请联系管理员 ");
		msgMap.put(431, "培训资料不存在");
		msgMap.put(432, "会话邀请已超时");
		msgMap.put(433, "时间不能小于开始时间");
		msgMap.put(435, "您还未开始答题，请先答题");
		msgMap.put(437, "培训课程不存在");
		msgMap.put(438, "您已进行过点踩操作，不可继续点赞");	
		msgMap.put(439, "重复操作");	
		msgMap.put(440, "您已进行过点赞操作，不可继续点踩");
		msgMap.put(450, "请完善游客信息后再操作");
		msgMap.put(451, "产品使用过期");
		msgMap.put(452, "系统配置中");
		msgMap.put(500, "系统异常");
		return msgMap;
	}
	
	/**
	 * Description: 取得英文消息Map对象 
	 * @return 英文消息Map对象  
	 * @author liun
	 * @since 2016年10月28日 下午4:46:28
	 */
	public static Map<Integer, String> getEnglishMsgMap() {
		msgMap.put(200, "OK");
		msgMap.put(201, "This account is logged in");
		msgMap.put(400, "Operation failed");
		msgMap.put(401, "Have not passed user authentication");
		msgMap.put(402, "Duplicate account or password");
		msgMap.put(403, "Two passwords are not the same");
		msgMap.put(404, "Resource not found");
		msgMap.put(405, "Account or password does not exist");
		msgMap.put(406, "Old password is not correct");
		msgMap.put(407, "Account has been registered");
		msgMap.put(408, "You have not logged in, please login first");
		msgMap.put(409, "Please select the content that needs to be filtered");
		msgMap.put(410, "Parameter missing");
		msgMap.put(411, "Signature error");
		msgMap.put(412, "No permissions");
		msgMap.put(413, "Account is not registered");
		msgMap.put(414, "Verification code error");
		msgMap.put(415, "Illegal request");
		msgMap.put(416, "Param error");
		msgMap.put(417, "This link has expired");
		msgMap.put(422, "Accepted by others");
		msgMap.put(423, "Conversation invitation is timeout");
		msgMap.put(424, "Appointment invitation is timeout");
		msgMap.put(425, "Password expires");	
		msgMap.put(427, "Session is timeout");
		msgMap.put(428, "Customer's longitude or latitude info does not exist");
		msgMap.put(429, "Repeated invitation is not allowed as dealer user has joined the conversation.");
		msgMap.put(430, "Account has been disabled; please contact the administrator");
		msgMap.put(431, "document does not exist");
		msgMap.put(432, "Session invitation is timeout");
		msgMap.put(433, "End time is not less than start time");
		msgMap.put(435, "You have not started testing");
		msgMap.put(437, "Training courses does not exist");
		msgMap.put(438, "You have done thumb down, cannot continue to thumb up");
		msgMap.put(439, "Repetitive operation");
		msgMap.put(440, "You have done thumb up, cannot continue to thumb down");
		msgMap.put(450, "Please improve the visitor's information and then reoperation");
		msgMap.put(500, "System Exception");
		return msgMap;
	}
	
}
