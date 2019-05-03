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
import xiuxiuxiu.dao.ReservationDAO;
import xiuxiuxiu.dao.ReservationDAOImpl;
import xiuxiuxiu.pojo.RepairActivity;
import xiuxiuxiu.pojo.Reservation;
import xiuxiuxiu.pojo.Student;

/**
 * 我的预约单管理页面请求的servlet
 */
@WebServlet("/MyReservationManageServlet")
public class MyReservationManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReservationManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Reservation> ReservationList;
		Student student = (Student) request.getSession().getAttribute("user");
		ReservationDAO reservationDao = new ReservationDAOImpl();
		ReservationList = reservationDao.List(student.getID());
		request.setAttribute("ReservationList", ReservationList);
		request.getRequestDispatcher("MyReservationManage.jsp").forward(request, response);

	}

}
