package com.zhubajie.util;

import com.zhubajie.util.FileUtil;

import java.sql.*;
import java.util.*;


public class DBUtil {
	public static Map<String, Map<String, String>> databaseMap = new HashMap();
	public static Map<String, Connection> databaseConnection = new HashMap();
	private static final Set<String> registered = new HashSet();
	public static Connection currentConnect = null;
	public static String currentDatabase = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static PreparedStatement prs = null;

	private static void registerDriver(String driver) throws Exception {
		try {
			if (registered.contains(driver)) {
				return;
			}
			DriverManager.registerDriver((Driver) Class.forName(driver)
					.newInstance());
			registered.add(driver);
		} catch (Throwable e) {
			throw new Exception("Cannot register SQL driver " + driver);
		}
	}

	public static Connection setConnection(String database) {
		String methodInfo = "setConnection(" + database + ")";
		Map<String, String> databaseInfoMap = (Map) databaseMap.get(database);
		if (databaseInfoMap == null) {
			getDatabaseMap();
			databaseInfoMap = (Map) databaseMap.get(database);
			if (databaseInfoMap == null) {
				String message = "查找不到" + database
						+ "对应的数据库信息，请配置database.properties文件中数据库信息！";
				Log.logError(message);
			}
		}
		try {
			registerDriver((String) databaseInfoMap.get("driver"));

			currentDatabase = database;
			currentConnect = DriverManager.getConnection(
					(String) databaseInfoMap.get("url"),
					(String) databaseInfoMap.get("userName"),
					(String) databaseInfoMap.get("password"));
		} catch (Exception e) {
			Log.logError(e.getMessage());
		}
		databaseConnection.put(currentDatabase, currentConnect);
		return currentConnect;
	}

	public static List<Map<String, Object>> queryForList(String sql) {
		List<Map<String, Object>> list = new ArrayList();
		String methodInfo = "queryForList(" + sql + ")";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = currentConnect.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				Map map = new HashMap();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				list.add(map);
			}
		} catch (Exception e) {
			Log.logError(e.getMessage());
			closeConnection();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static Map<String, Object> queryForMap(String sql) {
		String methodInfo = "queryForList(" + sql + ")";
		Statement stmt = null;
		ResultSet rs = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			stmt = currentConnect.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()){
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
				}}

		} catch (Exception e) {
			closeConnection();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public static void execute(String sql) {
		String methodInfo = "execute(" + sql + ")";
		Statement stmt = null;
		try {
			stmt = currentConnect.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			Log.logError(e.getMessage());
			closeConnection();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeConnection() {
		closeConnection(currentDatabase);
	}

	public static void closeConnection(String database) {
		try {
			Connection connection = (Connection) databaseConnection
					.remove(database);
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeAllConnection() {
		Iterator iter = databaseConnection.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			try {
				Connection connection = (Connection) entry.getValue();
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		databaseConnection.clear();
	}

	public static Map<String, Map<String, String>> getDatabaseMap() {
		Map<String, String> databaseInfoMap = new HashMap();
		Map<String, Map<String, String>> map = new HashMap();
		if (databaseMap.isEmpty()) {
			Properties properties = FileUtil
					.getProperties("/database.properties");
			Iterator iter = properties.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String databaseKey = (String) entry.getKey();
				if ((databaseKey != null) && (!databaseKey.trim().equals(""))
						&& (databaseKey.contains("."))) {
					String[] aa = databaseKey.split("\\.");
					String databaseName = databaseKey.split("\\.")[0].trim();
					if (databaseMap.get(databaseName) == null) {
						databaseMap.put(databaseName, new HashMap());
					}
					String databaseInfoKey = databaseKey.split("\\.")[1].trim();
					String databaseInfoValue = ((String) entry.getValue())
							.trim();
					databaseInfoMap = (Map) databaseMap.get(databaseName);
					databaseInfoMap.put(databaseInfoKey, databaseInfoValue);

					map.put(databaseName, databaseInfoMap);
				}
			}
			databaseMap = map;
		}
		return map;
	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("os.name"));
		//DBUtil.setConnection("test");
		//String sql = "select * from zhubajie_member.mb_account limit 1";
		//List<Map<String, Object>> lists = DBUtil.queryForList(sql);
		//System.out.println(lists.toString());
		//String sql = "INSERT INTO zhubajie_qa.qa_ui (id,testcase,`status`,time) VALUES(0,'testLogin','SUCCESS','2017-04-18 18:18:43');";
		//DBUtil.execute(sql);
	}
}
