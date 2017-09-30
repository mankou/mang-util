package mang.util.demo.http;

import java.io.UnsupportedEncodingException;
import org.apache.http.entity.StringEntity;

import mang.util.http.HttpUtil;


/**
 * 演示post请求  注controller的写法是@RequestBody String name 所以这里的请求有entity有些不一样
 * */
public class HttpUtilPost2 {

	public static void main(String[] args) {
		try {
			String url2 = "http://127.0.0.1:8083/index/post2.do/";
			StringEntity stringEntity=new StringEntity("name");
			String result2 = HttpUtil.post(url2, stringEntity);
			System.out.println(result2);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
