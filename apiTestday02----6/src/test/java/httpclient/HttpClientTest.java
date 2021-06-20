package httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {
	
	public static Map<String, Object> map;

	public static void main(String[] args) throws ClientProtocolException, IOException {

		String result=	doGet("https://www.baidu.com","token=11;user=123;");
		System.out.println(result);
		System.err.println("------------------------------");
		result = doPost("https://www.baidu.com",null,null);
		System.out.println(result);
		System.err.println("------------------------------");
		
		result = doPostJson("https://www.baidu.com",null,null);
		System.out.println(result);
		System.err.println("------------------------------");
		
	}
	
	public static String doGet(String urlString) throws ClientProtocolException, IOException {
		return doGet(urlString,null);
	}
	private static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}
	
	// header 例如 token=11;user=123;
	public static String doGet(String urlString ,String headerss) {
		
		//创建一个请求连接管理
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 请求
		HttpGet get = new HttpGet(urlString);
		
		if (!isEmpty(headerss)) {
			
//			String[] headers=headerss.split(";");
//			
//			for (String string : headers) {
//				
//				String[] headerString = string.split("=");
//				
//				get.addHeader(headerString[0],headerString[1]);
//			}
			//又是一种工具封装
			map = MapUtilss.coverStringToMap(headerss, ";");
			for (String key : map.keySet()) {
				get.addHeader(key,map.get(key).toString());
			}
			
		}
		// 获取返回对象
		CloseableHttpResponse resp;
		String result="";
		try {
			resp = httpClient.execute(get);
			//获取返回状态
			StatusLine statuString =  resp.getStatusLine();
			//得到结果
			HttpEntity entity= resp.getEntity();
			//结果转换
			result=EntityUtils.toString(entity,"utf-8");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//System.out.println(statuString);
		
		//System.out.println(result);
		
		return result;

		
		
	}
	
	
	public static String doPost(String url,String parameters,String headerss) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost post = new HttpPost(url);
		
		
		// post 数据处理
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//		formparams.add(new BasicNameValuePair("method", "loginMobile"));  
//		formparams.add(new BasicNameValuePair("loginname", "test1"));  
//		formparams.add(new BasicNameValuePair("loginpass", "test1"));  
//		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8); 
		
		if (!isEmpty(parameters)) {
			
//			String[] parameter_array= parameters.split("&");
//			
//			for (String string : parameter_array) {
//				
//				String[] parameter=string.split("=");
//				
//				formparams.add(new BasicNameValuePair(parameter[0], parameter[1]));
//			}
			// 有一种工具封装方法
			map = MapUtilss.coverStringToMap(parameters, "&");
			for (String key : map.keySet()) {
				formparams.add(new BasicNameValuePair(key, map.get(key).toString()));
			}
			//处理头部信息
			if (!isEmpty(headerss)) {
				
//				String[] headers=headerss.split(";");
//				
//				for (String string : headers) {
//					
//					String[] headerString = string.split("=");
//					
//					get.addHeader(headerString[0],headerString[1]);
//				}
				//又是一种工具封装
				map = MapUtilss.coverStringToMap(headerss, ";");
				for (String key : map.keySet()) {
					post.addHeader(key,map.get(key).toString());
				}
				
			}
			
			UrlEncodedFormEntity entity=	new UrlEncodedFormEntity(formparams,Consts.UTF_8);
			post.setEntity(entity);
			
		}
		// 获取返回对象
		CloseableHttpResponse resp=httpClient.execute(post);
		//获取返回状态
		StatusLine statuString= resp.getStatusLine();
		System.out.println(statuString);
		//结果
		HttpEntity httpEntity= resp.getEntity();
		//结果转换
		String result= EntityUtils.toString(httpEntity,"utf-8");
		
		return result;
		//System.out.println(result);
		
	}
	
public static String doPostJson(String url,String parameters,String headerss) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost postJson = new HttpPost(url);
		
		
		// post 数据处理
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//		formparams.add(new BasicNameValuePair("method", "loginMobile"));  
//		formparams.add(new BasicNameValuePair("loginname", "test1"));  
//		formparams.add(new BasicNameValuePair("loginpass", "test1"));  
//		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8); 
		
		if (!isEmpty(parameters)) {
			
//			String[] parameter_array= parameters.split("&");
//			
//			for (String string : parameter_array) {
//				
//				String[] parameter=string.split("=");
//				
//				formparams.add(new BasicNameValuePair(parameter[0], parameter[1]));
//			}
			// 有一种工具封装方法
			map = MapUtilss.coverStringToMap(parameters, "&");
			for (String key : map.keySet()) {
				formparams.add(new BasicNameValuePair(key, map.get(key).toString()));
			}
			
			//处理头部信息
			if (!isEmpty(headerss)) {
				
//				String[] headers=headerss.split(";");
//				
//				for (String string : headers) {
//					
//					String[] headerString = string.split("=");
//					
//					get.addHeader(headerString[0],headerString[1]);
//				}
				//又是一种工具封装
				map = MapUtilss.coverStringToMap(headerss, ";");
				for (String key : map.keySet()) {
					postJson.addHeader(key,map.get(key).toString());
				}
				
			}
			
			UrlEncodedFormEntity entity=	new UrlEncodedFormEntity(formparams,Consts.UTF_8);
			postJson.setEntity(entity);
			
		}
		// 获取返回对象
		CloseableHttpResponse resp=httpClient.execute(postJson);
		//获取返回状态
		StatusLine statuString= resp.getStatusLine();
		System.out.println(statuString);
		//结果
		HttpEntity httpEntity= resp.getEntity();
		//结果转换
		String result= EntityUtils.toString(httpEntity,"utf-8");
		
		return result;
		//System.out.println(result);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
