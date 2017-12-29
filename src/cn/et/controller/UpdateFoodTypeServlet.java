package cn.et.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.MyFoodType;

/**
 * Servlet implementation class UpdateFoodTypeServlet
 */
public class UpdateFoodTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFoodTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    MyFoodType mft = new MyFoodType();
    		
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//获取要更新菜系的请求参数
		String typeid = request.getParameter("cid");
		String newname = request.getParameter("newname");
		
		try {
			//调用更新菜系的方法进行修改
			mft.updateFoodType(typeid, newname);
			//更新后跳转到菜系列表重新查询一遍
			request.getRequestDispatcher("/listFoodType").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
