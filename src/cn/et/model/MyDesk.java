package cn.et.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MyDesk {    
	
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
		String sql = "select count(rowid) as cr from desk where dname like'%"+name+"%'";
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
		String sql = "select * from (select t.*,rownum as rn from DESK t where t.dname like '%"+name+"%' order by t.deskid asc) where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex()+"";
		List<Map> list = DbUtils.query(sql);
		//�����ݴ��뼯��
		pt.setData(list);
		return pt;
	}
	
	/**
	 * ���ݲ�������Ӳ���
	 * @param name
	 * @throws SQLException 
	 */
	public void saveDesk(String name) throws SQLException{
		String sql = "insert into desk values((select nvl((max(deskid)+1),1) from desk),'"+name+"',0,'')";
		DbUtils.execute(sql);
	}
	
	/**
	 * ɾ������
	 * @param did
	 * @throws SQLException
	 */
	public void deleteDesk(String did) throws SQLException{
		String sql = "delete from desk where deskid="+did;
		DbUtils.execute(sql);
	}
	
	/**
	 * �޸Ĳ���״̬
	 * @param did
	 * @throws SQLException
	 */
	public void updateDesk(String did,String state) throws SQLException{
		String sql = "update desk set dstate="+state+" where deskid="+did;
		DbUtils.execute(sql);
	}
	
	
}
