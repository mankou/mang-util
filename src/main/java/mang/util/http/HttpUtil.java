package mang.util.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
public class HttpUtil {
	
	
	/**
	 * get请求
	 * @param url 请求的url地址
	 * @return String
	 * */
	public static String get(String url){
		BasicResponseHandler basicResponseHandler=new BasicResponseHandler();
		String responseBody=get(url, basicResponseHandler);
		return responseBody;
	}
	
	
	/**
	 * get请求 可自己传入responseHandler进行处理
	 * @param url 请求的url地址
	 * @param responseHandler http处理类
	 * @return String
	 * */
	public static String get(String url,ResponseHandler responseHandler){
		 CloseableHttpClient httpclient = HttpClients.createDefault();
	        try {
	            HttpGet httpget = new HttpGet(url);
	            System.out.println("Executing request " + httpget.getRequestLine());
	            String responseBody = (String) httpclient.execute(httpget, responseHandler);
	            System.out.println("----------------------------------------");
	            System.out.println(responseBody);
	            return responseBody;
	        }  catch (Exception e) {
	        	e.printStackTrace();
	        	 throw new RuntimeException(e.getMessage());
			} finally {
	            try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	}
	
	/**
	 * post请求 传json格式字符串.
	 * @param url 请求url
	 * @param jsonStr json格式字符串
	 * @return String
	 * */
	public static String postJson(String url,String jsonStr){
		BasicResponseHandler basicResponseHandler=new BasicResponseHandler();
		String result;
		try {
			//因在post请求中传json格式的字符串比较常见 所以这里设置成默认的
			StringEntity stringEntity=new StringEntity(jsonStr);
			stringEntity.setContentType("application/json");
			stringEntity.setContentEncoding("UTF-8");
			result=post(url, basicResponseHandler,stringEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}
	
	
	/**
	 * post请求
	 * @param url post请求的url
	 * @param httpEntity post请求的参数
	 * @return String
	 * 
	 * */
	public static String post(String url,HttpEntity httpEntity){
		BasicResponseHandler basicResponseHandler=new BasicResponseHandler();
		String result=post(url, basicResponseHandler,httpEntity);
		return result;
	}
	
	/**
	 * post请求
	 * @param url post请求的url
	 * @param responseHandler 可传入自定义的responseHandler
	 * @param httpEntity post请求的参数
	 * @return String
	 * */
	public static String post(String url,ResponseHandler responseHandler,HttpEntity httpEntity){
		   CloseableHttpClient httpclient = HttpClients.createDefault();
	        try {
	            HttpPost httpPost = new HttpPost(url);
	            if(httpEntity!=null){
	            	httpPost.setEntity(httpEntity);
	            }
	           String responseBody =  (String)httpclient.execute(httpPost,responseHandler);
	           return responseBody;
	           
	        } catch (HttpResponseException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getStatusCode()+e.getMessage());
			}  catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			} finally {
	            try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		
	}
}
