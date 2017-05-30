package mang.util.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class RestUtil {

	/**
	 * 客户端调用rest服务接口
	 * @param url 请求地址
	 * @param data 请求参数
	 * @return 服务端返回json格式字符串
	 */
	public static String load(String url, String data) {

		try {
			URL restURL = new URL(url);

			HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();

			conn.setRequestProperty("Accept", "application/json; charset=utf-8");
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			//conn.setAllowUserInteraction(false);
			PrintStream ps = new PrintStream(conn.getOutputStream(), false, "UTF-8");
//			PrintStream ps = new PrintStream(conn.getOutputStream());
			ps.print(data);
			ps.close();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line, resultStr = "";
			while (null != (line = bReader.readLine()))
			{
				resultStr += line;
			}

			bReader.close();
			return resultStr;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("run error");
		}
		

	}

	public static void main(String[] args) {
		String url="http://127.0.0.1/server/test/";
		
		try {

			RestUtil restUtil = new RestUtil();

			String resultString = restUtil.load(
					"",
					"abcd-测试数据");
			System.out.println("客户端："+resultString);
		} catch (Exception e) {
			System.out.print(e.getMessage());

		}

	}
}
