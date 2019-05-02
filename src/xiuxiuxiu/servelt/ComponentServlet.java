package xiuxiuxiu.servelt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import xiuxiuxiu.dao.ComponentDAOImpl;
import xiuxiuxiu.pojo.Component;

@WebServlet("/ComponentServlet")
public class ComponentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ComponentDAOImpl componentDao = new ComponentDAOImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComponentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        String type = request.getParameter("type");

        Component component = new Component();
        component.setName(name);
        component.setQuantity(quantity);
        component.setPrice(price);
        component.setType(type);

        componentDao.addComponent(component);
        // 服务端跳转
        request.getRequestDispatcher("ComponentEdit.jsp").forward(request, response);

    }

    void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("123456");
        List<Component> components = componentDao.getList();
        request.setAttribute("components", components);
        request.getRequestDispatcher("ComponentEdit.jsp").forward(request, response);
    }
    
    void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (componentDao.isComponentExist(id)) {

            response.sendRedirect("ComponentEdit.jsp"); // 客户端跳转
        } else {
            componentDao.deleteComponent(id);
            response.sendRedirect("ComponentEdit.jsp"); // 客户端跳转
        }
    }

    void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (componentDao.isComponentExist(id)) {
            response.sendRedirect("ComponentEdit.jsp"); // 客户端跳转
        } else {
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            String type = request.getParameter("type");

            Component component = new Component();
            component.setName(name);
            component.setQuantity(quantity);
            component.setPrice(price);
            component.setType(type);
            componentDao.updateComponent(component);
            response.sendRedirect("ComponentEdit.jsp"); // 客户端跳转
        }
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("接受请求");
            String method = request.getParameter("method");
            if (method.equals("add")) {
                add(request, response);
            } else if (method.equals("update")) {
                update(request, response);
            } else if (method.equals("delete")) {
                delete(request, response);
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
