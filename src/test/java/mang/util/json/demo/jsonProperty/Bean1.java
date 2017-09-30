package mang.util.json.demo.jsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Bean1 {
	@JsonProperty
    public long MsgID=123L;


    @JsonProperty
    public String GroupID="234";

    @JsonIgnore
	public long getMsgID() {
		return MsgID;
	}

    
	public void setMsgID(long msgID) {
		MsgID = msgID;
	}

	@JsonIgnore
	public String getGroupID() {
		return GroupID;
	}


	public void setGroupID(String groupID) {
		GroupID = groupID;
	}
    
    
}
