package com.yunsom.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Title: DBBusiness 
 * @Description:数据库业务类
 */
public class DBBusiness extends DBUtil{
	
	/**
	 * 
	 * @MethodName: update 
	 * @Description: 数据库增删改操作
	 * @Parameter: @param sql
	 * @return void
	 * @Example: TODO
	 * @ModificationHistory: 
	 * Author		Date		Description(Why & What is modified)
	 * -----------------------------------------------------------------------------------
	 * Administrator - 2016年7月5日 下午7:03:58 - First Release
	 *
	 */
	public static void update(String sql){
		setConnection(PropertiesUtil.GetValueByKey("dbEnv"));
		execute(sql);
		System.out.println("sql info :"+sql);
		closeConnection();
	}
	
	/**
	 * 
	 * @MethodName: query 
	 * @Description:查询单条记录
	 * @Parameter: @param sql
	 * @Parameter: @return
	 * @return Map<String,Object>
	 * @Example: TODO
	 * @ModificationHistory: 
	 * Author		Date		Description(Why & What is modified)
	 * -----------------------------------------------------------------------------------
	 * Administrator - 2016年7月5日 下午7:05:35 - First Release
	 *
	 */
	public static Map<String, Object> query(String sql){
		Map<String, Object> map = null;
		setConnection(PropertiesUtil.GetValueByKey("dbEnv"));
		map = queryForMap(sql);
		closeConnection();
		if(map.isEmpty()){
			System.out.println("该记录在数据库中,不存在.当前sql："+sql);
			return new HashMap<String, Object>();
		}else{
			return map;
		}
	}
	
	/**
	 * 
	 * @MethodName: queryList 
	 * @Description: 查询多条记录
	 * @Parameter: @param sql
	 * @Parameter: @return
	 * @return List<Map<String,Object>>
	 * @Example: TODO
	 * @ModificationHistory: 
	 * Author		Date		Description(Why & What is modified)
	 * -----------------------------------------------------------------------------------
	 * Administrator - 2016年7月5日 下午7:05:39 - First Release
	 *
	 */
	public static List<Map<String, Object>> queryList(String sql){
		List<Map<String, Object>> mapList =null;
		setConnection(PropertiesUtil.GetValueByKey("dbEnv"));
		mapList = queryForList(sql);
		closeConnection();
		if(mapList.size()==0){
			System.out.println("该记录在数据库中,不存在.当前sql："+sql);
			return mapList;
		}else{
			return mapList;
		}
	}

	public static void main(String[] args) {
		String sql = "INSERT INTO zhubajie_qa.qa_ui (id,testcase,`status`,time) VALUES(0,'testLogin','SUCCESS','2017-04-18 18:18:43');";
		DBBusiness.update(sql);
		//System.out.println(lists.toString());
	}
}
