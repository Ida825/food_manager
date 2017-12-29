package cn.et.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MyFood {
	/**
	 * ���ز�ѯ������������
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
	public Integer getTableListCount(String name) throws SQLException{
		if(name==null){
			name="";
		}
		
		//������ѯ�����ݵ�SQL���
		String sql = "select count(rowid) as cr from food where foodname like'%"+name+"%'";
		//��ȡ�����ݿ��ѯ��������
		List<Map> list = DbUtils.query(sql);
		//���ز鵽����������
		return Integer.parseInt(list.get(0).get("CR").toString());
	}
	
	/**
	 * ��ȡ��ҳ����
	 * @param name 
	 * @param curPage 
	 * @return 
	 * @throws SQLException
	 */
	public PageTools getTableListPage(String name,Integer curPage) throws SQLException{
		if(name==null){
			name="";
		}
		//��ȡ����������
		Integer totalCount = getTableListCount(name);
		
		//��ȡ��ҳ�����Ķ���
		PageTools pt = new PageTools(curPage, totalCount, null);
		//��ѯ��ҳ��Ҫ��ʾ�����ݲ����뼯��
		String sql = "select * from (select t.*,ft.typename,rownum as rn from food t inner join foodtype ft on t.typeid=ft.typeid where t.foodname like '%"+name+"%' order by t.foodid asc) where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex()+"";
		List<Map> list = DbUtils.query(sql);
		//�����ݴ��뼯��
		pt.setData(list);
		return pt;
	}
	
	/**
	 * ���ݲ�����Ӳ�Ʒ
	 * @param name
	 * @param price 
	 * @throws SQLException 
	 */
	public void saveFood(String name, String price,String typeid,String descr,String imgPath) throws SQLException{
		
		String sql = "insert into food values((select nvl((max(foodid)+1),1) from food),"+typeid+",'"+name+"',"+price+",'"+descr+"','"+imgPath+"')";
		DbUtils.execute(sql);
	}
	
	
	
	/**
	 * ɾ����Ʒ
	 * @param did
	 * @throws SQLException
	 */
	public void deleteFood(String fid) throws SQLException{
		String sql = "delete from food where foodid="+fid;
		DbUtils.execute(sql);
	}
	
	/**
	 * �޸Ĳ�Ʒ
	 * @param did
	 * @throws SQLException
	 */
	public void updateFood(String foodid,String typeid,String foodname,String price,String descr,String imgPath) throws SQLException{
		String sql = "update FOOD set typeid="+typeid+", foodname='"+foodname+"',price="+price+",descr='"+descr+"',img='"+imgPath+"' where foodid="+foodid;
		DbUtils.execute(sql);
	}
	
	
}
