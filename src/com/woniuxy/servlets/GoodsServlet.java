package com.woniuxy.servlets;

import java.io.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.woniuxy.daos.GoodsDao;
import com.woniuxy.entities.*;
import com.woniuxy.daos.GoodsTypeDao;
import com.woniuxy.daos.SupplierDao;


/**
 * Servlet implementation class GoodsServlet
 */
@WebServlet({ "/goods/addGoods.do", "/goods/showGoods.do","/goods/updGoods.do" })
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		GoodsDao goodsDao = new GoodsDao();
		if (path.equals("/goods/showGoods.do")) {
			String gno = request.getParameter("gno");
			String gname = request.getParameter("gname");
			String gtname = request.getParameter("gtname");
			String sname = request.getParameter("sname");								
			try {
				
				
				PageBean<Goods> pb = new PageBean<Goods>();
				int totalRows = goodsDao.getTotalRows(gno,gname,gtname,sname);
				pb.setTotalRows(totalRows);
				
				int pageSize = 8;
				pb.setPageSize(pageSize);
				
				int currentPage = 1;
				String tempCurrentPage=request.getParameter("cutPage");
				 if(tempCurrentPage!=null) {
					 currentPage=Integer.parseInt(tempCurrentPage);
				 }
				if(currentPage<1) {
					currentPage=1;
				}
				if(currentPage>pb.getPages()) {
					currentPage=pb.getPages();
					if(pb.getPages()==0) {
						currentPage=1;
					}
				}
				pb.setCurrentPage(currentPage);
								
				List<Goods> l = goodsDao.getGoods(gno,gname,gtname,sname,pb);
				pb.setData(l);
				
				Map<String,Object> map = new HashMap<>();
				map.put("gno", gno);
				map.put("gname", gname);
				map.put("gtname", gtname);
				map.put("sname", sname);
				request.setAttribute("result", map);
				request.setAttribute("pageBean", pb);
				RequestDispatcher rd = request.getRequestDispatcher("showGoods.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}						
		}
		if (path.equals("/goods/updGoods.do")) {
			String gid = request.getParameter("gid");
			Goods goods = null;
			
			try {
				
				List<GoodsType> gt = new GoodsTypeDao().getGoodsType(null, null);
				List<Supplier> s = new SupplierDao().getSupplier(null, null);
				
				request.setAttribute("GoodsType", gt);
				request.setAttribute("Supplier", s);
				if (gid==null) {
					request.getRequestDispatcher("goodsAdd.jsp").forward(request, response);
				}else {
					goods = goodsDao.getGoodsById(Integer.parseInt(gid)).get(0);
					
					request.setAttribute("Goods", goods);
					request.getRequestDispatcher("goodsUpd.jsp").forward(request, response);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		if (path.equals("/goods/addGoods.do")) {
			//得到项目在服务器上的真实路径：D:\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JAVAWEB1216\
        	String realPath=request.getServletContext().getRealPath("/");
        	//项目路径下的front/upload目录
        	String dirPath=realPath+File.separator+"front"+File.separator+"upload";
        	
        	File f=new File(dirPath);
        	if(!f.exists()){
        		
        		f.mkdirs();
        	}
        	//处理文件上传：请求对象  存放路径  文件大小 编码
        	MultipartRequest mreq=new MultipartRequest(request, dirPath,5*1024*1024,"utf-8");
        	//不能再使用原来的请求对象得到请求参数，必须使用mreq
        	String goodsId=mreq.getParameter("goodsId");
        	String goodsCode=mreq.getParameter("goodsCode");
        	String goodsName=mreq.getParameter("goodsName");
        	String goodsPrice=mreq.getParameter("price");
        	String goodsType=mreq.getParameter("typeId");
        	String goodsSupplier=mreq.getParameter("supplierId");
        	//参数写文件域的名称
        	String oldFileName=mreq.getFilesystemName("goodsImg");
        	
        	//得到文件的新名称
        	Date d=new Date();
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        	String dateStr=sdf.format(d);
        	Random random=new Random();
        	int rn=random.nextInt(10000);//[)
        	//得到源文件的后缀
        	String ext=oldFileName.substring(oldFileName.indexOf("."));
        	//得到新文件名称
        	String newFileName=dateStr+rn+ext;
        	//创建一个针对原文件的文件对象
        	File oldFile=new File(dirPath+File.separator+oldFileName);
        	//更改文件名称
        	oldFile.renameTo(new File(dirPath+File.separator+newFileName));
        	//存放数据库
        	String goodsImg="front"+File.separator+"upload"+File.separator+newFileName;
        	try {
        		if (goodsId==null) {
        		Goods g=new Goods(0,Integer.parseInt(goodsType), Integer.parseInt(goodsSupplier), goodsCode, goodsName, Float.parseFloat(goodsPrice), goodsImg);
            	
    				goodsDao.addGoods(g);
    			
				}
        		else {
        		Goods g=new Goods(Integer.parseInt(goodsId),Integer.parseInt(goodsType), Integer.parseInt(goodsSupplier), goodsCode, goodsName, Float.parseFloat(goodsPrice), goodsImg);
        		goodsDao.updateGoods(g);
					}
        	} catch (SQLException e) {
				e.printStackTrace();
			}
        	
        	response.sendRedirect("showGoods.do");
        }
		
}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
