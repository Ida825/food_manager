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
		//获取查询的参数
		String typename = request.getParameter("typename");
		
		//获取当前页参数
		String curPage = request.getParameter("curPage");
		//默认从第一页开始
		int curPageVar = 1;
		if(curPage!=null){
			curPageVar = Integer.parseInt(curPage);
		}
		
		
		try {
			//调用分页的方法
			PageTools pt = mft.getTableListPage(typename, curPageVar);
			//将PageTools的对象存入request作用域
			request.setAttribute("pt", pt);
			//请求转发到界面层（通过el表达式在request作用域中获取到数据）
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
