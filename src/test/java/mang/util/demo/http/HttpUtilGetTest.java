package mang.util.demo.http;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.client.utils.URIBuilder;

import mang.util.http.HttpUtil;

public class HttpUtilGetTest {

	public static void main(String[] args) {
		
		try {
			//测试1 演示如何拼接get请求
			URI uri = new URIBuilder().setScheme("http").setHost("127.0.0.1:8083").setPath("/index/get")
					.setParameter("name", "xx").build();
			String result = HttpUtil.get(uri.toString());
			System.out.println(result);
			
			//测试2 直接在string中把url写好也行
			String url2="http://127.0.0.1:8083/index/get?name=xx";
			String result2=HttpUtil.get(url2);
			System.out.println(result2);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}

}
