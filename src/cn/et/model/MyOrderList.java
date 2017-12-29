package cn.et.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MyOrderList {
	/**
	 * 返回查询到的数据总数
	 * @return
	 * @throws SQLException 
	 */
	public Integer getTableListCount() throws SQLException{

		//创建查询总数据的SQL语句
		String sql = "select count(rowid) as cr from FOODORDER";
		//获取从数据库查询到的数据
		List<Map> list = DbUtils.query(sql);
		//返回查到的数据总数
		return Integer.parseInt(list.get(0).get("CR").toString());
	}
	
	/**
	 * 获取分页参数
	 * @param curPage 
	 * @return 
	 * @throws SQLException
	 */
	public PageTools getTableListPage(Integer curPage) throws SQLException{
		
		//获取数据总条数
		Integer totalCount = getTableListCount();
	
		
		//获取分页参数的对象
		PageTools pt = new PageTools(curPage, totalCount, null);
		//查询出页面要显示的数据并存入集合
		String sql = "select m.*,(select sum(fo.dcount*f.price)total from FOODORDERDETAIL fo inner join food f on fo.foodid=f.foodid where fo.orderid=m.orderid) tp from (select t.*,d.dname,rownum as rn from FOODORDER t inner join DESK d on t.deskid=d.deskid order by t.orderid asc) m"
				+ " where rn>="+curPage+" and rn<="+totalCount;
		List<Map> list = DbUtils.query(sql);
		//将数据存入集合
		pt.setData(list);
		return pt;
	}
	

	/**
	 * 获取订单总金额
	 * @param orderid
	 * @return
	 * @throws SQLException
	 */
	public Integer getTotal(String orderid) throws SQLException{
		String sql = "select sum(fo.dcount*f.price)total from FOODORDERDETAIL fo inner join food f on fo.foodid=f.foodid ";
		List<Map> list = DbUtils.query(sql);
		Integer total = Integer.parseInt(list.get(0).get("TOTAL").toString());
		return total;
	}
	
	
	/**
	 * 修改订单状态
	 * @param orderid
	 * @param state
	 * @throws SQLException
	 */
	public void updateOrder(String orderid,String state) throws SQLException{
		String sql = "update FOODORDER set orderstate="+state+" where orderid="+orderid;
		DbUtils.execute(sql);
	}
	
	/**
	 * 查询订单详情
	 * @param orderid
	 * @return
	 * @throws SQLException
	 */
	public List orderDetail(String orderid) throws SQLException{
		String sql = "select f.foodname,f.price,fd.dcount from food f inner join foodorderdetail fd on f.foodid=fd.foodid where fd.orderid="+orderid;
		List<Map> list = DbUtils.query(sql);
		return list;
	}
	
	
}
