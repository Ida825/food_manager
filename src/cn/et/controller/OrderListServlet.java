package cn.et.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.MyOrderList;
import cn.et.model.PageTools;

/**
 * Servlet implementation class OrderListServlet
 */
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    MyOrderList mol = new MyOrderList();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当前页请求参数
		String curPage = request.getParameter("curPage");
		//默认第一页
		Integer curPageVar = 1;
		if(curPage!=null){
			curPageVar = Integer.parseInt(curPage);
		}
		
		try {
			PageTools pt = mol.getTableListPage(curPageVar);
			//将查到的数据信息存入request作用域中
			request.setAttribute("pt", pt);
			
			request.getRequestDispatcher("/detail/orderList.jsp").forward(request, response);
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
