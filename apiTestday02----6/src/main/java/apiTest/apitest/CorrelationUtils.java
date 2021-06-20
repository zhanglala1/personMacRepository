package apiTest.apitest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;

import apiTest.Utils.MapUtilss;

public class CorrelationUtils {

	static Map<String, Object> correlationMap = new HashMap<String, Object>();

	static Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");

	public static void add(String key, String value) {
		correlationMap.put(key, value);
	}

	public static void addFromJson(String jsonString, String correlation_regex) {

		if (!JSON.isValid(jsonString)) {

			return;

		}
		Map<String, Object> replaceMap = MapUtilss.coverStringToMap(correlation_regex);
		if (!MapUtilss.isEmpty(replaceMap)) {

			for (String key : replaceMap.keySet()) {

				String value = replaceMap.get(key).toString();

				correlationMap.put(key, JSONPath.read(jsonString, value));
				
				//System.out.println(correlationMap);

			}
			replaceMap.clear();
		}

	}

	public static String getCorrelationValue(String group) {

		Object valueObject = correlationMap.get(group);

		if (valueObject == null) {

			return "";
		}

		return valueObject.toString();

	}

	public static void repalce(TestCase testCase) {
		// 关联 excel 后 不仅仅只是处理url 还需要处理 参数 头部信息等
		
		testCase.setUrl(patternReplace(testCase.getUrl()));
		testCase.setParameters(patternReplace(testCase.getParameters()));;
		testCase.setHeaders(patternReplace(testCase.getHeaders()));;
//		String urlString = testCase.getUrl();
//		String parameters = testCase.getParameters();
//		String headers = testCase.getHeaders();
//
//		if (MapUtilss.isEmpty(urlString)) {
//
//			patternReplace(urlString);
//		}
//		testCase.setUrl(urlString);
//		if (MapUtilss.isEmpty(parameters)) {
//
//			patternReplace(parameters);
//		}
//		
//		if (MapUtilss.isEmpty(headers)) {
//
//			patternReplace(headers);
//		}

//    	Matcher m = pattern.matcher(url);
//    	while (m.find()) {
//			//System.out.println(m.group(0));
//			//System.out.println(m.group(1));
//    		url= url.replace(m.group(), getCorrelationValue(m.group(1)));
//		}

	}
	/**
	 * 根据正则替换逻辑
	 * @param string
	 * @return
	 */
	private static String patternReplace(String string) {
		if (!MapUtilss.isEmpty(string)) {
			Matcher m = pattern.matcher(string);
			while (m.find()) {
				// System.out.println(m.group(0));
				// System.out.println(m.group(1));
				string = string.replace(m.group(), getCorrelationValue(m.group(1)));	
			}
		}
		return string;

	}

	public static void main(String[] args) {
		String testString11 = "{\"code\":\"1\",\"msg\":\"登录成功\",\"uid\":\"0052dc59ee414313ad7d842881a7a22a\"}";
		add("id3", "testid3");
		addFromJson(testString11, "id=uid;id2=code");
		TestCase testCase = new TestCase();
		testCase.setUrl("https://www.baidu.com?pid=${id}&test=${id2}&test2=${id3}");
		testCase.setHeaders("test2=${id3}");
		testCase.setParameters("pid=${id}&test=${id2}");
		repalce(testCase);
		System.out.println(testCase);
		System.out.println(correlationMap);
		correlationMap.clear();
	}

}
