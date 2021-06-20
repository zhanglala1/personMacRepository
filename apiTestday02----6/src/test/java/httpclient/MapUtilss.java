package httpclient;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

public class MapUtilss {

	public static Map<String, Object> coverStringToMap(String string,String regx) {
		
		Map<String, Object> map = new HashedMap();
		
		if (!isEmpty(string)) {
			
			String[] parameter_array= string.split(regx);
			
			for (String string1 : parameter_array) {
				
				String[] parameter=string1.split("=");
				
				if (parameter.length == 2 ) {
					
					map.put(parameter[0], parameter[1]);
				}
				
				
			}
			return map;
		}
		return null;
	}
	
	
	public static Map<String, Object> coverStringToMap(String string ){
		return coverStringToMap(string,";");
	}
	

	private static boolean isEmpty(final CharSequence cs) {
		
		return cs == null || cs.length() == 0;
	}
	
	
	public static void main(String[] args) {
		
		
		System.out.println(coverStringToMap("token=11;user=123;", ";"));
		
	}

}
