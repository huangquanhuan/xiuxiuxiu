package xiuxiuxiu.servelt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xiuxiuxiu.pojo.Student;
import xiuxiuxiu.dao.StudentDAO;
import xiuxiuxiu.dao.StudentDAOImpl;

/**
 * 响应修改个人信息请求的servlet
 */
@WebServlet("/ChangeInfoServlet")
public class ChangeInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeInfoServlet() {
		super();
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(true);
		Student student = (Student)session.getAttribute("name");
		student.setName(request.getParameter("name"));
		student.setStudentID(request.getParameter("studentID"));
		student.setEmail(request.getParameter("Email"));
		student.setAddress(request.getParameter("address"));
		
		StudentDAO studentDao = new StudentDAOImpl();
		studentDao.update(student);
		
		request.getRequestDispatcher("首页.jsp").forward(request, response);

	}

}
