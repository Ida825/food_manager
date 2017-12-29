package cn.et.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MyDesk {    
	
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
		String sql = "select count(rowid) as cr from desk where dname like'%"+name+"%'";
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
		String sql = "select * from (select t.*,rownum as rn from DESK t where t.dname like '%"+name+"%' order by t.deskid asc) where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex()+"";
		List<Map> list = DbUtils.query(sql);
		//将数据存入集合
		pt.setData(list);
		return pt;
	}
	
	/**
	 * 根据餐桌名添加餐桌
	 * @param name
	 * @throws SQLException 
	 */
	public void saveDesk(String name) throws SQLException{
		String sql = "insert into desk values((select nvl((max(deskid)+1),1) from desk),'"+name+"',0,'')";
		DbUtils.execute(sql);
	}
	
	/**
	 * 删除餐桌
	 * @param did
	 * @throws SQLException
	 */
	public void deleteDesk(String did) throws SQLException{
		String sql = "delete from desk where deskid="+did;
		DbUtils.execute(sql);
	}
	
	/**
	 * 修改餐桌状态
	 * @param did
	 * @throws SQLException
	 */
	public void updateDesk(String did,String state) throws SQLException{
		String sql = "update desk set dstate="+state+" where deskid="+did;
		DbUtils.execute(sql);
	}
	
	
}
