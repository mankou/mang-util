package mang.util.json.demo.namingStrategy;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.PascalCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


@JsonNaming(PascalCaseStrategy.class)
public class Request {
	private String appKey="717BF4F4BC2C4D7982CDD7E19B658AF3";
	private String appSecret="D9B98A9E3F5A4F9CACB359CD41CA3536";
	private String cardNo="1234567890";
	
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
}
