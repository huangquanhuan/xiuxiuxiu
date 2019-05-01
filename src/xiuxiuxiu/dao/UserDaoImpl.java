package xiuxiuxiu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import xiuxiuxiu.pojo.Student;
import xiuxiuxiu.util.DBUtil;

public class UserDaoImpl implements UserDao {
    @Override
    public void addStudent(Student bean) {
        String sql = "insert into user(password,user_name,phone_number,access_level,student_id,address,e_mail) values(? ,? ,? ,? ,? ,? ,? )";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, bean.getPassword());
            ps.setString(2, bean.getName());
            ps.setString(3, bean.getPhoneNumber());
            ps.setInt(4, bean.getAccessLevel());
            ps.setString(5, bean.getStudentID());
            ps.setString(6, bean.getAddress());
            ps.setString(7, bean.getEmail());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setID(id);
            }
            
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int id) {
        String sql = "delete from user where user_id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    /**
     * 只可修改用户的姓名、密码、学号、住址、电子邮箱，账户(ID)、手机号、权限等级不可修改
     */
    @Override
    public void updateStudent(Student bean) {
        String sql = "update user set user_name=?,password=?,student_id=?,address=?,e_mail=? where id=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.setString(3, bean.getStudentID());
            ps.setString(4, bean.getAddress());
            ps.setString(5, bean.getEmail());
            ps.setInt(6, bean.getID());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*
     * 根据id获取整个用户信息
     * (non-Javadoc)
     * @see dao.DAO#getStudent(java.lang.String)
     */
    @Override
    public Student getStudent(int id) {
        String sql = "select user_id,user_name,password,phone_number,access_level,student_id,address,e_mail from user where user_id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
            	EquipmentDAO equipmentDao = new  EquipmentDAOImpl();
                Student bean = new Student();
                bean.setID(rs.getInt("user_id"));
                bean.setName(rs.getString("user_name"));
                bean.setPassword(rs.getString("password"));
                bean.setPhoneNumber(rs.getString("phone_number"));
                bean.setAccessLevel(rs.getInt("access_level"));
                bean.setStudentID(rs.getString("student_id"));
                bean.setAddress(rs.getString("address"));
                bean.setEmail(rs.getString("e_mail"));
                bean.setEquipment(equipmentDao.List(id));//根据用户id获取设备id列表
                return bean;
            } else {
                System.out.println("该id不存在！！");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 可用于登录验证
     * (non-Javadoc)
     * @see dao.DAO#getStudent(java.lang.String, java.lang.String)
     */
    @Override
    public Student getStudent(int id, String password) {
        String sql = "select user_id,user_name,password,phone_number,access_level,student_id,address,e_mail from user where user_id = ? and password = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.setString(2, password);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
            	EquipmentDAO equipmentDao = new  EquipmentDAOImpl();
                Student bean = new Student();
                bean.setID(rs.getInt("user_id"));
                bean.setName(rs.getString("user_name"));
                bean.setPassword(rs.getString("password"));
                bean.setPhoneNumber(rs.getString("phone_number"));
                bean.setAccessLevel(rs.getInt("access_level"));
                bean.setStudentID(rs.getString("student_id"));
                bean.setAddress(rs.getString("address"));
                bean.setEmail(rs.getString("e_mail"));
                bean.setEquipment(equipmentDao.List(id));//根据用户id获取设备id列表
                return bean;
            } else {
                System.out.println("用户不存在或密码错误");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

/*该方法为排序样例
 * 
 * (non-Javadoc)
 * @see dao.DAO#searchStudent(java.lang.String)
 */
    @Override
    public List<Student> searchStudent(String condition) {
        String sql = "select user_id,user_name,password,phone_number,access_level,student_id,address,e_mail from user ORDER BY user_id";
        List<Student> userList = new ArrayList<Student>();
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.execute();
            ResultSet rs = ps.getResultSet();
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
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public int getTotalStudent() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select count(*) from user";

            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                total = rs.getInt(1);
                return total;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean isStudentExist(int id) {
        String sql = "select * from User where id=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

}
