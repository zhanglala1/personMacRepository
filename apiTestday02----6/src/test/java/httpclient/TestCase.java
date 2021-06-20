package httpclient;

import com.github.crab2died.annotation.ExcelField;

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
	
	@ExcelField(title = "是否开启",readConverter = IsRunConvert.class)
	private boolean run;
	
	@ExcelField(title = "用例名称")
	private String caseName; //驼峰
	
	@ExcelField(title = "类型")
	private String type; 
	
	@ExcelField(title = "地址")
	private String url; 
	
	@ExcelField(title = "参数")
	private String parameters; 
	

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
		return "TestCase [run=" + run + ", caseName=" + caseName + ", type=" + type + ", url=" + url + ", parameters="
				+ parameters + ", headers=" + headers + "]";
	} 

}
