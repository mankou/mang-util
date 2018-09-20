package mang.util.common;

import org.junit.Assert;
import org.junit.Test;

import mang.util.test.entity.ValidatorEntityNotBlank;
import mang.util.test.entity.ValidatorEntityNotEmpty;
import mang.util.test.entity.ValidatorEntityNotNull;

public class BeanValidatorTest {
	
	@Test
	public void checkNotNull(){
		ValidatorEntityNotNull entity=new ValidatorEntityNotNull();
		boolean isSuccess=false;
		try {
			BeanValidator.check(entity);
			isSuccess=true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		Assert.assertFalse(isSuccess);
	}
	
	@Test
	public void checkNotEmpty(){
		ValidatorEntityNotEmpty entity=new ValidatorEntityNotEmpty();
		entity.setUserCode("");
		boolean isSuccess=false;
		try {
			BeanValidator.check(entity);
			isSuccess=true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		Assert.assertFalse(isSuccess);
	}
	
	@Test
	public void checkNotBlank(){
		ValidatorEntityNotBlank entity=new ValidatorEntityNotBlank();
		entity.setUserCode("   ");
		boolean isSuccess=false;
		try {
			BeanValidator.check(entity);
			isSuccess=true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		Assert.assertFalse(isSuccess);
	}

}
