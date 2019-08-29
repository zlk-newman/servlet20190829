package com.yuw.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DBTestDriver {

	public static void main(String[] args) {
		try {
			// 0 将数据库的驱动jar包拷入项目的web-info的lib目录中，并引入项目
			// Oracle的驱动类在路径：D:\app\Adminstrator\product\11.2.0\dbhome_1\jdbc\lib\ojdbc6.jar

			// 1 加载数据库驱动
			// 加载数据库驱动类的实例，需要传入的参数有：数据库的连接串（Url）、数据库用户名、数据库访问密码
			// 数据库访问的URL（不同的数据库的URL是固定的，请自行查找即可）
			String strURL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			/*
			 * 解析OracleUrl： jdbc:oracle:thin:@ 这部分是固定格式；
			 * 
			 * @后面是数据库服务器的主机名、ip地址：其中使用127.0.0.1 表示数据库服务器是本机；或者使用 localhost表示本机 1521
			 * 表示Oracle客户端监听程序的端口号； orcl是Oracle数据库的实例名（等价认为是数据库名）
			 */
			// 用户名和密码
			String strUserName = "yuw2019";
			String strUserPsw = "oracle";
			// 数据库的驱动类的包路径名
			String strDBDriver = "oracle.jdbc.OracleDriver";

			// 使用反射的方式创建数据库驱动类的实例，各个数据库驱动类的名字是固定的，请自行查找；
			Class.forName(strDBDriver);

			// 2 建立数据库连接
			Connection conn = DriverManager.getConnection(strURL, strUserName, strUserPsw);

			// 3 准备sql语句
			String strSql = "select * from tuserinfo";
			// 4 执行SQL语句
			// 需要使用Statement查询器对象进行sql语句执行
			// 创建一个conn对象的查询器Statement
			// 括号中的参数是为了可以重置ResultSet的游标的位置
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			// 使用查询器Statement，执行sql语句
			ResultSet rs = st.executeQuery(strSql);

			// 5 获取执行结果集ResultSet，并解析结果集
			// 使用循环迭代解析查询结果集 resultset的内容
			while (rs.next()) {
				// 此处采用了游标的方式遍历查询结果集中的内容
				System.out.print("用户名：" + rs.getString(2));
				System.out.println("密码：" + rs.getString("user_psw"));
			}

			// 补充内容：了解即可
			///////////////////////////////////////////////////////////////
			System.out.println("使用元数据自动遍历每一行的列的值：");
			conn.close();
			// 将ResultSet的遍历的下标位置从最后，重置到初始位置
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
			///////////////////////////////////////////////////////////////////
			// 关闭资源
			rs.close();
			st.close();
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
