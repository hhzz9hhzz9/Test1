package com.woniuxy.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woniuxy.daos.CustomerDao;
import com.woniuxy.entities.Customer;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet({ "/customer/caozuo.do", "/customer/showCus.do","/customer/cusAdd.do","/customer/cusUpd.do" })
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		CustomerDao customerDao = new CustomerDao();
		if (path.equals("/customer/showCus.do")) {
			try {
				List<Customer> list = customerDao.getList();
				request.setAttribute("customer", list);
				request.getRequestDispatcher("showCus.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (path.equals("/customer/caozuo.do")) {
			int uid = Integer.parseInt(request.getParameter("cid"));
			int flag = Integer.parseInt(request.getParameter("status"));

			try {
				if (flag == 1) {
					customerDao.updStatus(uid, "Õ£”√");
				} else if (flag == 0) {
					customerDao.updStatus(uid, "∆Ù”√");
				}
				response.sendRedirect(request.getContextPath()+"/customer/showCus.do");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (path.equals("/customer/cusAdd.do")) {
			String cname = request.getParameter("cusName");
			String ctel = request.getParameter("cusTel");
			String caddr = request.getParameter("cusAddr");
			try {
				customerDao.addCus(cname, ctel, caddr);
				response.sendRedirect(request.getContextPath()+"/customer/showCus.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (path.equals("/customer/cusUpd.do")) {
			int cid = Integer.parseInt(request.getParameter("cusId"));
			String cname = request.getParameter("cusName");
			String ctel = request.getParameter("cusTel");
			String caddr = request.getParameter("cusAddr");
			Customer customer = new Customer(cid, cname, ctel, caddr);
			try {
				customerDao.updCus(customer);
				response.sendRedirect(request.getContextPath()+"/customer/showCus.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
