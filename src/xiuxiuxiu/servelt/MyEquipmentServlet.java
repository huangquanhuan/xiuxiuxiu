package xiuxiuxiu.servelt;

import xiuxiuxiu.dao.EquipmentDAO;

import xiuxiuxiu.dao.EquipmentDAOImpl;
import xiuxiuxiu.dao.StudentDAO;
import xiuxiuxiu.dao.StudentDAOImpl;
import xiuxiuxiu.pojo.Equipment;
import xiuxiuxiu.pojo.Student;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import java.io.IOException;

/**
 * 响应个人设备管理弹窗请求的servlet
 */
@WebServlet("/MyEquipmentServlet")
public class MyEquipmentServlet extends HttpServlet {
	private EquipmentDAO equipmentDAO = new EquipmentDAOImpl();
	private Equipment equipment = new Equipment();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			
			String method=request.getParameter("method");
			if(method.equals("edit"))
				edit(request,response);
			else if(method.equals("add"))
				add(request,response);
			else if(method.equals("delete"))
				delete(request,response);
			else
				request.getRequestDispatcher("首页.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("equipmentName");
			equipmentDAO.updateEquipment(name, id);
			
			HttpSession session = request.getSession(true);
			StudentDAO studentDAO = new StudentDAOImpl();
			Student student = (Student) session.getAttribute("name");
			student = studentDAO .get(student.getID());
			session.setAttribute("name", student);
			request.getRequestDispatcher("首页.jsp").forward(request, response);
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String equipmentName = request.getParameter("equipmentName");
		HttpSession session = request.getSession(true);
		Student user = (Student)session.getAttribute("name");
		equipmentDAO.addEquipment(equipmentName, user.getID());
		
		StudentDAO studentDAO = new StudentDAOImpl();
		user = studentDAO .get(user.getID());
		session.setAttribute("name", user);
		request.getRequestDispatcher("首页.jsp").forward(request, response);
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		equipmentDAO.deleteEquipment(id);
		System.out.println("删除id为："+id+"的设备");
		
		HttpSession session = request.getSession(true);
		StudentDAO studentDAO = new StudentDAOImpl();
		Student student = (Student) session.getAttribute("name");
		student = studentDAO .get(student.getID());
		session.setAttribute("name", student);
		request.getRequestDispatcher("首页.jsp").forward(request, response);
	}
}
