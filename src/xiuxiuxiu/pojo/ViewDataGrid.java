package xiuxiuxiu.pojo;

/*
 * @author 黄权焕
 *对视图一行的简单封装
 */
public class ViewDataGrid {
    private String userName;//用户名
    private String studentID;//学号
    private String phoneNumber;//手机号
    private String applicationType;//维修方式
    private String activeTime;//场次时间
    private String componentType;//零件类型
    private String reservationState;//预约单状态
    private int reservationID;//预约单id
    
    public String getUserName() {
        return userName;
    }
    public String getStudentID() {
        return studentID;
    }
    public String getActiveTime() {
        return activeTime;
    }
    public String getComponentType() {
        return componentType;
    }
    public String getApplicationType() {
        return applicationType;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public int getReservationID() {
        return reservationID;
    }
    public String getReservationState() {
        return reservationState;
    }
    
    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }
    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }
    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }
    public void setReservationState(String reservationState) {
        this.reservationState = reservationState;
    }
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    };
}
