package com.woniuxy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.woniuxy.daos.GoodsDao;
import com.woniuxy.entities.Goods;
import com.woniuxy.entities.PageBean;

/**
 * Servlet implementation class GoodsFrontServlet
 */
@WebServlet("/front/getAll.do")
public class GoodsFrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsFrontServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		GoodsDao goodsDao = new GoodsDao();	
		PrintWriter out=response.getWriter();
		try {
			
			String gname = request.getParameter("gname");
			PageBean<Goods> pb = new PageBean<Goods>();
			int totalRows = goodsDao.getTotalRows(null,gname,null,null);
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
							
			List<Goods> l = goodsDao.getGoods(null,gname,null,null,pb);
			pb.setData(l);
			
			String cusName=(String)request.getSession().getAttribute("cusName");
			Map<String,Object> map = new HashMap<>();
			map.put("cusName", cusName);
			map.put("pb", pb);
			JSONObject js=new JSONObject(map);
			out.print(js);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			out.flush();
			out.close();
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
