package mang.util.txt.linefilter;

import org.apache.commons.lang3.StringUtils;

public class BlankFilter implements LineFilter {

	@Override
	public boolean isFilter(String line) {
		if(StringUtils.isBlank(line)){
			return true;
		}
		return false;
	}

}
