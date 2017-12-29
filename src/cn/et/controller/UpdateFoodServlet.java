package cn.et.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.et.model.MyFood;
import cn.et.model.MyFoodType;

/**
 * Servlet implementation class UpdateFoodServlet
 */
public class UpdateFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    MyFood mf = new MyFood();
    MyFoodType mft = new MyFoodType();
    String absPath = "E:/myImage";
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//判断是否是文件上传请求
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			//创建解析文件上传的工厂类
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//用于解析上传的内容
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			String foodid = null;
			String foodname = null;
			String price = null;
			String descr = null;
			String typeid = null;
			String fileName = null;
			String img = null;
			
			ServletContext sc = this.getServletContext(); 
			//相对路径获取绝对路径
			//absPath = sc.getRealPath("/myImage");
			String spath = "";
			try {
				List<FileItem> list = upload.parseRequest(request);
				//遍历集合,解析出是文本还是文件
				Iterator i = list.iterator();
				while(i.hasNext()){
					FileItem fi = (FileItem)i.next();
					if(fi.isFormField()){
						//分别取到所需参数
						if(fi.getFieldName().equals("foodid")){
							foodid = fi.getString();
						}
						
						if(fi.getFieldName().equals("foodname")){
							foodname = fi.getString("UTF-8");
						}
						
						if(fi.getFieldName().equals("price")){
							price = fi.getString();
						}
						
						if(fi.getFieldName().equals("descr")){
							descr = fi.getString();
						}
						
						if(fi.getFieldName().equals("typeid")){
							typeid = fi.getString();
						}
						
						if(fi.getFieldName().equals("img")){
							img= fi.getString("UTF-8");
						}

					}else{
						//如果是文件
						//获取文件名
						
						if(fi.getName()==null || fi.getName().equals("")){
							
							fileName = img;
							spath = "/"+fileName;
							break;
						}else{
							fileName = "/"+fi.getName();
						}
						//获取字节流
						InputStream is = fi.getInputStream();
						//获取文件上传路径
						String desPath = absPath+fileName;
						spath = fileName;
						FileOutputStream fos = new FileOutputStream(new File(desPath));
						byte[] bs = new byte[1024];
						int n = -1;
						while((n=is.read(bs))!=-1){
							fos.write(bs,0,n);
						}
						fos.flush();
						is.close();
						fos.close();
					}
				}
				
				mf.updateFood(foodid, typeid, foodname, price, descr, spath);
				request.getRequestDispatcher("/listFood").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
