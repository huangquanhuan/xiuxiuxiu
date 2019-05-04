package xiuxiuxiu.servelt;

import xiuxiuxiu.dao.StudentDAO;
import xiuxiuxiu.dao.StudentDAOImpl;
import xiuxiuxiu.pojo.Student;
import xiuxiuxiu.pojo.User;
import xiuxiuxiu.util.Page;
import xiuxiuxiu.servelt.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.nio.sctp.MessageInfo;

import java.io.IOException;
import java.rmi.dgc.Lease;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = { "/StudentServlet" })
public class StudentServlet extends HttpServlet {
	private StudentDAO StudentDAO = new StudentDAOImpl();
	private Student student = new Student();
	int start = 0;
	int count = 5;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");

			if (request.getParameter("type") != null) {
				if (request.getParameter("type").equals("login")) {
					login(request, response);
				}
				else if (request.getParameter("type").equals("register")) {
				    register(request, response);
				}
				else if (request.getParameter("type").equals("exit")) {
					exit(request, response);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Servlet本身并不输出响应到客户端，因此必须将请求转发到视图页面
		RequestDispatcher rd;
		student.setPhoneNumber(request.getParameter("phonenumber"));
		student.setPassword(request.getParameter("password"));
		
		if (student.getPhoneNumber().length() < 1) {
			request.setAttribute("err", "请输入登录名！");
			// 获取转发对象
			rd = request.getRequestDispatcher("首页.jsp");
			// 转发请求
			rd.forward(request, response);
		} else if (student.getPassword().length() < 2) {
			request.setAttribute("err", "请输入密码！");
			// 获取转发对象
			rd = request.getRequestDispatcher("首页.jsp");
			// 转发请求
			rd.forward(request, response);
		}  else if (StudentDAO.get(student.getPhoneNumber(), student.getPassword()) == null) {
			// 登陆失败
			request.setAttribute("err", "密码错误！");
			// 获取转发对象
			rd = request.getRequestDispatcher("首页.jsp");
			// 转发请求
			rd.forward(request, response);
		} else {
			// 登陆成功
			
			HttpSession session = request.getSession(true);
			student = StudentDAO.get(student.getPhoneNumber(), student.getPassword());
			session.setAttribute("name", student);
			
			//登录成功后刷新首页维修活动
			//后续需要跳转到HomePageServlet
			request.getRequestDispatcher("/HomePageServlet").forward(request,response);
		}

	}
	public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Servlet本身并不输出响应到客户端，因此必须将请求转发到视图页面
		RequestDispatcher rd;
		HttpSession session = request.getSession(true);
		session.invalidate();
		rd = request.getRequestDispatcher("首页.jsp");
		// 转发请求
		rd.forward(request, response);

	}

	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Servlet本身并不输出响应到客户端，因此必须将请求转发到视图页面
		RequestDispatcher rd;

		student.setName(request.getParameter("name"));
		student.setPhoneNumber(request.getParameter("phonenumber"));
		student.setAddress(request.getParameter("address"));
		student.setPassword(request.getParameter("password"));
		String password_repeat = request.getParameter("password2");
		
			StudentDAO.add(student);
			// 获取转发对象
			rd = request.getRequestDispatcher("首页.jsp");
			// 转发请求
			rd.forward(request, response);
		

	}

}

