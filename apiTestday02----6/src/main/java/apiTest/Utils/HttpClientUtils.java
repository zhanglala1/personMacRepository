package apiTest.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {

//	public static String doGet(String url, Map<String, String> headers) {
//		
//	}
	static boolean openProxy =false;
	static HttpHost proxy = new HttpHost("127.0.0.1", 8888);
	static RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
	
	public static String doGet(String url) {
		return doGet(url, null);
	}

	private static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	// header 例如 token=11;user=123;
	public static String doGet(String url, String headers) {
		// 创建一个请求连接管理
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 请求
		HttpGet get = new HttpGet(url);
		// 设置代理
		if(openProxy) {
			get.setConfig(requestConfig);
		}
//		HttpHost proxy = new HttpHost("127.0.0.1", 8888);
//		RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
//		get.setConfig(requestConfig);
		// 处理头部信息
		if (!isEmpty(headers)) {
			String[] header_array = headers.split(";");
			for (String line : header_array) {
				String[] header = line.split("=");
				get.addHeader(header[0], header[1]);
			}
//			get.addHeader("token", "1212");	
//			get.addHeader("user", "1212");	
		}
		// HttpGet("http://118.24.13.38:8080/goods/UserServlet?method=loginMobile&loginname=test1&loginpass=test1");
		// 获取返回对象
		String result = "";
		CloseableHttpResponse resp;

		try {
			resp = httpclient.execute(get);
			// http 状态 200 404 302 500
			StatusLine line = resp.getStatusLine();
			System.out.println(line);
			// 结果
			HttpEntity httpEntity = resp.getEntity();
			// 结果转换
			result = EntityUtils.toString(httpEntity, "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

//	public static String doPost(String url,String parameters, String headers) {
//		
//	}
	public static String doPost(String url, String parameters, String headers) {
		// 创建一个请求连接管理
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 请求
		HttpPost post = new HttpPost(url);
		// post 数据处理
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//		formparams.add(new BasicNameValuePair("method", "loginMobile"));  
//		formparams.add(new BasicNameValuePair("loginname", "test1"));  
//		formparams.add(new BasicNameValuePair("loginpass", "test1"));  
//		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);  
		if (!isEmpty(parameters)) {
			String[] parameter_array = parameters.split("&");
			for (String line : parameter_array) {
				String[] parameter = line.split("=");
				formparams.add(new BasicNameValuePair(parameter[0], parameter[1]));
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
			post.setEntity(entity);
		}

		// 设置代理
//		HttpHost proxy = new HttpHost("127.0.0.1", 8888);
//		RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
		if(openProxy) {
			post.setConfig(requestConfig);
		}
		// 处理头部信息
		if (!isEmpty(headers)) {
			String[] header_array = headers.split(";");
			for (String line : header_array) {
				String[] header = line.split("=");
				
				post.addHeader(header[0], header[1]);
			}
//			get.addHeader("token", "1212");	
//			get.addHeader("user", "1212");	
		}
		// HttpGet("http://118.24.13.38:8080/goods/UserServlet?method=loginMobile&loginname=test1&loginpass=test1");
		// 获取返回对象
		String result = "";
		CloseableHttpResponse resp;

		try {
			resp = httpclient.execute(post);
			// http 状态 200 404 302 500
			StatusLine line = resp.getStatusLine();
			System.out.println(line);
			// 结果
			HttpEntity httpEntity = resp.getEntity();
			// 结果转换
			if(line.getStatusCode()==200) {
				
				result = EntityUtils.toString(httpEntity, "utf-8");
			}else {
				result ="返回失败"+line.getStatusCode();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static String doPostJson(String url, String jsonParam, Map<String, Object> headers) {
		// 创建一个请求连接管理
				CloseableHttpClient httpclient = HttpClients.createDefault();
				// 请求
				HttpPost postJson = new HttpPost(url);
				// postJson  数据处理
				//解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");  
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				postJson.setEntity(entity);
				
				// 设置代理
//				HttpHost proxy = new HttpHost("127.0.0.1", 8888);
//				RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
				if(openProxy) {
					postJson.setConfig(requestConfig);
				}
				// 处理头部信息
				
				if(headers!=null&&!headers.isEmpty()) {
					
					Set<String> headersSet= headers.keySet();
					for (String key : headersSet) {
						//方式一: Object 转换成 String
						String value= String.valueOf(headers.get(key));
						//方式二: Object 转换成 String
						//String value=headers.get(key).toString();
						postJson.addHeader(key, value);
					}
				}
				// 处理头部信息
//				if (!isEmpty(headers)) {
//					String[] header_array = headers.split(";");
//					for (String line : header_array) {
//						String[] header = line.split("=");
//						postJson.addHeader(header[0], header[1]);
//					}
////					get.addHeader("token", "1212");	
////					get.addHeader("user", "1212");	
//				}
				// HttpGet("http://118.24.13.38:8080/goods/UserServlet?method=loginMobile&loginname=test1&loginpass=test1");
				// 获取返回对象
				String result = "";
				CloseableHttpResponse resp;

				try {
					resp = httpclient.execute(postJson);
					// http 状态 200 404 302 500
					StatusLine line = resp.getStatusLine();
					System.out.println(line);
					// 结果
					HttpEntity httpEntity = resp.getEntity();
					// 结果转换
					if(line.getStatusCode()==200) {
						
						result = EntityUtils.toString(httpEntity, "utf-8");
					}else {
						result ="返回失败"+line.getStatusCode();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return result;
		
	}
	
	public static String doPostJson(String url, String jsonParam, String headers) {
		// 创建一个请求连接管理
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 请求
		HttpPost postJson = new HttpPost(url);
		// postJson  数据处理
		//解决中文乱码问题
		StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");  
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		postJson.setEntity(entity);
		
		// 设置代理
//		HttpHost proxy = new HttpHost("127.0.0.1", 8888);
//		RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
		if(openProxy) {
			postJson.setConfig(requestConfig);
		}
		// 处理头部信息
		if (!headers.isEmpty()) {
			String[] header_array = headers.split(";");
			for (String line : header_array) {
				String[] header = line.split("=");
				
				postJson.addHeader(header[0], header[1]);
			}
//			get.addHeader("token", "1212");	
//			get.addHeader("user", "1212");	
		}
		// HttpGet("http://118.24.13.38:8080/goods/UserServlet?method=loginMobile&loginname=test1&loginpass=test1");
		// 获取返回对象
		String result = "";
		CloseableHttpResponse resp;

		try {
			resp = httpclient.execute(postJson);
			// http 状态 200 404 302 500
			StatusLine line = resp.getStatusLine();
			System.out.println(line);
			// 结果
			HttpEntity httpEntity = resp.getEntity();
			// 结果转换
			if(line.getStatusCode()==200) {
				
				result = EntityUtils.toString(httpEntity, "utf-8");
			}else {
				result ="返回失败"+line.getStatusCode();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		doPost("http://118.24.13.38:8080/goods/UserServlet",
				"method=loginMobile&loginname=test1&loginpass=test1&p1=test1&p2=test1", "user=123;token=test");
		
		//doPostJson("http://118.24.13.38:8080/goods/json2", "{\"count\":3}", "token=61b3590090982a0185dda9d3bd793b46;");
		doPostJson("http://118.24.13.38:8080/goods/json2", "{\"count\":3}", MapUtilss.coverStringToMap("token=61b3590090982a0185dda9d3bd793b46;", ";"));
	}
}
