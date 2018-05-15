package mang.util.common;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class RandomUtilTest {

	@Test
	public void getRandomInt(){
		int random1=RandomUtil.getRandomInt(1);
		assertArrayEquals(new int[]{0}, new int[]{random1});
		
		int random2=RandomUtil.getRandomInt(0);
		assertArrayEquals(new int[]{0}, new int[]{random2});
		
		int random3=RandomUtil.getRandomInt(8);
		if(random3<8){
			
		}else{
			throw new RuntimeException("random error");
		}
	}
}
