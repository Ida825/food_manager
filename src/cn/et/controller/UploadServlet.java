package cn.et.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    MyFood mf = new MyFood();
    String absPath = "E:/myImage";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//�ж��Ƿ����ļ��ϴ�����
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			//���������ļ��ϴ��Ĺ�����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//���ڽ����ϴ�������
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			String foodname = null;
			String price = null;
			String descr = null;
			String typeid = null;
			ServletContext sc = this.getServletContext(); 
			//���·����ȡ����·��
			//absPath = sc.getRealPath("/myImage");
			String spath = "";
			try {
				List<FileItem> list = upload.parseRequest(request);
				//��������,���������ı������ļ�
				Iterator i = list.iterator();
				while(i.hasNext()){
					FileItem fi = (FileItem)i.next();
					if(fi.isFormField()){
						//�ֱ�ȡ���������
						if(fi.getFieldName().equals("foodName")){
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
						
					}else{
						//������ļ�
						//��ȡ�ļ���
						String fileName = fi.getName();
						//��ȡ�ֽ���
						InputStream is = fi.getInputStream();
						//��ȡ�ļ��ϴ�·��
						String desPath = absPath+"/"+fileName;
						spath = spath+"/"+fileName;
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
				
				mf.saveFood(foodname, price, typeid, descr, spath);
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