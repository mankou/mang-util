package mang.util.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;



/**
 * 拷备工具类.
 * <p>用于处理拷备的工具类，如从一个对象的相同属性拷备到另一个对象
 * @author mang
 * @version 
 * create:2015-10-9 17:33:17
 * modify:2015-10-9 17:33:20
 * */
public class CopyUtil{  
	/**
	 * 默认定义的不拷备的属性
	 */
	private static String[] defaultExcludeField = new String[]{"id","serialVersionUID"};
	
	/**
	 * 复制两个对象相同的属性 在拷备进历史时特别有用.
	 * <p>即如果两个对象属性一样,则复制 
	 * <br>代码参考自"java两个类之间copy部分属性" http://blog.csdn.net/java_freshman01/article/details/7198978
	 * @param source 源
	 * @param target 目标
	 * @param excludeField 需要排除的属性 如果没有可写null 默认会把id排除
	 * */
	public static void copyProperties(Object source, Object target,List<String> excludeField) {

		String fileName, str, getName, setName;
		List fields = new ArrayList();
		Method getMethod = null;
		Method setMethod = null;
		Class c1 = source.getClass();
		Class c2 = target.getClass();
		try {
			//找出两个对象相同的属性
			fields = findSameFiled(source,target);
			
			//去除不需要拷备的属性
			if(excludeField==null){ //如果传空也支持
				excludeField = new ArrayList<String>();
			}
			fields = removeFiled(fields, excludeField);
			
			//TODO 用field.set()的方式设置 不用set/get方法了
			if (null != fields && fields.size() > 0) {
				
				for (int i = 0; i < fields.size(); i++) {
					// 获取属性名称
					Field f = (Field) fields.get(i);
					fileName = f.getName();
					// 属性名第一个字母大写
					str = fileName.substring(0, 1).toUpperCase();
					// 拼凑getXXX和setXXX方法名
					getName = "get" + str + fileName.substring(1);
					setName = "set" + str + fileName.substring(1);
					// 获取get、set方法
					getMethod = c1.getMethod(getName, new Class[] {});
					setMethod = c2.getMethod(setName,
							new Class[] { f.getType() });

					// 获取属性值
					Object o = getMethod.invoke(source, new Object[] {});
					System.out.println(fileName + " : " + o);
					// 将属性值放入另一个对象中对应的属性
					if (null != o) {
						
							System.out.println("o2.setMethod = " + setMethod);
							setMethod.invoke(target, new Object[] { o });
						
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 找出两个类中相同的属性.
	 * 
	 */
	private static List<Field> findSameFiled(Object source, Object target){
		List<Field> fields = new ArrayList<Field>();
		Class c1 = source.getClass();
		Class c2 = target.getClass();

		Field[] fs1 = c1.getDeclaredFields();
		Field[] fs2 = c2.getDeclaredFields();
		// 两个类属性比较剔除不相同的属性，只留下相同的属性
		for (int i = 0; i < fs2.length; i++) {
			for (int j = 0; j < fs1.length; j++) {
				if (fs1[j].getName().equals(fs2[i].getName())) {
					fields.add(fs1[j]);
					break;
				}
			}
		}
		return fields;
	}
	
	/**
	 * 去除需要排除的属性
	 */
	private static List<Field> removeFiled(List<Field> fields,List<String> excludeField){
		//先把预先定义的 需要排除的属性加到exclude列表中来
		for(int i=0;i<defaultExcludeField.length;i++){
			excludeField.add(defaultExcludeField[i]);
		}
		
		List<Field> removeList = new ArrayList<Field>();
		for(int i=0;i<excludeField.size();i++){
			String name = excludeField.get(i);
			for(int j=0;j<fields.size();j++){
				Field field = (Field) fields.get(j);
				if(field.getName().equals(name)){
					removeList.add(fields.get(j));
					break;
				}
			}
		}
		fields.removeAll(removeList);
		
		return fields;
	}
	
	/**
	 * 深度复制.
	 * <p>如把List<BuPos> srcList复制到List<BuPos> destList。 深度复制后srcList中的属性改变也不会影响到destList
	 * <br>摘自：java中ArrayList深拷贝有关问题 http://rogerfederer.iteye.com/blog/1748747
	 * */
	 public static <T> List<T> deepCopy(List<T> src) {  
	        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
	        ObjectOutputStream out;
			try {
				out = new ObjectOutputStream(byteOut);
				out.writeObject(src);  
			    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());  
			    ObjectInputStream in = new ObjectInputStream(byteIn);  
			    @SuppressWarnings("unchecked")  
			     List<T> dest = (List<T>) in.readObject();  
			    return dest;  
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null; 
	    }  
	 
	/**
	 * 深度复制一个对象.
	 * 注:需要尝试复制的对象须实现implements Serializable 接口 否则程序运行时会报错
	 * 摘自 JAVA对象的深度克隆  http://blog.csdn.net/xiaolang85/article/details/7386983
	 * */
	public static Object deepCopy(Object src) {
		try{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(src);
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
					bos.toByteArray()));
			return ois.readObject();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 将src中指定属性拷备到target中.
	 * 开发背景：项目中经常会将一个类中的某些属性拷备到另一个类中 如果属性很多 需要写大量的get/set方法 所以想写一个公用类拷备属性值
	 * @param src 源
	 * @param srcPropertyArray 源中要拷备的属性的属性名称 其与targetPropertyArray一一对应 如果其与targetPropertyArray都为空 则表示拷备相同属性名的属性值
	 * @param target 目标
	 * @param targetPropertyArray 目标中要拷备的属性名称  其与srcPropertyArray一一对应 如果其与srcPropertyArray都为空 则表示拷备相同属性名的属性值
	 * */
	public static void copyProperties(Object src,String[] srcPropertyArray,Object target,String[] targetPropertyArray) {
		try{
			
			//如果srcPropertyArray 和targetPropertyArray都为空 则把属性名称相同的属性拷备了
			if(srcPropertyArray==null&&targetPropertyArray==null){
				srcPropertyArray=EntityUtil.getSameProperty(src,target);
				targetPropertyArray=srcPropertyArray;
			}else if((srcPropertyArray==null&&targetPropertyArray!=null)||
					(srcPropertyArray!=null&&targetPropertyArray==null )||
					srcPropertyArray.length!=targetPropertyArray.length){
				//如果拷备属性个数不一致 直接抛出异常
				throw  new RuntimeException("srcPropertyArray与targetPropertyArray个数不一致");
			}
			
			
			for(int i=0;i<srcPropertyArray.length;i++){
				
				Field srcField = src.getClass().getDeclaredField(srcPropertyArray[i]);
				srcField.setAccessible(true); //设置些属性是可以访问的  因有时属性是private的 如果不设置会报错
				Object srcPropertyValue=srcField.get(src);
				
				Field targetField=target.getClass().getDeclaredField(targetPropertyArray[i]);
				targetField.setAccessible(true);
				
				targetField.set(target, srcPropertyValue);
			}
			
		}catch (Exception e) {
			throw  new RuntimeException(e);
		}
		
	}
	
	
	
}  