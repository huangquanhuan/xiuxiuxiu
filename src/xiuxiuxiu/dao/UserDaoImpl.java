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

/*
 * 使用PreparedStatement可以解决SQL注入的问题
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void add(Student student) {

    	String sql = "insert into user(password,user_name,phone_number,access_level,student_id,address,e_mail) values(? ,? ,? ,? ,? ,? ,? )";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, student.getPassword());
			ps.setString(2, student.getName());
			ps.setString(3, student.getPhoneNumber());
			ps.setInt(4, student.getAccessLevel());
			ps.setString(5, student.getStudentID());
			ps.setString(6, student.getAddress());
			ps.setString(7, student.getEmail());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				student.setID(id);
			}
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    @Override
    public void delete(Integer id) {
        String sql = "delete from user where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void delete(Student entity) {
        Integer id = entity.getID();
        delete(id);
    }

    /**
     * 只可修改用户的姓名、密码、学号、住址、电子邮箱(账户(ID)、手机号、权限等级不可修改)
     */
    @Override
    public void update(Student student) {
        String sql = "update user set user_name=?,password=?,student_id=?,address=?,e_mail=? where id=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getPassword());
            ps.setString(3, student.getStudentID());
            ps.setString(4, student.getAddress());
            ps.setString(5, student.getEmail());
            ps.setInt(6, student.getID());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据id获取User表中的整个用户信息
     */
   
    public Student getStudent(int id) throws SQLException {
        String sql = "select user_id,user_name,password,phone_number,access_level,student_id,address,e_mail from user where user_id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                Student student = new Student();
                student.setID(rs.getInt("user_id"));
                student.setName(rs.getString("user_name"));
                student.setPassword(rs.getString("password"));
                student.setPhoneNumber(rs.getString("phone_number"));
                student.setAccessLevel(rs.getInt("access_level"));
                student.setStudentID(rs.getString("student_id"));
                student.setAddress(rs.getString("address"));
                student.setEmail(rs.getString("e_mail"));
                return student;
            } else {
                System.out.println("该id不存在！！");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据id和密码获取整个用户信息，可用于登录验证
     * @return 如果无匹配
     */
    public Student getStudent(int id, String password) throws SQLException {
        String sql = "select user_id,user_name,password,phone_number,access_level,student_id,address,e_mail from user where user_id = ? and password = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, password);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                Student student = new Student();
                student.setID(rs.getInt("user_id"));
                student.setName(rs.getString("user_name"));
                student.setPassword(rs.getString("password"));
                student.setPhoneNumber(rs.getString("phone_number"));
                student.setAccessLevel(rs.getInt("access_level"));
                student.setStudentID(rs.getString("student_id"));
                student.setAddress(rs.getString("address"));
                student.setEmail(rs.getString("e_mail"));
                return student;
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

    
    @Override
    public boolean isExist(int id) {
        String sql = "select * from User where id=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
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

	@Override
	public boolean isExist(String phoneNumber) {
		String sql = "select * from User where phoneNumber=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, phoneNumber);
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
