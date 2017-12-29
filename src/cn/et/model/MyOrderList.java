package cn.et.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MyOrderList {
	/**
	 * ���ز�ѯ������������
	 * @return
	 * @throws SQLException 
	 */
	public Integer getTableListCount() throws SQLException{

		//������ѯ�����ݵ�SQL���
		String sql = "select count(rowid) as cr from FOODORDER";
		//��ȡ�����ݿ��ѯ��������
		List<Map> list = DbUtils.query(sql);
		//���ز鵽����������
		return Integer.parseInt(list.get(0).get("CR").toString());
	}
	
	/**
	 * ��ȡ��ҳ����
	 * @param curPage 
	 * @return 
	 * @throws SQLException
	 */
	public PageTools getTableListPage(Integer curPage) throws SQLException{
		
		//��ȡ����������
		Integer totalCount = getTableListCount();
	
		
		//��ȡ��ҳ�����Ķ���
		PageTools pt = new PageTools(curPage, totalCount, null);
		//��ѯ��ҳ��Ҫ��ʾ�����ݲ����뼯��
		String sql = "select m.*,(select sum(fo.dcount*f.price)total from FOODORDERDETAIL fo inner join food f on fo.foodid=f.foodid where fo.orderid=m.orderid) tp from (select t.*,d.dname,rownum as rn from FOODORDER t inner join DESK d on t.deskid=d.deskid order by t.orderid asc) m"
				+ " where rn>="+curPage+" and rn<="+totalCount;
		List<Map> list = DbUtils.query(sql);
		//�����ݴ��뼯��
		pt.setData(list);
		return pt;
	}
	

	/**
	 * ��ȡ�����ܽ��
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
	 * �޸Ķ���״̬
	 * @param orderid
	 * @param state
	 * @throws SQLException
	 */
	public void updateOrder(String orderid,String state) throws SQLException{
		String sql = "update FOODORDER set orderstate="+state+" where orderid="+orderid;
		DbUtils.execute(sql);
	}
	
	/**
	 * ��ѯ��������
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
