package apiTest.apitest;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.github.crab2died.ExcelUtils;

import apiTest.Utils.HttpClientUtils;
import apiTest.Utils.MapUtilss;

public class ApiTest {

	public static void main(String[] args) {
		String path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "apitest-3.xlsx";
		// 反射
		try {
			List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(path, TestCase.class);
			for (TestCase testCase : list) {
				// 是否开启
				if (testCase.isRun()) {
					//匹配完成后 重新赋值
					CorrelationUtils.repalce(testCase);
					String reulString = "";
					if ("get".equalsIgnoreCase(testCase.getType())) {
						reulString = HttpClientUtils.doGet(testCase.getUrl(), testCase.getHeaders());
						
//							System.out.println(reulString);
					} else if ("post".equalsIgnoreCase(testCase.getType())) {
						// 有一个类型 post form数据
						reulString = HttpClientUtils.doPost(testCase.getUrl(), testCase.getParameters(),
								testCase.getHeaders());
					} else if ("postJson".equalsIgnoreCase(testCase.getType())) {
//						reulString = HttpClientUtils.doPostJson(testCase.getUrl(), testCase.getParameters(),
//								MapUtilss.coverStringToMap(testCase.getHeaders()));
						reulString = HttpClientUtils.doPostJson(testCase.getUrl(), testCase.getParameters(),
								testCase.getHeaders());
					} else {
						System.out.println("尚未支持");
					}
					//关联    //处理结果
					CorrelationUtils.addFromJson(reulString, testCase.getCorrelation());
					System.out.println("---------"+reulString);
				}
			}
			Long time = new Date().getTime();
			ExcelUtils.getInstance().exportObjects2Excel(list,TestCase.class,"result_"+time+".xlsx");
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
