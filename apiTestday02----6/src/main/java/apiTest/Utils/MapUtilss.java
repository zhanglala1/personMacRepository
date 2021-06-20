package apiTest.Utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

public class MapUtilss {
	
	public static Map<String, Object> coverStringToMap(String string,String regx) {
		
		if(!isEmpty(string)) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			String[] header_array = string.split(regx);
			
			for (String line : header_array) {
				String[] header = line.split("=");
				if (header.length==2) {
					
					map.put(header[0], header[1]);
				}
			}
			
			return map;
		}
		return null;
	}
	
	public static Map<String, Object> coverStringToMap(String string) {
		
		return coverStringToMap(string,";");
		
		
	}
	
	
	
	
	public static boolean isEmpty(final CharSequence cs) {
		
		return cs == null || cs.length() == 0;
		
	}
	
	
	public static boolean isEmpty(Map<String , Object> map) {
		
		return map.isEmpty()&&map == null;
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		
		
		Map<String, Object> map = coverStringToMap("method=loginMobile&loginname=test1&loginpass=test1","&");
		
		System.out.println(map);
		
	}
	
	
	

}
