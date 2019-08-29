package com.yuw.db.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.yuw.bean.UserInfoBean;

/**
 * 使用oop封装jdbc对数据库的操作
 * 
 * @author Administrator
 *
 */
public class DBDriver {

	/**
	 * 获取数据库连接对象
	 * 
	 * @return 数据库连接对象
	 */
	public Connection getConn() {
		// 2 建立数据库连接
		Connection conn = null;
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

			conn = DriverManager.getConnection(strURL, strUserName, strUserPsw);
		} catch (Exception e) {
			// 异常处理
			System.out.println("jdbc获取数据库连接异常：");
			e.printStackTrace();
		}

		return conn;
	}

	/**
	 * 基于jdbc查询操作
	 * 
	 * @param strSql 查询sql语句
	 * @return ResultSet结果集
	 */
	@Deprecated
	public ResultSet query(String strSql) {
		// 返回对象，查询结果集
		ResultSet rs = null;
		try {
			// 4 执行SQL语句
			// 需要使用Statement查询器对象进行sql语句执行
			// 创建一个conn对象的查询器Statement
			// 括号中的参数是为了可以重置ResultSet的游标的位置
			Statement st = getConn().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// 使用查询器Statement，执行sql语句
			rs = st.executeQuery(strSql);
		} catch (Exception e) {
			// 异常处理
			System.out.println("jdbc查询异常：");
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 基于jdbc查询操作
	 * 
	 * @param strSql 查询sql语句
	 * @return List+Map类型的结果集
	 */
	public List<Map<String, Object>> queryListMap(String strSql) {
		// 返回对象List
		List<Map<String, Object>> lst = new ArrayList<>();

		// 获取查询结果集ResultSet
		// 返回对象，查询结果集

		// 将查询结果集ResultSet中的数据 拷贝到 List+Map的数据结构中

		try (Connection conn = getConn();
				// 4 执行SQL语句
				// 需要使用Statement查询器对象进行sql语句执行
				// 创建一个conn对象的查询器Statement
				// 括号中的参数是为了可以重置ResultSet的游标的位置
				Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				// 使用查询器Statement，执行sql语句
				ResultSet rs = st.executeQuery(strSql);) {
			// 将ResultSet的遍历的下标位置从最后，重置到初始位置
			rs.beforeFirst();
			// 使用元数据获取每一行的类信息数据
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获取列数
			int columnCount = rsmd.getColumnCount();
			// 自动遍历每一行数据的的各个列的值
			while (rs.next()) {
				// 创建一个Map对象，用于存储每一行的数据的列的值（列名和列值构成键值对）
				Map<String, Object> map = new LinkedHashMap<>();
				// 使用Resultset的元数据遍历每一行的列数据
				for (int i = 1; i <= columnCount; i++) {
					// 获取当前列的名字
					String strColmnName = rsmd.getColumnLabel(i);
					// 获取当前列的值
					Object objColmnValue = rs.getObject(strColmnName);
					// 列名-列值 键值对放到每一行的map中
					map.put(strColmnName, objColmnValue);
				}
				// 将每一行的map存入List中，List中的每个map表示是一行数据
				lst.add(map);
			}
		} catch (SQLException e) {
			// 异常处理
			System.out.println("jdbc查询异常：");
			e.printStackTrace();
		}
		// 返回值
		return lst;
	}

	/**
	 * 基于jdbc查询操作
	 * 
	 * @param strSql 查询sql语句
	 * @return List+Map类型的结果集
	 */
	@Deprecated
	public List<UserInfoBean> queryListBean_Old(String strSql) {
		// 返回对象List
		List<UserInfoBean> lst = new ArrayList<>();

		// 获取查询结果集ResultSet
		// 返回对象，查询结果集

		// 将查询结果集ResultSet中的数据 拷贝到 List+Map的数据结构中

		try (Connection conn = getConn();
				// 4 执行SQL语句
				// 需要使用Statement查询器对象进行sql语句执行
				// 创建一个conn对象的查询器Statement
				// 括号中的参数是为了可以重置ResultSet的游标的位置
				Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				// 使用查询器Statement，执行sql语句
				ResultSet rs = st.executeQuery(strSql);) {
			// 将ResultSet的遍历的下标位置从最后，重置到初始位置
			rs.beforeFirst();
			// 使用元数据获取每一行的类信息数据
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获取列数
			int columnCount = rsmd.getColumnCount();
			// 自动遍历每一行数据的的各个列的值
			while (rs.next()) {
				// 创建一个实体类对象，用于存储每一行的数据的列的值（列名和实体类的属性名一一对应）
				UserInfoBean en = new UserInfoBean();
				// 把当前ResultSet中的这一行的数据存储到与之对应的实体类的属性中
				en.setUser_Id(rs.getInt(1));
				en.setUser_Name(rs.getString(2));
				en.setUser_Psw(rs.getString(3));
				en.setUser_Createtime(rs.getDate(4));
				en.setIsdeleted(rs.getInt(5));

				// 使用Resultset的元数据遍历每一行的列数据
				/*
				 * for (int i = 1; i <= columnCount; i++) { // 获取当前列的名字 String strColmnName =
				 * rsmd.getColumnLabel(i); // 获取当前列的值 Object objColmnValue =
				 * rs.getObject(strColmnName); // 列名-列值 键值对放到每一行的map中
				 * en.setUser_Id(rs.getInt(1)); en.setUser_Name(rs.getString(2)); }
				 */
				// 将每一行的map存入List中，List中的每个map表示是一行数据
				lst.add(en);
			}
		} catch (SQLException e) {
			// 异常处理
			System.out.println("jdbc查询异常：");
			e.printStackTrace();
		}
		// 返回值
		return lst;
	}

	// 使用泛型、反射将该方法改造为具有通用性的方法

	/**
	 * 基于jdbc查询操作
	 * 
	 * @param strSql 查询sql语句
	 * @param clazz  实体类的Class对象
	 * @return List+Bean的查询结果集
	 */
	public <T> List<T> queryListBean(String strSql, Class<T> clazz) {
		// 返回对象List
		List<T> lst = new ArrayList<>();

		// 获取查询结果集ResultSet
		// 返回对象，查询结果集

		// 将查询结果集ResultSet中的数据 拷贝到 List+Map的数据结构中

		try (Connection conn = getConn();
				// 4 执行SQL语句
				// 需要使用Statement查询器对象进行sql语句执行
				// 创建一个conn对象的查询器Statement
				// 括号中的参数是为了可以重置ResultSet的游标的位置
				Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				// 使用查询器Statement，执行sql语句
				ResultSet rs = st.executeQuery(strSql);) {
			// 将ResultSet的遍历的下标位置从最后，重置到初始位置
			rs.beforeFirst();			
			// 自动遍历每一行数据的的各个列的值
			while (rs.next()) {
				// 创建一个实体类对象，用于存储每一行的数据的列的值（列名和实体类的属性名一一对应）
				T en = clazz.newInstance();
				// 使用反射将当前ResultSet中的这一行的数据存储到与之对应的实体类的属性中
				// 这里要求有一个映射关系：数据库的字段名需要和实体类的属性名保持一致（可以不用区分大小写，但是要求内容必须一致）

				// 方法一： 使用 filed类的 set方法设置

				// 方法二：使用方法对象setXXX获取方法对象，然后调用invoke进行设值
				
				// 方法三：使用apache提供的BeanUtils的反射工具类
				
				// 此处采用方法一处理
				// 使用反射获取所有的属性对象
				Field[] fields = clazz.getDeclaredFields();
				// 遍历每一个属性对象
				for (Field field : fields) {
					// 获取属性名
					String fieldName = field.getName();
					
					// 因为属性名和数据库的字段名一样（对应要求）
					// 通过属性名也就是数据库字段名获取该字段的值
					Object objColmnValue = getValueByTypeFromResultSet(field.getGenericType(), rs, fieldName);

					// 设置访问私有属性的权限
					field.setAccessible(true);
					// 使用反射，设置该属性的值					
					field.set(en, objColmnValue);
				}
				
				// 将每一行的实体类存入List中，List中的每个实体类表示是一行数据
				lst.add(en);
			}
		} catch (Exception e) {
			// 异常处理
			System.out.println("jdbc查询异常：");
			e.printStackTrace();
		}
		// 返回值
		return lst;
	}

	/**
	 * 根据实体类中的属性的数据类型从ResultSet中获取对应的数据类型字段值
	 * 
	 * @param type      实体类中的属性的数据类型
	 * @param rs        ResultSet结果集
	 * @param fieldName 实体类中的属性名也是数据库的字段名
	 * @return 与实体类属性名对应的字段的值
	 * @throws SQLException
	 */
	private Object getValueByTypeFromResultSet(Type type, ResultSet rs, String fieldName) throws SQLException {
		// 返回值类型
		Object obj = null;
		// 获取数据类型
		String strTypeName = type.getTypeName();
		// 根据属性的数据类型，从数据库结果集ResultSet中获取与之对应的数据类型的值
		switch (strTypeName) {
		case "java.lang.String":
			obj = rs.getString(fieldName);
			break;
		case "java.lang.Integer":
			// case "int":
			obj = rs.getInt(fieldName);
			break;
		case "java.util.Date":
			obj = rs.getDate(fieldName);
			break;
	    // 继续添加其他的数据类型
		}
		// 返回值
		return obj;

	}

}
