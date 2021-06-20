package fastjson;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class FastJsonTest {
	
	public static void main(String[] args) {
		    String jsonString1 = "{\"param5\":\"value5\",\"param3\":\"value3\",\"param4\":\"value4\",\"param1\":\"value1\",\"param2\":\"value2\"}"; 
		    
	        JSONObject object = (JSONObject) JSON.parse(jsonString1);
	        System.out.println(object);
	        
	        System.out.println(object.get("param5"));
	        
	        String jsonString = "{\"uid\":\"value5\",\"loginname\":\"value3\",\"loginpass\":\"value4\",\"param1\":\"value1\",\"param2\":\"value2\"}";
	        
	        JSONObject object2 = (JSONObject) JSON.parse(jsonString);
	        System.out.println(object2);
	        System.out.println(object2.get("uid"));
	        
	        
	        
//	        String jsonString2 = "[{\"param5\":\"value5\",\"param3\":\"value3\",\"param4\":\"value4\",\"param1\":\"value1\",\"param2\":\"value2\"},"
//	        		+ "{\"p1\":\"v1\",\"p2\":\"v2\",\"p3\":\"v3\",\"p4\":\"v4\",\"p5\":\"v5\"}]";  
//	        JSONArray arry=  JSON.parseArray(jsonString2);
//	        for (int i = 0; i < arry.size(); i++) {
//	        	JSONObject obj = (JSONObject) arry.get(i);
//	        	System.out.println(obj);
//			}
	        System.out.println("----------------");
	        String jsonString2 = "{\"code\":\"1\",\"data\":[{\"param5\":\"value5\",\"param3\":\"value3\",\"param4\":\"value4\",\"param1\":\"value1\",\"param2\":\"value2\"},,{\"param5\":\"value5\",\"param3\":\"value3\",\"param4\":\"value4\",\"param1\":\"value1\",\"param2\":\"value2\"},{\"p1\":\"v1\",\"p2\":\"v2\",\"p3\":\"v3\",\"p4\":\"v4\",\"p5\":\"v5\"}]}";
			JSONObject object3 = (JSONObject) JSON.parse(jsonString2);
			System.out.println(object3.get("code"));
//
			JSONArray jsonArray = object3.getJSONArray("data");
			for (Object object4 : jsonArray) {
//				System.out.println(object4.getClass());
//				System.out.println(object4);
				
				JSONObject jsonObject = (JSONObject) object4;
				System.out.println(jsonObject.getClass());
				System.out.println(jsonObject.get("param5"));
			}
			
	        //解析有规律的
	        String jsonString3 = "[{\"age\":12,\"date\":1465475917155,\"name\":\"s1\"},"
	        		+ "{\"age\":12,\"date\":1465475917175,\"name\":\"s2\"},"
	        		+ "{\"age\":12,\"date\":1465475917175,\"name\":\"s3\"},"
	        		+ "{\"age\":12,\"date\":1465475917175,\"name\":\"s4\"},"
	        		+ "{\"age\":12,\"date\":1465475917175,\"name\":\"s5\"},"
	        		+ "{\"age\":12,\"date\":1465475917175,\"name\":\"s6\"}]";
	        
	        
	        List<Student>  studentsList=JSON.parseArray(jsonString3, Student.class);
	        
	        for (Student student : studentsList) {
				System.out.println(student.getClass());
				System.out.println(student);
				System.out.println(JSON.toJSONString(student));
			}
	        
	        //String jsonString4 = JSON.toJSONString(studentsList);
	        // 对时间格式化一下
	        String detaformat = JSON.toJSONStringWithDateFormat(studentsList, "yyyy-MM-dd  HH:mm:ss");
	        System.out.println(detaformat);
	        //如果没有对象
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("zhangsan", "test");
	        jsonObject.put("age", "100");
	        
	        System.out.println(jsonObject.toJSONString());
	        
	        
	        
//	        List<Student> studentList = JSON.parseArray(jsonString3,Student.class); 
//	        for (Student student : studentList) {
//				System.out.println(student);
//				System.out.println(JSON.toJSONString(student));
//			}
	        
            //对象转json
//	        String jsonString = JSON.toJSONString(studentList);
//	        System.out.println("javabean to json"+jsonString);
//	       String dateFormat =  JSON.toJSONStringWithDateFormat(studentList, "yyyy-MM-dd HH:mm:ss");
//	       System.out.println("dataformat "+ dateFormat);
	        
	       //如果没有对象
//	        JSONObject jsonObject = new JSONObject();
//	        jsonObject.put("zhangsan", "test");
//	        jsonObject.put("age", "100");
//	        System.out.println(jsonObject.toJSONString());
	        
	}

}
