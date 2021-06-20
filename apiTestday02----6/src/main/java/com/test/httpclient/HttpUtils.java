package com.test.httpclient;

import java.io.IOException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtils {
	
	public static  String doGet(String url) {
		
		return doGet(url,null);
	}
	
	public static  String doGet(String url,String headerss) {
				
				//headers token=11;user=123;
				//创建一个请求连接管理
				CloseableHttpClient httpClient = HttpClients.createDefault();
				//请求
				HttpGet get = new HttpGet(url);
				//设置代理
//				HttpHost proxyHost= new HttpHost("192.168.31.79", 8887);
//				RequestConfig rf = RequestConfig.custom().setProxy(proxyHost).build();
//				get.setConfig(rf);
				
				if (headerss!=null) {
					//不能把 header 写死,  做工具要写成变量
					//get.addHeader("121212", "12121212");
					String[] headers = headerss.split(";");
					for (String header : headers) {
						String[] header1 =header.split("=");
						get.addHeader(header1[0], header1[1]);
					}
					
					
				}
				//获取返回对象
				CloseableHttpResponse resp;
				String  result = "";
				try {
					resp = httpClient.execute(get);
					//http 状态 resp.getStatusLine()
					System.out.println( resp.getStatusLine());
					//结果
					HttpEntity httpEntity =  resp.getEntity();
					//结果转换
					result= EntityUtils.toString(httpEntity,"utf-8");
					//System.out.println(result);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return result;
		
	}
	
	

}
