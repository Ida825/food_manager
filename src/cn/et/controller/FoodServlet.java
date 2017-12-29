package cn.et.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.MyFood;
import cn.et.model.PageTools;

/**
 * Servlet implementation class FoodServlet
 */
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    MyFood mf = new MyFood();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ������Ʒ���������
		String foodName = request.getParameter("foodname");
		//��ȡ��ǰҳ����
		String curPage = request.getParameter("curPage");
		Integer curPageVar = 1;
		if(curPage!=null){
			curPageVar = Integer.parseInt(curPage);
		}
		
		try {
			//��ȡ��ҳ����
			PageTools pt = mf.getTableListPage(foodName, curPageVar);
			//�����ݴ���request������
			request.setAttribute("pt", pt);
			//����ת��������㣨��el���ʽ��request����������ݻ�ȡ������
			request.getRequestDispatcher("/detail/foodList.jsp").forward(request, response);
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
