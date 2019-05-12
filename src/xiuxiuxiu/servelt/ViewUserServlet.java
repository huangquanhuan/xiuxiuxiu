package xiuxiuxiu.servelt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xiuxiuxiu.dao.RepairActivityDAO;
import xiuxiuxiu.dao.RepairActivityDAOImpl;
import xiuxiuxiu.dao.ViewUserDAOImpl;
import xiuxiuxiu.pojo.RepairActivity;
import xiuxiuxiu.pojo.ViewDataGrid;

@WebServlet("/ViewUserServlet")
public class ViewUserServlet extends HttpServlet{
    private static ViewUserDAOImpl viewUserDAOImpl = new ViewUserDAOImpl();
    List<ViewDataGrid> viewUsers = new ArrayList<ViewDataGrid>();
    private static final long serialVersionUID = 1L;
    
    void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = Optional.ofNullable(request.getParameter("userName")).orElse("");
        int applicationType = Integer.parseInt(Optional.ofNullable(request.getParameter("MethodTypeSelect")).orElse("-1")) ;
        int activityId = Integer.parseInt(Optional.ofNullable(request.getParameter("activityID")).orElse("-1"));
        String componentType = Optional.ofNullable(request.getParameter("componentsTypeSelect")).orElse("");
        int reservationState = Integer.parseInt(Optional.ofNullable(request.getParameter("StateSelect")).orElse("-1"));
        viewUsers = viewUserDAOImpl.getList(name,applicationType, activityId, componentType, reservationState);
        request.setAttribute("viewUsers", viewUsers);
        request.setAttribute("totalNum", Optional.ofNullable(viewUsers).map(u->u.size()).orElse(0));
        request.getRequestDispatcher("appointments-search.jsp").forward(request, response);
    }
    
    void show(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        RepairActivityDAO repairActivityDAO = new RepairActivityDAOImpl();
        List<RepairActivity> activities = repairActivityDAO.listRecentActivities(5);
        request.setAttribute("activities", activities);
        request.getRequestDispatcher("appointments-home.jsp").forward(request, response);
    }
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            String method = Optional.ofNullable(request.getParameter("method")).orElse("");
            if (method.equals("")) {
                //处理
                list(request, response);
            } else if(method.equals("show")){
                show(request, response);
            } else {
                list(request, response);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
