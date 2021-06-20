package com.test.httpclient;

import java.io.IOException;

import org.apache.commons.lang3.event.EventUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientGet {
	
	
	public static void main(String[] args) {
		
//		//创建一个请求连接管理
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		//请求
//		HttpGet post = new HttpGet("https://www.baidu.com");
//		//获取返回对象
//		CloseableHttpResponse resp = httpClient.execute(post);
//		//http 状态 resp.getStatusLine()
//		System.out.println( resp.getStatusLine());
//		//结果
//		HttpEntity httpEntity =  resp.getEntity();
//		//结果转换
//		String result = EntityUtils.toString(httpEntity);
//		System.out.println(result);
//		
		//String string = HttpUtils.doGet("https://www.baidu.com");
		
		
		String string = HttpUtils.doGet("https://www.baidu.com");
		System.out.println(string);
		
		String string1 = HttpUtils.doGet("https://www.baidu.com","token=123;user=123");
		
		System.out.println(string1);
		
	}
	
	
	
	
	
	
	
	
	
	
	

}


