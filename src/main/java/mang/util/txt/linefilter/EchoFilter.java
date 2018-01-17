package mang.util.txt.linefilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoFilter extends SimpleAbstractLineFilter {
	private static final Logger log = LoggerFactory.getLogger(EchoFilter.class);
	
	@Override
	public boolean isConform(String line) {
		int lineNum=this.getContext().getCurrentLineNum();
		log.info("当前行号:{},内容:{}",new Object[]{lineNum,line});
		return true;
	}
}
