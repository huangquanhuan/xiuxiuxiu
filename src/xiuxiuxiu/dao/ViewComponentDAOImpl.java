package xiuxiuxiu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import xiuxiuxiu.util.*;
import xiuxiuxiu.pojo.*;

public class ViewComponentDAOImpl {
    
    
    /**
     * 查询视图
     * @param reservationState 3种状态：0表示未受理状态，1表示已受理未完成状态，2表示已完成状态
     * @param applicationType 2种预约类型：0表示活动预约，1表示上门维修预约
     * */
    public List<ViewDataGrid> getList(String name,int applicationType,int activityID,String componentType, int reservationState) {
        List<ViewDataGrid> lists = new LinkedList<ViewDataGrid>();
        Reservation reservation = new Reservation();
        String sql = "SELECT U.user_name, U.student_id, U.phone_number, RV.application_type, RA.time, concat(C.name,\"-\",C.type), RV.state, RV.id "
                + "FROM\r\n" + "    repair_activity AS RA\r\n"
                + "    LEFT JOIN reservation AS RV ON RA.id = RV.repair_activity_id\r\n"
                + "    LEFT JOIN `user` AS U ON RV.user_id = U.user_id\r\n"
                + "    LEFT JOIN apply_component AS AC ON RV.id = AC.reservation_id\r\n"
                + "    LEFT JOIN component AS C ON AC.component_id = C.id \r\n" + "WHERE 1=1 ";
        if(!name.equals("")) {
            sql += " U.user_name=?";
        }
        if(applicationType <= 1 && applicationType >= 0) {
            sql += " RV.application_type=?";
        }
        if(activityID != -1) {
            sql += " RA.id=?";
        }
        if(!componentType.equals("")) {
            sql += " C.type=?";
        }
        if(reservationState <= 2 && reservationState >= 0) {
            sql += " RV.state=?";
        }
        sql += "ORDER BY C.id";
        try {
                Connection c = DBUtil.getConnection(); 
                PreparedStatement ps = c.prepareStatement(sql) ;
            int index = 1;
            if(!name.equals("")) {
                ps.setString(index++, name);
            }
            if(applicationType <= 1 && applicationType >= 0) {
                ps.setInt(index++, applicationType);
            }
            if(activityID != -1) {
                ps.setInt(index++, activityID);
            }
            if(!componentType.equals("")) {
                ps.setString(index++, componentType);
            }
            if(reservationState <= 2 && reservationState >= 0) {
                ps.setInt(index++, reservationState);
            }
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                //单独取出进行转换int->string
                reservation.setApplicationType(rs.getInt("RV.application_type"));
                reservation.setState(rs.getInt("RV.state"));
                // 填充数据
                ViewDataGrid bean = new ViewDataGrid();
                bean.setUserName(rs.getString("U.user_name"));
                bean.setStudentID(rs.getString("U.student_id"));
                bean.setPhoneNumber(rs.getString("U.phone_number"));
                bean.setApplicationType(reservation.getApplicationType());
                bean.setActiveTime(rs.getString("RA.time"));
                bean.setComponentType(rs.getString("concat(C.name,\"-\",C.type)"));
                bean.setReservationState(reservation.getState());
                bean.setReservationID(rs.getInt("RV.id"));

                lists.add(bean);
            }
            return lists;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } 
    }
}
