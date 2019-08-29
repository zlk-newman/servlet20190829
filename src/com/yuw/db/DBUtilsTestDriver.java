package com.yuw.db;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.jupiter.api.Test;

import com.yuw.bean.UserInfoBean;

public class DBUtilsTestDriver {

	@Test
	public void testDBUtils() {

		try {

			// 获取数据源对象
			DataSource ds = DBCPDataSource.getDataSource();
			// DBUtils需要使用QueryRunner查询器对象进行sql语句执行（QueryRunner等价于jdbc的Statement查询器）
			QueryRunner qr = new QueryRunner(ds, true);
			// 查询sql语句
			String strSql = "select * from tuserinfo";
			// 执行查询，获取查询结果集（List+Bean）
			List<UserInfoBean> lst = qr.query(strSql, new BeanListHandler<UserInfoBean>(UserInfoBean.class));
			
			
			
			// 遍历解析结果
			// 遍历行，list中的一个map就是一行数据
			for (UserInfoBean en : lst) {
				// 打印每一行的实体类的信息
				System.out.println(en.toString());
			}
			System.out.println("==============================");

			// 执行查询，获取查询结果集（List+Map）
			List<Map<String, Object>> lst2 = qr.query(strSql, new MapListHandler());
			
			// 遍历行，list中的一个map就是一行数据
			for (Map<String, Object> map : lst2) {
				// 遍历列，map中的一个键值对就是一列的值
				Set<String> keys = map.keySet();
				// 通过keyset遍历map的键值对
				for (String key : keys) {
					System.out.print("  " + map.get(key));
				}
				// 换行
				System.out.println();
			}
			System.out.println("==============================");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
