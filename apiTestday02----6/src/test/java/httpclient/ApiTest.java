package httpclient;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.github.crab2died.ExcelUtils;
import com.github.crab2died.exceptions.Excel4JException;

public class ApiTest {
	
	
	
	public static void main(String[] args) throws InvalidFormatException, Excel4JException, IOException {
		
		String path = System.getProperty("user.dir")+File.separator+"data"+File.separator+"apitest.xlsx";
		
		List<TestCase> list= ExcelUtils.getInstance().readExcel2Objects(path, TestCase.class);
		
		for (TestCase testCase : list) {
			
			if(testCase.isRun()) {
				
				if("get".equalsIgnoreCase(testCase.getType())) {
					
				//String result =	HttpClientTest.doGet(testCase.getUrl(),testCase.getHeaders());
				String result =	HttpClientTest.doGet(testCase.getUrl());
				
				System.out.println(result);
					
				}else if ("post".equalsIgnoreCase(testCase.getType())) {
					//未完
					System.out.println("------------");
				}else {
					System.out.println("尚未支持---哈哈哈哈");
				}
				
			}
			
		}
		
		
	}

}
