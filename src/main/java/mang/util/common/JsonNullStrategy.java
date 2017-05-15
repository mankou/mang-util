package mang.util.common;

/**
 * jackJson处理空字符串的策略
 * */
public enum JsonNullStrategy {
	NON_DEFAULT("NON_DEFAULT"),
	NON_EMPTY("NON_EMPTY"),
	NON_NULL("NON_EMPTY"),
	EMPTY_STRING("EMPTY_STRING");

    private String value;

    private JsonNullStrategy(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
