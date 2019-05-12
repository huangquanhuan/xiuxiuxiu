package xiuxiuxiu.servelt;
import xiuxiuxiu.dao.ManagerDAO;

import xiuxiuxiu.dao.ManagerDAOImpl;
import xiuxiuxiu.pojo.Manger;
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

@WebServlet(name = "ManagerServlet", urlPatterns = { "/ManagerServlet" })
public class ManagerServlet extends HttpServlet{
	private ManagerDAO managerDAO = new ManagerDAOImpl();
	private Manger mangers = new Manger();
	int start = 0;
	int count = 5;
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			/* 获取分页信息 */
			if (request.getParameter("page.start") != null) {
				start = Integer.parseInt(request.getParameter("page.start"));
			}
			if (request.getParameter("page.count") != null) {
				count = Integer.parseInt(request.getParameter("page.count"));
			}
			Page page = new Page(start, count);
			page.setTotal(3);

			if (request.getParameter("page.start") != null) {
				list(request, response, page);
			}
			if (request.getParameter("type") != null) {
				if (request.getParameter("type").equals("list")) {
					list(request, response,page);
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void list(HttpServletRequest request, HttpServletResponse response, Page page)
			throws ServletException, IOException {
		List<Manger> mangers = managerDAO.list(page.getStart(), page.getCount());
		System.out.print("11");
		int total = managerDAO.getTotalManger();
		page.setTotal(total);
		request.setAttribute("mangers", mangers);
		// Servlet本身并不输出响应到客户端，因此必须将请求转发到视图页面
		RequestDispatcher rd;
		// 获取转发对象
		rd = request.getRequestDispatcher("人员列表.jsp");
		// 转发请求
		rd.forward(request, response);
	}
}
