package com.woniuxy.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woniuxy.daos.GoodsTypeDao;
import com.woniuxy.entities.GoodsType;

/**
 * Servlet implementation class TypeServlet
 */
@WebServlet({ "/goodsType/addType.do", "/goodsType/showType.do","/goodsType/updType.do","/goodsType/delType.do" })
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		GoodsTypeDao dao = new GoodsTypeDao();
		if (path.equals("/goodsType/showType.do")) {			
			String typeCode = request.getParameter("gtno");
			String typeName = request.getParameter("gtname");
			try {
				List<GoodsType> list = dao.getGoodsType(typeCode, typeName);
				request.setAttribute("goodsType", list);
				request.setAttribute("gtno", typeCode);
				request.setAttribute("gtname", typeName);
				request.getRequestDispatcher("showGoodsType.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (path.equals("/goodsType/addType.do")) {
			String typeCode = request.getParameter("typeCode");
			String typeName = request.getParameter("typeName");
			GoodsType gt = new GoodsType(0, typeCode, typeName);
			try {
				dao.addType(gt);
				response.sendRedirect(request.getContextPath()+"/goodsType/showType.do");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (path.equals("/goodsType/updType.do")) {
			int typeId = Integer.parseInt(request.getParameter("typeId"));
			String typeCode = request.getParameter("typeCode");
			String typeName = request.getParameter("typeName");
			GoodsType gt = new GoodsType(typeId, typeCode, typeName);
			try {
				dao.updType(gt);
				response.sendRedirect(request.getContextPath()+"/goodsType/showType.do");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (path.equals("/goodsType/delType.do")) {
			
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
