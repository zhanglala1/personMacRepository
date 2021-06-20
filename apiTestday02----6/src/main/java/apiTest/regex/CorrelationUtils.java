package apiTest.regex;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;

import apiTest.Utils.MapUtilss;

public class CorrelationUtils {
	
	
	static Map<String, Object> correlationMap = new LinkedHashMap<String, Object>();
	
	
	static Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
	
	
	
	public static void add2(String key,String value) {
		correlationMap.put(key, value);
	}
	
	public static void add(String jsonString,String correlation_regex) {
		
		if (!JSON.isValid(jsonString)) {
			return;
		}
		Map<String, Object> replaceMap =  MapUtilss.coverStringToMap(correlation_regex);
		if (!MapUtilss.isEmpty(replaceMap)) {
			
			for (String key : replaceMap.keySet()) {
				
				String value = replaceMap.get(key).toString();
				//如果提取多个数据  放到全局 map  
				Object valueObject =  JSONPath.read(jsonString, value);
				//如果提取不到,全局查找  补偿一下
				if(valueObject == null) {
					valueObject = JSONPath.read(jsonString, ".."+value);
				}
				// 如果提取多个数据
				if (valueObject instanceof List) {
					List<Object> list = (List<Object>) valueObject;
					int count = 0;
					for (Object arrayValue : list) {
						correlationMap.put(key+"_g"+(++count), arrayValue);
					}
					//如果地址参数瞎写,默认放一个值,放第一个  补偿一下
					if(!list.isEmpty()) {
						correlationMap.put(key, list.get(0));
					}
				}else {
					correlationMap.put(key, JSONPath.read(jsonString, value));
				}
			}
			System.out.println(correlationMap);
			replaceMap.clear();
		}
	}

	public static String getCorrelationValue(String group) {
		
		Object valueObject =  correlationMap.get(group);
		
		if(valueObject==null) {
			
			return "";
		}
		
		return valueObject.toString();
		
	}
	
	
	public static String repalce(String url) {
    	
    	Matcher m = pattern.matcher(url);
    	while (m.find()) {
			//System.out.println(m.group(0));
			//System.out.println(m.group(1));
    		url= url.replace(m.group(), getCorrelationValue(m.group(1)));
		}
    	
    	return url;
		
	}
	
	public static void main(String[] args) {
//    	String testString11 = "{\"code\":\"1\",\"msg\":\"登录成功\",\"uid\":\"0052dc59ee414313ad7d842881a7a22a\"}";
//    	add2("id3","testid3");
//    	add(testString11,"id=uid;id2=code");
//		String url=	repalce("https://www.baidu.com?pid=${id}&test=${id2}&test2=${id3}");
//		System.out.println(url);
//		correlationMap.clear();
    	String testString2 = "{\"code\":\"1\",\"msg\":\"登录成功\",\"data\":[{\"uid\":\"0052dc59ee414313ad7d842881a7a22a\"},{\"uid\":\"0052dc59ee414313ad7d842881a7a22b\"}]}";
		add(testString2, "uids=..uid");
		//correlationMap.clear();
	}

}
