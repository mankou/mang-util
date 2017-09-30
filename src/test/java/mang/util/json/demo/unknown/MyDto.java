package mang.util.json.demo.unknown;

public class MyDto {
	private String stringValue="1";
	private int intValue=1;
	private boolean booleanValue=true;
//	private String stringValue2="2";

	public MyDto() {
		super();
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public boolean isBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	@Override
	public String toString() {
		return "MyDto [stringValue=" + stringValue + ", intValue=" + intValue + ", booleanValue=" + booleanValue + "]";
	}

//	public String getStringValue2() {
//		return stringValue2;
//	}
//
//	public void setStringValue2(String stringValue2) {
//		this.stringValue2 = stringValue2;
//	}
	
	
	
	
	
}
