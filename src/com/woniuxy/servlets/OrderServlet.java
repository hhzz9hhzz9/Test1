package com.woniuxy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woniuxy.daos.OrderDao;
import com.woniuxy.entities.Order;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/front/orderConfirm.do")
public class OrderServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sids=request.getParameter("sids");
		
		String orderRemark=request.getParameter("orderRemark");
		String orderPrice=request.getParameter("totalprice");
		String cusId=(String)request.getSession(false).getAttribute("cusId");
		
		Order order=new Order(Integer.parseInt(cusId), Float.parseFloat(orderPrice), orderRemark);	
		OrderDao orderDao=new OrderDao();
		PrintWriter out=response.getWriter();
		try {
			String orderId=orderDao.addOrder(order,sids);
			out.print(orderId);
			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
