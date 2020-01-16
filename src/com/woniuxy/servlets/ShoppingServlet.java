package com.woniuxy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.woniuxy.daos.ShoppingDao;
import com.woniuxy.entities.Shopping;

/**
 * Servlet implementation class ShoppingServlet
 */
@WebServlet({"/front/addShop.do","/front/getShopping.do","/front/updShopping.do","/front/getSubShoppings.do"})
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		ShoppingDao shoppingDao=new ShoppingDao();
		if (path.equals("/front/addShop.do")) {
			String goodsId=request.getParameter("goodsId");
			int cusId=Integer.parseInt((String)request.getSession(false).getAttribute("cusId"));
			try {
			if (goodsId==null) {
				
				int total = shoppingDao.getTotalShop(cusId);
				PrintWriter out = response.getWriter();
				out.print(total);
				out.flush();
				out.close();
				return;
			}
			Shopping shopping=new Shopping();
			shopping.setCusId(cusId);

			shopping.setGid(Integer.parseInt(goodsId));
			
				shoppingDao.addShop(shopping); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (path.equals("/front/getShopping.do")) {
			Object obj = request.getSession(false).getAttribute("cusId");
			if (obj!=null) {
				int cusId = Integer.parseInt((String)obj);
				PrintWriter out=response.getWriter();
				try {
					List<Shopping> list = shoppingDao.getShoppingByCusId(cusId);
					
//					System.out.println(list);
					String cusName=(String)request.getSession(false).getAttribute("cusName");
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("cusName", cusName);
					map.put("shoppings", list);
					JSONObject jsonObject = new JSONObject(map);
					out.print(jsonObject);					
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					out.flush();
					out.close();
				}
				
			}	
		}
		if (path.equals("/front/updShopping.do")) {
			String shId = request.getParameter("shoppingId");
			String shCount = request.getParameter("shoppingCount");

			Shopping shopping=new Shopping();
			shopping.setShId(Integer.parseInt(shId));
			shopping.setShCount((Integer.parseInt(shCount)));
			PrintWriter out=response.getWriter();
			try {
				
				shoppingDao.updShopping(shopping);				
				out.print(true);
			} catch (SQLException e) {
				out.print(false);				
				e.printStackTrace();				
			}finally {
				out.flush();
				out.close();
			}
		}
		if(path.equals("/front/getSubShoppings.do")){
			//得到客户端的请求参数
			String shoppingIds=request.getParameter("sids");
			PrintWriter out=response.getWriter();
			try {
				List<Shopping> l=shoppingDao.getSubShoppings(shoppingIds);
				String cusName=(String)request.getSession(false).getAttribute("cusName");
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("cusName", cusName);
				map.put("shoppings", l);
				JSONObject js=new JSONObject(map);
				out.print(js);
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}finally {
				out.flush();
				out.close();
			}
			
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
