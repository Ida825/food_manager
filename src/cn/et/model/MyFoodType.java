package cn.et.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MyFoodType {
	/**
	 * ���ز�ѯ������������
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
	public Integer getFoodTypeListCount(String name) throws SQLException{
		if(name==null){
			name="";
		}
		
		//������ѯ�����ݵ�SQL���
		String sql = "select count(rowid) as cr from foodtype where typename like'%"+name+"%'";
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
		Integer totalCount = getFoodTypeListCount(name);
		
		PageTools pt = new PageTools(curPage, totalCount, null);
		//��ѯ��ҳ��Ҫ��ʾ�����ݲ����뼯��
		String sql = "select * from (select t.*,rownum as rn from foodtype t where t.typename like '%"+name+"%' order by t.typeid asc) where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex()+"";
		List<Map> list = DbUtils.query(sql);
		//�����ݴ��뼯��
		pt.setData(list);
		return pt;
	}
	
	/**
	 * ���ݲ�����Ӳ�ϵ
	 * @param name
	 * @throws SQLException 
	 */
	public void saveFoodType(String name) throws SQLException{
		String sql = "insert into foodtype values((select nvl((max(typeid)+1),1) from foodtype),'"+name+"')";
		DbUtils.execute(sql);
	}
	
	/**
	 * ɾ����ϵ
	 * @param did
	 * @throws SQLException
	 */
	public void deleteFoodType(String did) throws SQLException{
		String sql = "delete from foodtype where typeid="+did;
		DbUtils.execute(sql);
	}
	
	/**
	 * ���²�ϵ
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
