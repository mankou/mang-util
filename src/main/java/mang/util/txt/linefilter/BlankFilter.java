package mang.util.txt.linefilter;

import org.apache.commons.lang3.StringUtils;

public class BlankFilter extends SimpleAbstractLineFilter {

	@Override
	public boolean isConform(String line) {
		if(StringUtils.isBlank(line)){
			return false;
		}
		return true;
	}


}
