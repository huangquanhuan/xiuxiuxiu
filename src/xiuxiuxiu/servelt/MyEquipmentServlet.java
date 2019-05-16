package xiuxiuxiu.servelt;

import xiuxiuxiu.dao.EquipmentDAO;

import xiuxiuxiu.dao.EquipmentDAOImpl;
import xiuxiuxiu.pojo.Equipment;
import xiuxiuxiu.pojo.Student;
import xiuxiuxiu.util.Page;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
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
import java.util.List;

@WebServlet(name = "EquipmentServlet", urlPatterns = { "/EquipmentServlet" })
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
			else if(method.equals("add"))
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
			request.getRequestDispatcher("首页.jsp").forward(request, response);
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("equipmentName");
		HttpSession session = request.getSession(true);
		Student user = (Student)session.getAttribute("name");
		equipmentDAO.addEquipment(name, user.getID());
		request.getRequestDispatcher("首页.jsp").forward(request, response);
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		equipmentDAO.deleteEquipment(id);
		request.getRequestDispatcher("首页.jsp").forward(request, response);
	}
}
