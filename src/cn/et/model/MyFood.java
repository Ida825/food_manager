package cn.et.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MyFood {
	/**
	 * 返回查询到的数据总数
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
	public Integer getTableListCount(String name) throws SQLException{
		if(name==null){
			name="";
		}
		
		//创建查询总数据的SQL语句
		String sql = "select count(rowid) as cr from food where foodname like'%"+name+"%'";
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
		Integer totalCount = getTableListCount(name);
		
		//获取分页参数的对象
		PageTools pt = new PageTools(curPage, totalCount, null);
		//查询出页面要显示的数据并存入集合
		String sql = "select * from (select t.*,ft.typename,rownum as rn from food t inner join foodtype ft on t.typeid=ft.typeid where t.foodname like '%"+name+"%' order by t.foodid asc) where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex()+"";
		List<Map> list = DbUtils.query(sql);
		//将数据存入集合
		pt.setData(list);
		return pt;
	}
	
	/**
	 * 根据菜名添加菜品
	 * @param name
	 * @param price 
	 * @throws SQLException 
	 */
	public void saveFood(String name, String price,String typeid,String descr,String imgPath) throws SQLException{
		
		String sql = "insert into food values((select nvl((max(foodid)+1),1) from food),"+typeid+",'"+name+"',"+price+",'"+descr+"','"+imgPath+"')";
		DbUtils.execute(sql);
	}
	
	
	
	/**
	 * 删除菜品
	 * @param did
	 * @throws SQLException
	 */
	public void deleteFood(String fid) throws SQLException{
		String sql = "delete from food where foodid="+fid;
		DbUtils.execute(sql);
	}
	
	/**
	 * 修改菜品
	 * @param did
	 * @throws SQLException
	 */
	public void updateFood(String foodid,String typeid,String foodname,String price,String descr,String imgPath) throws SQLException{
		String sql = "update FOOD set typeid="+typeid+", foodname='"+foodname+"',price="+price+",descr='"+descr+"',img='"+imgPath+"' where foodid="+foodid;
		DbUtils.execute(sql);
	}
	
	
}
