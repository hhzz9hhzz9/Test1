package com.woniuxy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.woniuxy.daos.CustomerDao;
import com.woniuxy.entities.Customer;

/**
 * Servlet implementation class CusFrontServlet
 */
@WebServlet("/front/login.do")
public class CusFrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CusFrontServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cusName=request.getParameter("cusName");
		String cusPwd=request.getParameter("cusPwd");
		Customer cus=new Customer(cusName, cusPwd);
		CustomerDao cusDao=new CustomerDao();
		PrintWriter out=response.getWriter();
		try {
			int cusId=cusDao.isExit(cus);
			if(cusId!=0){
				HttpSession session=request.getSession();
				session.setAttribute("cusId", String.valueOf(cusId));
				session.setAttribute("cusName", cusName);
				out.print(true);
			}
			else{
				out.print(false);
			}
			
		} catch (SQLException e) {
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
		doGet(request, response);
	}

}
