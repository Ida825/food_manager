package cn.et.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MyFoodType {
	/**
	 * 返回查询到的数据总数
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
	public Integer getFoodTypeListCount(String name) throws SQLException{
		if(name==null){
			name="";
		}
		
		//创建查询总数据的SQL语句
		String sql = "select count(rowid) as cr from foodtype where typename like'%"+name+"%'";
		//获取从数据库查询到的数据
		List<Map> list = DbUtils.query(sql);
		//返回查到的数据总数
		return Integer.parseInt(list.get(0).get("CR").toString());
	}
	
	/**
	 * 获取分页参数
	 * @param name 
	 * @param curPage 
	 * @return 
	 * @throws SQLException
	 */
	public PageTools getTableListPage(String name,Integer curPage) throws SQLException{
		if(name==null){
			name="";
		}
		//获取数据总条数
		Integer totalCount = getFoodTypeListCount(name);
		
		PageTools pt = new PageTools(curPage, totalCount, null);
		//查询出页面要显示的数据并存入集合
		String sql = "select * from (select t.*,rownum as rn from foodtype t where t.typename like '%"+name+"%' order by t.typeid asc) where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex()+"";
		List<Map> list = DbUtils.query(sql);
		//将数据存入集合
		pt.setData(list);
		return pt;
	}
	
	/**
	 * 根据菜名添加菜系
	 * @param name
	 * @throws SQLException 
	 */
	public void saveFoodType(String name) throws SQLException{
		String sql = "insert into foodtype values((select nvl((max(typeid)+1),1) from foodtype),'"+name+"')";
		DbUtils.execute(sql);
	}
	
	/**
	 * 删除菜系
	 * @param did
	 * @throws SQLException
	 */
	public void deleteFoodType(String did) throws SQLException{
		String sql = "delete from foodtype where typeid="+did;
		DbUtils.execute(sql);
	}
	
	/**
	 * 更新菜系
	 * @param did
	 * @throws SQLException
	 */
	public void updateFoodType(String did,String name) throws SQLException{
		String sql = "update foodtype set typename='"+name+"' where typeid="+did;
		DbUtils.execute(sql);
	}
	
	public List<Map> getAllFoodType() throws SQLException{
		String sql = "select * from foodtype";
		return DbUtils.query(sql);
	}
}
