package mang.util.demo.http;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import mang.util.http.HttpUtil;


/**
 * post请求-注意controller的写法是@RequestParam String name,String value 其传入2个String类型的字符串
 * */
public class HttpUtilPost {

	public static void main(String[] args) {
		try {
			String url = "http://127.0.0.1:8083/index/post.do/";
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("name", "vip"));
			nvps.add(new BasicNameValuePair("value", "testValue"));
			UrlEncodedFormEntity httpEntiry = new UrlEncodedFormEntity(nvps);
			String result = HttpUtil.post(url, httpEntiry);
			System.out.println(result);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
