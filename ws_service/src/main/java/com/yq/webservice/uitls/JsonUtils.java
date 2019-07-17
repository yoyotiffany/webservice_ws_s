package com.yq.webservice.uitls;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 自定义响应结构
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();


    public static String objectToJson(Object data, boolean includeNull) {
    	try {
    		if(includeNull == false)
				MAPPER.setSerializationInclusion(Include.NON_NULL);
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 将对象转换成json字符串，默认为不含NULL。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
    	return objectToJson(data,false);
    }
    

    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    
    
    
	public static String getJsonString(int records, List<?> rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("records", records);
		map.put("rows", rows);
		return JsonUtils.objectToJson(map);
	}
    
	/**
     * 将json转化成Map
     * 
     * @param json
     * @param mapClass
     * @param keyClass
     * @param valueClass
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <K, V> Map<K, V> toMap(String json, Class<? extends Map> mapClass, Class<K> keyClass,
            Class<V> valueClass) throws JsonParseException, JsonMappingException, IOException {
        JavaType javaType = MAPPER.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
        return MAPPER.readValue(json, javaType);
    }

}
