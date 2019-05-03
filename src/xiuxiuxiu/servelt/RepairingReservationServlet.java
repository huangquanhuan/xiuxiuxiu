package xiuxiuxiu.servelt;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xiuxiuxiu.dao.ComponentDAO;
import xiuxiuxiu.dao.ComponentDAOImpl;
import xiuxiuxiu.dao.EquipmentDAO;
import xiuxiuxiu.dao.EquipmentDAOImpl;
import xiuxiuxiu.dao.RepairActivityDAO;
import xiuxiuxiu.dao.RepairActivityDAOImpl;
import xiuxiuxiu.dao.StudentDAO;
import xiuxiuxiu.dao.StudentDAOImpl;
import xiuxiuxiu.pojo.Component;
import xiuxiuxiu.pojo.Equipment;
import xiuxiuxiu.pojo.RepairActivity;
import xiuxiuxiu.util.Page;

/**
 * RepairingReservationServlet
 * 
 * 用于处理用户端维修预约页面的后台请求
 * 
 * @author Lenovo
 *
 */
@WebServlet(urlPatterns="/makeReservation")
public class RepairingReservationServlet extends BaseServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -6196254428422180914L;
    
    /**
     * confirmInformation，用于预约第一步：确认个人信息的方法
     */
    public String confirmInformation(HttpServletRequest request, HttpServletResponse response, Page page) {
        Integer uid = Integer.parseInt(request.getParameter("uid"));
        StudentDAO studentDAO = new StudentDAOImpl();
        request.setAttribute("user", studentDAO.get(uid));
        return "make-a-reservation-1.jsp";
    }
    
    /**
     * beforeReserve - 用于预约第二步：填写预约表之前初始化表单内容
     */
    public String beforeReserve(HttpServletRequest request, HttpServletResponse response, Page page) {
        // 获取活动场次列表
        RepairActivityDAO repairActivityDAO = new RepairActivityDAOImpl();
        List<RepairActivity> activities = repairActivityDAO.listRecentActivities(5);
        request.setAttribute("activities", activities);
        
        // 获取零件列表
        ComponentDAO componentDAO = new ComponentDAOImpl();
        List<Component> components = componentDAO.getList();
        request.setAttribute("components", components);
        
        // 获取设备列表
        Integer userId = Integer.parseInt(request.getParameter("uid"));
        EquipmentDAO equipmentDAO = new EquipmentDAOImpl();
        List<Equipment> equipments = equipmentDAO.listEquipmentsByUser(userId);
        request.setAttribute("equipments", equipments);
        
        return "make-a-reservation-2.jsp";
    }

    /* (non-Javadoc)
     * @see xiuxiuxiu.servelt.BaseServlet#add(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, xiuxiuxiu.util.Page)
     */
    @Override
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see xiuxiuxiu.servelt.BaseServlet#delete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, xiuxiuxiu.util.Page)
     */
    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see xiuxiuxiu.servelt.BaseServlet#edit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, xiuxiuxiu.util.Page)
     */
    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see xiuxiuxiu.servelt.BaseServlet#update(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, xiuxiuxiu.util.Page)
     */
    @Override
    public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see xiuxiuxiu.servelt.BaseServlet#list(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, xiuxiuxiu.util.Page)
     */
    @Override
    public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
        // TODO Auto-generated method stub
        return null;
    }
}
