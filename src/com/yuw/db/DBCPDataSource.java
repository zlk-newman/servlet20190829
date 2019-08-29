package com.yuw.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

/**
 * 使用dbcp作为数据源（采用单例模式）
 * 
 * @author Administrator
 *
 */
public class DBCPDataSource {

	// 1、定义一个单例模式类的静态实例对象
	private static DataSource ds = null;

	// 使用静态语句块初始化ds变量
	static {
		try {
			// 加载数据库属性配置文件
			// 使用Properties工具类加载db.properties配置文件的内容
			Properties prop = new Properties();
			// 读取指定的配置文件内容
			prop.load(DBCPDataSource.class.getClassLoader().getResourceAsStream("db.properties"));
			// 通过指定的数据库配置信息获取数据源对象
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			// 异常处理
			System.out.println("获取DBCP2数据源异常：");
			e.printStackTrace();
		}
	}

	// 2、构造方法私有化
	private DBCPDataSource() {
	}

	// 3、提供一个统一的单例模式对象的获取方法接口
	public static DataSource getDataSource() {
		return ds;
	}	
}
