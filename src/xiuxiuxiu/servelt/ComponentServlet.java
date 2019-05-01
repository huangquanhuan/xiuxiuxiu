package xiuxiuxiu.servelt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.sun.javafx.scene.layout.region.Margins.Converter;


import xiuxiuxiu.dao.ComponentDAOImpl;
import xiuxiuxiu.pojo.Component;

@WebServlet("/ComponentServlet")
public class ComponentServlet extends HttpServlet{
    
    private static final long serialVersionUID = 1L;
    ComponentDAOImpl componentDao = new ComponentDAOImpl();
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComponentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    void add(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("componentName");
        int quantity  = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        String type = request.getParameter("type");
        
        Component component = new Component();
        component.setName(name);
        component.setQuantity(quantity);
        component.setPrice(price);
        component.setType(type);
        
        componentDao.addComponent(component);        
        
    }
    
    void Delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (componentDao.isComponentExist(id)) {
            response.sendRedirect("ComponentEdit.jsp"); // 客户端跳转
        }
        else {
            componentDao.deleteComponent(id);
            response.sendRedirect("ComponentEdit.jsp"); // 客户端跳转
        }
    }
    
    void Update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (componentDao.isComponentExist(id)) {
            response.sendRedirect("ComponentEdit.jsp"); // 客户端跳转
        }
        else {
            String name = request.getParameter("componentName");
            int quantity  = Integer.parseInt(request.getParameter("quantity"));
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
            String method = request.getParameter("method");
            if(method.equals("add")) {
                add(request, response);
            }
            else if(method.equals("update")) {
                
            }
            else if(method.equals("delete")) {
                
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
