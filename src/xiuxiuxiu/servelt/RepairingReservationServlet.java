package xiuxiuxiu.servelt;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
    
    /** 用于文件上传的相关组件 */
    private ServletFileUpload upload = null;
    
    /** 图片保存的位置（相对路径） */
    private static final String UPLOAD_DIRECTORY = "uploadedImages";
    
    private static final int MEMORY_THERESHOLD = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;
    
    @Override
    public void init() throws ServletException {
        super.init();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THERESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        
        upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        upload.setHeaderEncoding("UTF-8");
    }
    
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

    /**
     * addFieldService - 用于提交现场维修的表单的处理方法
     */
    public String addFieldService(HttpServletRequest request, HttpServletResponse response, Page page) {
        ApplyComponentDAO applyComponentDAO = new ApplyComponentDAOImpl();
        
        StudentDAO studentDAO = new StudentDAOImpl();
        Student student = studentDAO.get(Integer.parseInt(request.getParameter("userId")));
        
        RepairActivityDAO repairActivityDAO = new RepairActivityDAOImpl();
        RepairActivity repairActivity = repairActivityDAO.get(Integer.parseInt(request.getParameter("activity")));
        
        ReservationImgUrlDAO reservationImgUrlDAO = new ReservationImgUrlDAOImpl();
        
        // 处理非图片部分的表单
        Reservation reservation = new Reservation();
        reservation.setState(0);    // 设置状态
        reservation.setUserID(Integer.parseInt(request.getParameter("userId")));
        reservation.setApplicationType(0);      // 设置其类型为活动预约
        reservation.setApplicationTime(new Date().toString());  // 设置申请提交时间
        reservation.setEquipmentID(Integer.parseInt(request.getParameter("device")));
        reservation.setDetail(request.getParameter("issueDetail"));
        reservation.setRepairActivityID(Integer.parseInt(request.getParameter("activity")));
        reservation.setRequiredTime(repairActivity.getTime());
        reservation.setPlace(repairActivity.getPlace());
        
        ReservationDAO reservationDAO = new ReservationDAOImpl();
        reservationDAO.addReservation(reservation);
        
        // 处理需求的零件信息
        String[] neededComponents = request.getParameterValues("neededComponents");
        if (neededComponents != null) {
            for (String component : neededComponents) {
                applyComponentDAO.add(reservation.getID(), Integer.parseInt(component));
            }
        }
        
        // 处理图片上传
        String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
                        String filePath = uploadPath + File.separator + System.currentTimeMillis() + fileExtension;
                        File savedImage = new File(filePath);
                        item.write(savedImage);
                        reservationImgUrlDAO.addReservationImgUrl(filePath, reservation.getID());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 设置跳转参数
        request.setAttribute("message", "您的预约已提交");
        request.setAttribute("backLink", "makeReservation?method=confirmInformation&uid=" + request.getParameter("userId"));
        
        return "reserve-result.jsp";
    }
    
    public String addDoorToDoorService(HttpServletRequest request, HttpServletResponse response, Page page) {
        return null;
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
