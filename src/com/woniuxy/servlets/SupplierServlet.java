package com.woniuxy.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woniuxy.daos.SupplierDao;
import com.woniuxy.entities.Supplier;

/**
 * Servlet implementation class SupplierServlet
 */
@WebServlet({ "/supplier/showSupplier.do", "/supplier/delSupplier.do", "/supplier/addSupplier.do", "/supplier/updSupplier.do" })
public class SupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		SupplierDao dao = new SupplierDao();
		if (path.equals("/supplier/showSupplier.do")) {
			String sno = request.getParameter("supplierCode");
			String sname = request.getParameter("supplierName");
			try {
				List<Supplier> list = dao.getSupplier(sno, sname);
				request.setAttribute("sno", sno);
				request.setAttribute("sname", sname);
				request.setAttribute("supplier", list);
				request.getRequestDispatcher("showSupplier.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (path.equals("/supplier/addSupplier.do")) {
			String sno = request.getParameter("supplierCode");
			String sname = request.getParameter("supplierName");
			String tel = request.getParameter("supplierTel");
			Supplier supplier = new Supplier(0, sno, sname, tel);
			try {
				dao.addSupplier(supplier);
				response.sendRedirect(request.getContextPath()+"/supplier/showSupplier.do");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (path.equals("/supplier/updSupplier.do")) {
			int sid = Integer.parseInt(request.getParameter("supplierId"));
			String sno = request.getParameter("supplierCode");
			String sname = request.getParameter("supplierName");
			String tel = request.getParameter("supplierTel");
			Supplier supplier = new Supplier(sid, sno, sname, tel);
			try {
				dao.updateSupplier(supplier);
				response.sendRedirect(request.getContextPath()+"/supplier/showSupplier.do");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (path.equals("/supplier/delSupplier.do")) {
			
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
