package xiuxiuxiu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import xiuxiuxiu.pojo.Student;
import xiuxiuxiu.util.DBUtil;

public class UserDaoImpl1 {

    public void addStudent(Student bean) throws SQLException {

        String sql = "insert into user(user_id,password,user_name,phone_number,access_level,student_id,address,e_mail) values(? ,? ,? ,? ,? ,? ,? ,?)";
        List<Object> list = new LinkedList<Object>();
        list.add(bean.getID());
        list.add(bean.getPassword());
        list.add(bean.getName());
        list.add(bean.getPhoneNumber());
        list.add( bean.getAccessLevel());
        list.add(bean.getStudentID());
        list.add(bean.getAddress());
        list.add(bean.getEmail());
        BaseDao.executeUpdate(sql, list);
        ResultSet rs = BaseDao.getGeneratedKeys(sql, list);
        if (rs.next()) {
            int id = rs.getInt(1);
            bean.setID(id);
        }
    }

   
    public void deleteStudent(int id) throws SQLException {
        String sql = "delete from user where user_id = ?";
        List<Object> list = new LinkedList<Object>();
        list.add(id);
        BaseDao.executeUpdate(sql, list);
    }

    /**
     * 只可修改用户的姓名、密码、学号、住址、电子邮箱，账户(ID)、手机号、权限等级不可修改
     */
   
    public void updateStudent(Student bean) throws SQLException {
        String sql = "update user set user_name=?,password=?,student_id=?,address=?,e_mail=? where id=?";
        List<Object> list = new LinkedList<Object>();
        list.add(bean.getName());
        list.add(bean.getPassword());
        list.add(bean.getStudentID());
        list.add(bean.getAddress());
        list.add(bean.getEmail());
        list.add(bean.getID());
        BaseDao.executeUpdate(sql, list);
    }

    /*
     * 根据id获取整个用户信息
     * (non-Javadoc)
     * @see dao.DAO#getStudent(java.lang.String)
     */
   
    public Student getStudent(int id) throws SQLException {
        String sql = "select user_id,user_name,password,phone_number,access_level,student_id,address,e_mail from user where user_id = ?";
        List<Object> list = new LinkedList<Object>();
        list.add(id);
        ResultSet rs = BaseDao.executeQuery(sql, list);
        if (rs.next()) {
            Student bean = new Student();
            bean.setID(rs.getInt("user_id"));
            bean.setName(rs.getString("user_name"));
            bean.setPassword(rs.getString("password"));
            bean.setPhoneNumber(rs.getString("phone_number"));
            bean.setAccessLevel(rs.getInt("access_level"));
            bean.setStudentID(rs.getString("student_id"));
            bean.setAddress(rs.getString("address"));
            bean.setEmail(rs.getString("e_mail"));
            return bean;
        } else {
            System.out.println("该id不存在！！");
            return null;
        }
    }

    /*
     * 可用于登录验证
     * (non-Javadoc)
     * @see dao.DAO#getStudent(java.lang.String, java.lang.String)
     */
    public Student getStudent(int id, String password) throws SQLException {
        String sql = "select user_id,user_name,password,phone_number,access_level,student_id,address,e_mail from user where user_id = ? and password = ?";
        List<Object> list = new LinkedList<Object>();
        list.add(id);
        list.add(password);
        ResultSet rs = BaseDao.executeQuery(sql, list);
        if (rs.next()) {
            Student bean = new Student();
            bean.setID(rs.getInt("user_id"));
            bean.setName(rs.getString("user_name"));
            bean.setPassword(rs.getString("password"));
            bean.setPhoneNumber(rs.getString("phone_number"));
            bean.setAccessLevel(rs.getInt("access_level"));
            bean.setStudentID(rs.getString("student_id"));
            bean.setAddress(rs.getString("address"));
            bean.setEmail(rs.getString("e_mail"));
            return bean;
        } else {
            System.out.println("用户不存在或密码错误");
            return null;
        }
    }

/*该方法为排序样例
 * 
 * (non-Javadoc)
 * @see dao.DAO#searchStudent(java.lang.String)
 */
    public List<Student> searchStudent(String condition) throws SQLException {
        String sql = "select user_id,user_name,password,phone_number,access_level,student_id,address,e_mail from user ORDER BY user_id";
        List<Student> userList = new ArrayList<Student>();
        List<Object> list = new LinkedList<Object>();
        ResultSet rs = BaseDao.executeQuery(sql, list);
        while (rs.next()) {
            Student bean = new Student();
            bean.setID(rs.getInt("user_id"));
            bean.setName(rs.getString("user_name"));
            bean.setPassword(rs.getString("password"));
            bean.setPhoneNumber(rs.getString("phone_number"));
            bean.setAccessLevel(rs.getInt("access_level"));
            bean.setStudentID(rs.getString("student_id"));
            bean.setAddress(rs.getString("address"));
            bean.setEmail(rs.getString("e_mail"));
            userList.add(bean);
        }
        return userList;        
    }
    
   
    public int getTotalStudent() throws SQLException {
        int total = 0;
        String sql = "select count(*) from Student";
        List<Object> list = new LinkedList<Object>();
        ResultSet rs = BaseDao.executeQuery(sql, list);
        if (rs.next()) {
            total = rs.getInt(1);
            return total;
        } else {
            return 0;
        }
    }

   
    public boolean isStudentExist(int id) throws SQLException {
        String sql = "select * from User where id=?";
        List<Object> list = new LinkedList<Object>();
        list.add(id);
        ResultSet rs = BaseDao.executeQuery(sql, list);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }
    
}
