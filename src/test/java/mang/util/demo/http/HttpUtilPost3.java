package mang.util.demo.http;


import java.util.Date;

import mang.util.http.HttpUtil;
import mang.util.json.JsonUtil;

/**
 * 演示post传json格式字符串
 * */
public class HttpUtilPost3 {

	public static void main(String[] args) {
		try {
			User user = new User();
			user.setDate(new Date());
			user.setName("hello");
			user.setId(1);

			String userstr = JsonUtil.obj2String(user);

			String url = "http://127.0.0.1:8083/index/post3.do/";
			String result = HttpUtil.postJson(url, userstr);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
