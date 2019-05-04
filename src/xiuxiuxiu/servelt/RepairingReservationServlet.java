package xiuxiuxiu.servelt;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

import xiuxiuxiu.dao.ApplyComponentDAO;
import xiuxiuxiu.dao.ApplyComponentDAOImpl;
import xiuxiuxiu.dao.ComponentDAO;
import xiuxiuxiu.dao.ComponentDAOImpl;
import xiuxiuxiu.dao.EquipmentDAO;
import xiuxiuxiu.dao.EquipmentDAOImpl;
import xiuxiuxiu.dao.RepairActivityDAO;
import xiuxiuxiu.dao.RepairActivityDAOImpl;
import xiuxiuxiu.dao.ReservationDAO;
import xiuxiuxiu.dao.ReservationDAOImpl;
import xiuxiuxiu.dao.ReservationImgUrlDAO;
import xiuxiuxiu.dao.ReservationImgUrlDAOImpl;
import xiuxiuxiu.dao.StudentDAO;
import xiuxiuxiu.dao.StudentDAOImpl;
import xiuxiuxiu.pojo.Component;
import xiuxiuxiu.pojo.Equipment;
import xiuxiuxiu.pojo.RepairActivity;
import xiuxiuxiu.pojo.Reservation;
import xiuxiuxiu.pojo.Student;
import xiuxiuxiu.util.Page;
import xiuxiuxiu.util.ParamDto;
import xiuxiuxiu.util.RequestParser;

/**
 * RepairingReservationServlet
 * 
 * 用于处理用户端维修预约页面的后台请求
 * 
 * @author 刘忠燏
 *
 */
@WebServlet(urlPatterns="/makeReservation")
public class RepairingReservationServlet extends BaseServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -6196254428422180914L;
    
    /** 图片保存的位置（相对路径） */
    private static final String UPLOAD_DIRECTORY = "uploadedImages";
    
    
    /**
     * confirmInformation，用于预约第一步：确认个人信息的方法
     */
    public String confirmInformation(HttpServletRequest request, HttpServletResponse response, Page page) {
        // 如果用户信息从 session 加载，则使用以下方法
        // Student student = (Student)request.getSession().getAttribute("user");
        // request.setAttribute("user", student);
        
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
        
        return "预约2.jsp";
    }

    /**
     * addFieldService - 用于提交现场维修的表单的处理方法
     */
    public String addFieldService(HttpServletRequest request, HttpServletResponse response, Page page) {
        // 如果用户信息从 session 加载，则使用以下方法
        // Student student = (Student)request.getSession().getAttribute("user");
        
        ApplyComponentDAO applyComponentDAO = new ApplyComponentDAOImpl();
        RepairActivityDAO repairActivityDAO = new RepairActivityDAOImpl();
        StudentDAO studentDAO = new StudentDAOImpl();
        ReservationImgUrlDAO reservationImgUrlDAO = new ReservationImgUrlDAOImpl();
        
        ParamDto dto = RequestParser.parse(request);
        Map<String, String> parameters = dto.getParamMap();
        
        Student student = studentDAO.get(Integer.parseInt(parameters.get("userId")));
        RepairActivity repairActivity = repairActivityDAO.get(Integer.parseInt(parameters.get("activity")));
        
        
        // 处理非图片部分的表单
        Reservation reservation = new Reservation();
        reservation.setState(0);    // 设置状态
        reservation.setUserID(student.getID());
        reservation.setApplicationType(0);      // 设置其类型为活动预约
        reservation.setApplicationTime(new Date().toString());  // 设置申请提交时间
        reservation.setEquipmentID(Integer.parseInt(parameters.get("device")));
        reservation.setDetail(parameters.get("issueDetail"));
        reservation.setRepairActivityID(repairActivity.getID());
        reservation.setRequiredTime(repairActivity.getTime());
        reservation.setPlace(repairActivity.getPlace());
        
        ReservationDAO reservationDAO = new ReservationDAOImpl();
        reservationDAO.addReservation(reservation);
        
        // 处理需求的零件信息
        String neededComponents = parameters.get("neededComponents");
        if (neededComponents != null) {
            String[] componentList = parameters.get("neededComponents").split(",");
            if (componentList != null) {
                for (String component : componentList) {
                    if (component.length() != 0) {
                        applyComponentDAO.add(reservation.getID(), Integer.parseInt(component));
                    }
                }
            }
        }
        
        String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        FileItem issueImage = dto.getFileMap().get("issueImage");
        String fileName = new File(issueImage.getName()).getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
        String filePath = uploadPath + File.separator + System.currentTimeMillis() + fileExtension;
        File savedImage = new File(filePath);
        try {
            issueImage.write(savedImage);
            reservationImgUrlDAO.addReservationImgUrl(filePath, reservation.getID());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 设置跳转参数
        request.setAttribute("message", "您的预约已提交");
        request.setAttribute("backLink", "makeReservation?method=confirmInformation&uid=" + request.getParameter("userId"));
        
        return "reserve-result.jsp";
    }
    
    /**
     * addDoorToDoorService - 用于申请上门维修的表单的提交
     */
    public String addDoorToDoorService(HttpServletRequest request, HttpServletResponse response, Page page) {
        // 如果用户信息从 session 加载，则使用以下方法
        // Student student = (Student)request.getSession().getAttribute("user");
        
        ApplyComponentDAO applyComponentDAO = new ApplyComponentDAOImpl();
        RepairActivityDAO repairActivityDAO = new RepairActivityDAOImpl();
        StudentDAO studentDAO = new StudentDAOImpl();
        ReservationImgUrlDAO reservationImgUrlDAO = new ReservationImgUrlDAOImpl();
        
        ParamDto dto = RequestParser.parse(request);
        Map<String, String> parameters = dto.getParamMap();
        
        Student student = studentDAO.get(Integer.parseInt(parameters.get("userId")));
        
        
        // 处理非图片部分的表单
        Reservation reservation = new Reservation();
        reservation.setState(0);    // 设置状态
        reservation.setUserID(student.getID());
        reservation.setApplicationType(1);      // 设置其类型为上门预约
        reservation.setApplicationTime(new Date().toString());  // 设置申请提交时间
        reservation.setEquipmentID(Integer.parseInt(parameters.get("device")));
        reservation.setDetail(parameters.get("issueDetail"));
        reservation.setRequiredTime(parameters.get("requiredTime"));
        reservation.setPlace(student.getAddress());
        
        ReservationDAO reservationDAO = new ReservationDAOImpl();
        reservationDAO.addReservation(reservation);
        
        // 处理需求的零件信息
        String[] neededComponents = parameters.get("neededComponents").split(",");
        if (neededComponents != null) {
            for (String component : neededComponents) {
                if (component.length() == 0) {
                    applyComponentDAO.add(reservation.getID(), Integer.parseInt(component));
                }
            }
        }
        
        String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        FileItem issueImage = dto.getFileMap().get("issueImage");
        String fileName = new File(issueImage.getName()).getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
        String filePath = uploadPath + File.separator + System.currentTimeMillis() + fileExtension;
        File savedImage = new File(filePath);
        try {
            issueImage.write(savedImage);
            reservationImgUrlDAO.addReservationImgUrl(filePath, reservation.getID());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 设置跳转参数
        request.setAttribute("message", "您的预约已提交");
        request.setAttribute("backLink", "makeReservation?method=confirmInformation&uid=" + request.getParameter("userId"));
        
        return "reserve-result.jsp";
    }
    
    //利用表单查看详细信息
    public String getForID(HttpServletRequest request, HttpServletResponse response, Page page) {
        ReservationDAOImpl reservationDAO = new ReservationDAOImpl();
        ComponentDAOImpl componentDAO = new ComponentDAOImpl();
        StudentDAOImpl studentDAO = new StudentDAOImpl();
        
        Integer id = Integer.parseInt(request.getParameter("id"));
        Reservation reservation = reservationDAO.getReservation(id);
        request.setAttribute("reservation", reservation);
        
        List<Integer> componentList = reservation.getComponentIDList();
        List<Component> components = new ArrayList<Component>();
        for(Integer cID : componentList)
        {
            Component component = componentDAO.getComponent(cID);
            components.add(component);
        }
        request.setAttribute("components", components);
        
        Integer uid = reservation.getUserID();
        Student student = studentDAO.get(uid);
        request.setAttribute("student", student);
        return "BookingDetails.jsp";
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
