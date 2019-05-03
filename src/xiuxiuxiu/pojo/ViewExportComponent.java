package xiuxiuxiu.pojo;
/**
 * @author 黄权焕
 * 视图一行的封装
 * 包括学生-预约单-零件
 * */
public class ViewExportComponent {
    private Student student = new Student();
    private Reservation reservation = new Reservation();
    private Component component = new Component();
    
    public Component getComponent() {
        return component;
    }
    public Reservation getReservation() {
        return reservation;
    }
    public Student getStudent() {
        return student;
    }
    public void setComponent(Component component) {
        this.component = component;
    }
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
}
