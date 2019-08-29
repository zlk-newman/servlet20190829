package com.yuw.db.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.yuw.bean.UserInfoBean;

public class DBDriverTest {
// 数据库驱动类
	DBDriver dbd = new DBDriver();

	@Test
	public void testQuery3() {
		// 3 准备sql语句
		String strSql = "select * from tuserinfo";
		// 调用DBDriver的查询方法，获取查询结果集（List+Map）
		List<UserInfoBean> lst = dbd.queryListBean(strSql, UserInfoBean.class);
		// 遍历结果集
		// 遍历行，list中的一个map就是一行数据
		for (UserInfoBean en : lst) {
			// 打印每一行的实体类的信息
			System.out.println(en.toString());
		}
		System.out.println("==============================");
	}

	@Test
	public void testQuery2() {
		// 3 准备sql语句
		String strSql = "select * from tuserinfo";
		// 调用DBDriver的查询方法，获取查询结果集（List+Map）
		List<Map<String, Object>> lst = dbd.queryListMap(strSql);
		// 遍历结果集
		// 遍历行，list中的一个map就是一行数据
		for (Map<String, Object> map : lst) {
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
	}

	@Test
	public void testQuery() {
		// 3 准备sql语句
		String strSql = "select * from tuserinfo";
		// 将ResultSet的遍历的下标位置从最后，重置到初始位置
		// 调用查询方法获取ResultSet对象
		ResultSet rs = dbd.query(strSql);
		try {
			rs.beforeFirst();
			// 使用元数据获取每一行的类信息数据
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获取列数
			int columnCount = rsmd.getColumnCount();
			// 自动遍历每一行数据的的各个列的值
			while (rs.next()) {
				// 使用Resultset的元数据遍历每一行的列数据
				for (int i = 1; i <= columnCount; i++) {
					// 获取当前列的名字
					String strColmnName = rsmd.getColumnLabel(i);
					System.out.print(" " + rs.getObject(strColmnName));
				}
				// 换行
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
