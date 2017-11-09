package mang.util.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;



/**
 * bean处理工具类.
 * <p>用于处理拷备的工具类，如从一个对象的相同属性拷备到另一个对象 这里使用了spring的BeanUtils.copyProperties
 * @author mang
 * @version 
 * create:2017-06-19 17:21:13
 * modify:2017-06-19 17:21:16
 * */
public class BeanUtil{
	
	/**
	 * 从源list拷备相同属性到目标list
	 * 
	 * @param lis 源list
	 * @param targetClazz 目标类 
	 * @return list
	 * */
	public static List copyBeans(List lis,Class targetClazz){
		List returnLis=new ArrayList<Object>();
		for(Object obj:lis){
			try {
				Object targetObj=targetClazz.newInstance();
				BeanUtils.copyProperties(obj, targetObj);
				returnLis.add(targetObj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnLis;
	}
}  