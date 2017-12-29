package cn.et.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.MyDesk;
import cn.et.model.PageTools;

/**
 * Servlet implementation class DeskServlet
 */
public class DeskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    MyDesk md = new MyDesk();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//��ȡҪ��ѯ�Ĳ���
			String name = request.getParameter("dname");
			
			//��ȡ��ǰҳ����
			String curPage = request.getParameter("curPage");
			Integer curPageVar = 1;
		
			if(curPage!=null){
				curPageVar = Integer.parseInt(curPage);
			}
			
			//���Ʋ����ģ�Ͳ��ȡ����
			PageTools pt = md.getTableListPage(name, curPageVar);
			//�����ݴ浽request������
			request.setAttribute("pt",pt);
			//����ת���������(����������el���ʽͨ��request���������ȡ������)
			request.getRequestDispatcher("/detail/boardList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
