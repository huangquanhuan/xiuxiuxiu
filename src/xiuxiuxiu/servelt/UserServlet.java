package xiuxiuxiu.servelt;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xiuxiuxiu.dao.UserDaoImpl;
import xiuxiuxiu.pojo.User;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDaoImpl userDao= new UserDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */

    public void login(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            int name = Integer.parseInt( request.getParameter("phoneNumber").trim() );
            String password = request.getParameter("password").trim();
            User user = userDao.getStudent(name, password);
            if(user == null) {
                request.getRequestDispatcher("login.jsp?err=账号或密码错误，请重新输入").forward(request, response);
            }
            else {
                request.getRequestDispatcher("loginSuccess.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            String method = request.getParameter("method");
            if(method.equals("login")) {
                login(request,response);
            }
            else if(method.equals("add")) {
                //处理添加
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
