/**
 * All rights reserved.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.haivin.util.jackson;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <pre>
 * Title:Jackson使用工具类
 * Description: 使用Jackson进行Json字符串和对象之间的相互转换 
 * </pre>
 * @author xumf
 * @since 2016年6月30日
 * @version 1.0
 */
public class JsonUtils {	
	static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	/**
	 * ObjectMapper对象 
	 */
	private final static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * ObjectMapper的配置信息 
	 */
    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);        
    }

    /**
     * 构造函数
     * @author xumf
     * @since 2016年6月30日 下午12:17:39
     */
    private JsonUtils() {
    }

    /**
     * Description: 将指定的对象转换为Json字符串  
     * @param obj   指定对象 
     * @return      Json字符串 
     * @author xumf
     * @since 2016年6月30日 下午12:17:45
     */
    public static String encode(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
        	logger.error(e.getMessage(),e);
        } catch (JsonMappingException e) {
        	logger.error(e.getMessage(),e);
        } catch (IOException e) {
        	logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * Description: 将指定的Json字符串转换为指定类型的对象 
     * @param json  指定的Json字符 
     * @param valueType 指定类型 (例如：Demo.class)
     * @return 指定类型的对象  
     * @author xumf
     * @since 2016年6月30日 下午12:21:08
     */
    public static <T> T decode(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (JsonParseException e) {
        	logger.error(e.getMessage(),e);
        } catch (JsonMappingException e) {
        	logger.error(e.getMessage(),e);
        } catch (IOException e) {
        	logger.error(e.getMessage(),e);
        }
        return null;
    }
    
    /**
     * Description: 将json array字符串转换为指定集合类型的对象 
     * @param json  json array字符串  
     * @param typeReference 指定集合类型
     * (例如：new TypeReference<List<Demo>>(){}、
     *        new TypeReference<HashMap<String, List<Demo>>>(){}、
     *        new TypeReference<HashMap<String, Object>>(){}、
     *        new TypeReference<Demo[]>(){}、
     *        new TypeReference<List<Map<String, String>>>(){}、
     *        new TypeReference<List<ComplexDemo>>(){})
     * @return
     * @author xumf
     * @since 2016年6月30日 下午2:33:26
     */
    @SuppressWarnings("unchecked")
    public static <T> T decode(String json, TypeReference<T> typeReference) {
        try {
            return (T) objectMapper.readValue(json, typeReference);
        } catch (JsonParseException e) {
        	logger.error(e.getMessage(),e);
        } catch (JsonMappingException e) {
        	logger.error(e.getMessage(),e);
        } catch (IOException e) {
        	logger.error(e.getMessage(),e);
        }
        return null;
    }
}
