package com.woniuxy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.woniuxy.daos.UserDao;
import com.woniuxy.entities.User;
import com.woniuxy.entities.LoginCache;
import com.woniuxy.tools.MD5;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet({ "/user.do", "/exit.do", "/checkPwd.do", "/updPwd.do", "/user/showUser.do", "/user/caozuo.do" ,"/user/userAdd.do","/user/userUpd.do"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();

		PrintWriter out = response.getWriter();
		UserDao userDao = new UserDao();
		if (path.equals("/user.do")) {
			HttpSession session = request.getSession();
			String checkCode = (String) session.getAttribute("checkCode");
			String checkCode1 = request.getParameter("checkCode");
			if (!checkCode.equalsIgnoreCase(checkCode1)) {

				out.print("<script>alert('验证码不正确');location.href='login.jsp'</script>");
				out.flush();
				out.close();
				return;
			}
			String userName = request.getParameter("userName");
			String userPwd = request.getParameter("userPwd");

			try {
				User user = new User(userName, MD5.toMD5(userPwd));
				int uid = userDao.isExist(user);
				if (uid != 0) {

					session.setAttribute("userName", userName);
					session.setAttribute("userId", uid);
					session.setAttribute("userPwd", userPwd);

					Cookie cookie = new Cookie("userName", userName);
					cookie.setMaxAge(1800);// 设置cookie对象的过期时间，单位是秒
					response.addCookie(cookie);

					response.sendRedirect(request.getContextPath() + "/index.jsp");
					return;
				}
				out.print("<script>alert('用户名密码不正确或账户被禁用');location.href='login.jsp'</script>");
//				response.sendRedirect(request.getContextPath() + "/error.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}finally {
				out.flush();
				out.close();
			}
		}
		if (path.equals("/exit.do")) {
			HttpSession session = request.getSession(false);
			String userName = request.getParameter("userName");
			LoginCache lc = LoginCache.getInstance();
			lc.setMap(userName, null);
			session.removeAttribute("userName");

			response.sendRedirect("login.jsp?t=" + Math.random());
		}
		if (path.equals("/checkPwd.do")) {
			HttpSession session = request.getSession(false);
			String oldPwd = request.getParameter("oldPwd");
			String oldPwd1 = (String) session.getAttribute("userPwd");
			out.print(oldPwd.equals(oldPwd1));
			out.flush();
			out.close();
		}
		if (path.equals("/updPwd.do")) {
			HttpSession session = request.getSession(false);
			String pwd = request.getParameter("newPwd");
			int uid = (Integer) session.getAttribute("userId");
			try {
				userDao.updPwd(uid, MD5.toMD5(pwd));
				session.setAttribute("userPwd", pwd);
				response.sendRedirect("login.jsp");
			} catch (SQLException | NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		if (path.equals("/user/showUser.do")) {
			try {
				List<User> list = userDao.getUsers();
				request.setAttribute("user", list);
				request.getRequestDispatcher("showUser.jsp").forward(request,  response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (path.equals("/user/caozuo.do")) {
			int uid = Integer.parseInt(request.getParameter("uid"));
			int flag = Integer.parseInt(request.getParameter("status"));

			try {
				if (flag == 1) {
					userDao.updOthers(uid, "停用");
				} else if (flag == 0) {
					userDao.updOthers(uid, "启用");
				} else {
					String pwd;
					pwd = MD5.toMD5("123456");
					userDao.updPwd(uid, pwd);
				}
				response.sendRedirect(request.getContextPath()+"/user/showUser.do");
			} catch (NoSuchAlgorithmException | SQLException e) {
				e.printStackTrace();
			}

		}
		if (path.equals("/user/userAdd.do")) {
			String userName = request.getParameter("userName");
			String userRole = request.getParameter("userRole");
			

			try {
				userDao.addUser(userName, userRole);
				response.sendRedirect(request.getContextPath()+"/user/showUser.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (path.equals("/user/userUpd.do")) {
			int uid = Integer.parseInt(request.getParameter("userId"));
			String userName = request.getParameter("userName");
			String userRole = request.getParameter("userRole");
			try {
				userDao.updUser(userName, userRole,uid);
				response.sendRedirect(request.getContextPath()+"/user/showUser.do");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
