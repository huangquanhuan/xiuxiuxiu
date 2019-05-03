package xiuxiuxiu.servelt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xiuxiuxiu.dao.RepairActivityDAO;
import xiuxiuxiu.dao.RepairActivityDAOImpl;
import xiuxiuxiu.pojo.RepairActivity;

/**
 * 响应首页请求的servlet
 */
@WebServlet("/HomePageServlet")
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomePageServlet() {
		super();
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<RepairActivity> repairActivityList;
		RepairActivityDAO repairActivityDao = new RepairActivityDAOImpl();
		repairActivityList = repairActivityDao.ListAll();
		request.setAttribute("repairActivityList", repairActivityList);
		request.getRequestDispatcher("HomePage.jsp").forward(request, response);

	}

}
