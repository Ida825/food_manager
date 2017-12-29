package cn.et.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.MyFoodType;
import cn.et.model.PageTools;

/**
 * Servlet implementation class FoodTypeServlet
 */
public class FoodTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    MyFoodType mft = new MyFoodType();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ѯ�Ĳ���
		String typename = request.getParameter("typename");
		
		//��ȡ��ǰҳ����
		String curPage = request.getParameter("curPage");
		//Ĭ�ϴӵ�һҳ��ʼ
		int curPageVar = 1;
		if(curPage!=null){
			curPageVar = Integer.parseInt(curPage);
		}
		
		
		try {
			//���÷�ҳ�ķ���
			PageTools pt = mft.getTableListPage(typename, curPageVar);
			//��PageTools�Ķ������request������
			request.setAttribute("pt", pt);
			//����ת��������㣨ͨ��el���ʽ��request�������л�ȡ�����ݣ�
			request.getRequestDispatcher("/detail/cuisineList.jsp").forward(request, response);
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
