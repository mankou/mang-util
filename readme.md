# 说明
* 整理自己的工具类形成自己的jar包
# TODO
* 整理一套能用的mvn命令写成文档 把配置文件都配置好 要求能一个命令打包、生成源码、javadoc  能把源码、javadoc、jar安装的maven仓库 目前我执行mvn install报错 不能把jar包安装到maven仓库
# 版本说明
	# V2.0.2
		+ 增加用freemaker生成word的工具类.
		+ 重新规划了包路径 增加了demo包存放一些demo 将原来的工具类放到common包类型
    # V2.0.1
		+ 添加PropertyUtil.java 用于读取property配置文件
	# V2.0.0
		+ 将代码纳入maven管理 同时将代码从mangJavaSample移到mang-util工程下

	# V1.1.13
		+ CopyUtil增加拷备指定属性的公用代码
		+ 增加EntityUtil公用类
	
	# V1.1.12
	 + StringUtil增加如下方法 joinHql joinHqlLike joinHqlIn indexOf mergeWithNoRepeat removeRepeat
	 
	# V1.1.11
	 + StringUtil 增加joinWithNoRepeat 用于支持连接字符串并且不重复
	  
	# V1.1.10
	* 注 因换虚拟机160117丢失了lib导致期间的版本丢失
	+ TimeUtil 增加max() 返回2个时间中最大的一个
	+ TimeUtil 增加min() 返回2个时间中最小的一个

	# V1.1.6
	* + StringUtil.splitToArray()方法 将字符串转换成字符串数组

	# V1.1.5
	* + NumberUtil.isNumber() 方法 判断字符串是否由数字组成

	# V1.1.4
	* + TimeUtil.computeTimeInterval() 增加语言设置 可设置中文、英文
	* + StringUtil.select() 选择字符串工具类

	# V1.1.3
	* + FileUtil增加processEndSeparator方法
	* * FileUtil修改注释

	# V1.1.2
	 * +FileUtil 添加了几个处理文件的方法

	# V1.1.1
	 * +SpringUtil 添加去掉字符串数字的方法cleanNumber()

