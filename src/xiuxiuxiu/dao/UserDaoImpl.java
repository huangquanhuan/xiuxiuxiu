package xiuxiuxiu.dao;

import java.sql.*;
import java.util.*;
import xiuxiuxiu.pojo.*;
import xiuxiuxiu.util.*;
import xiuxiuxiu.util.*;


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
    @Override
    public Student get(int id) {
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
    public Student get(String id, String password) {
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
    
    @Override
    public List<Student> listAll() {
        List<Student> temp = null ;
        return temp;
    };
    
    @Override
    public List<Student> listByPage(int pageNo, int pageSize) {
        List<Student> temp = null ;
        return temp;
    }
    
    @Override
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select count(*) from User";
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