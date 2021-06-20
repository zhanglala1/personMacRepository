package apiTest.apitest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.github.crab2died.ExcelUtils;
import com.github.crab2died.annotation.ExcelField;
import com.github.crab2died.exceptions.Excel4JException;

/**
 * excel4j 对象
 * @author pc
 *
 */
public class TestCase {
	

	@ExcelField(title = "头部")
	private String headers;
	
//	@ExcelField(title = "是否开启")
//	private String run;
	
	@ExcelField(title = "是否开启", readConverter=IsRunConvert.class)
	private boolean run;
	
	@ExcelField(title = "用例名称")
	private String caseName; //驼峰
	
	@ExcelField(title = "类型")
	private String type; 
	
	@ExcelField(title = "地址")
	private String url; 
	
	@ExcelField(title = "参数",readConverter = ParamtersConvert.class)
	private String parameters; 
	
	@ExcelField(title = "关联")
	private String correlation; 
	
	public String getCorrelation() {
		return correlation;
	}

	public void setCorrelation(String correlation) {
		this.correlation = correlation;
	}

	

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	@Override
	public String toString() {
		return "TestCase [headers=" + headers + ", run=" + run + ", caseName=" + caseName + ", type=" + type + ", url="
				+ url + ", parameters=" + parameters + ", correlation=" + correlation + "]";
	}

	public static void main(String[] args) throws InvalidFormatException, Excel4JException, IOException {
		String pathString = System.getProperty("user.dir")+File.separator+"data"+File.separator+"apitest.xlsx";
		
		List<TestCase>  list = ExcelUtils.getInstance().readExcel2Objects(pathString, TestCase.class);
		for (TestCase testCase : list) {
			System.out.println(testCase);
		}
		
		
	}

}
