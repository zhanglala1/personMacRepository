package apiTest.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.map.HashedMap;

import com.alibaba.fastjson.JSONPath;

import apiTest.Utils.MapUtilss;
import apiTest.jsonPath.JsonPath;

// 参考教程
//https://www.runoob.com/java/java-regular-expressions.html 
public class RegexMatches
{
    public static void main( String args[] ){
 
      // 按指定模式在字符串查找
//      String line = "This order was placed for QT3000! OK?";
//      String pattern = "(\\D*)(\\d+)(.*)";
// 
//      // 创建 Pattern 对象
//      Pattern r = Pattern.compile(pattern);
// 
//      // 现在创建 matcher 对象
//      Matcher m = r.matcher(line);
//      if (m.find( )) {
//         System.out.println("group: " + m.group() );
//         System.out.println("group1: " + m.group(1) );
//         System.out.println("group2: " + m.group(2) );
//         System.out.println("group3: " + m.group(3) ); 
//      } else {
//         System.out.println("NO MATCH");
//      }
    	String testString11 = "{\"code\":\"1\",\"msg\":\"登录成功\",\"uid\":\"0052dc59ee414313ad7d842881a7a22a\"}";
    	String testString2 = "{\"code\":\"1\",\"msg\":\"登录成功\",\"data\":[{\"uid\":\"0052dc59ee414313ad7d842881a7a22a\"},{\"uid\":\"0052dc59ee414313ad7d842881a7a22b\"}]}";
    	
    	
    	System.out.println(JSONPath.read(testString2, "..uid"));
    	// 数据提取
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	Map<String, Object> replaceMap=MapUtilss.coverStringToMap("id=uid;id2=code");
    	
    	for (String key : replaceMap.keySet()) {
			String value=replaceMap.get(key).toString();
			System.out.println(value);
			Object json1 =   JSONPath.read(testString11, value);
			
			
			System.out.println(json1);
			map.put(key, json1);
			
		}
    	System.out.println(map);
    	
    	
    	
    	//Map<String, Object> map = new HashMap<String, Object>();
    	//map.put("id", "111");
    	//替换
    	String string ="https://www.baidu.com?pid=${id}&test=${id2}";
    	Pattern r = Pattern.compile("\\$\\{(.+?)\\}");
    	Matcher m = r.matcher(string);
    	while (m.find()) {
			//System.out.println(m.group(0));
			//System.out.println(m.group(1));
			string= string.replace(m.group(), (CharSequence) map.get(m.group(1).toString()));
		}
    	
    	System.out.println(string);
    	
    	
    	//{"code":"1",""msg":登录成功","uid":"0052dc59ee414313ad7d842881a7a22a"}
//    	String line1 ="{\"msg\":登录成功\",\"uid\":\"0052dc59ee414313ad7d842881a7a22a\",\"code\":\"1\"}";
//    	// 创建 Pattern 对象
//    	Pattern r1 = Pattern.compile("\"uid\":\"(.+?)\",\"code\":\"(.+?)\"");
//    	// 现在创建 matcher 对象
//    	Matcher m2 = r1.matcher(line1);
//    	while (m.find()) {
//			System.out.println(m2.group(1));
//			System.out.println(m2.group(2));
//		}
    	
    	
    	
    	
    	
    	
    	
      
      
      
   }
}