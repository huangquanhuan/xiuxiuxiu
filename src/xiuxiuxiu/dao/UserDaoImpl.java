package xiuxiuxiu.dao;

import java.sql.*;
import java.util.*;
import xiuxiuxiu.pojo.*;
import xiuxiuxiu.util.*;
import xiuxiuxiu.util.*;


/*
 * 使用PreparedStatement可以结解SQL注入的问题
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void add(User bean) {

        String sql = "insert into user(user_id,password,user_name,phone_number,access_level,student_id,address,e_mail) values(? ,? ,? ,? ,? ,? ,? ,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, bean.getId());
            ps.setString(2, bean.getPassword());
            ps.setString(3, bean.getName());
            ps.setString(4, bean.getPhoneNumber());
            ps.setInt(5, bean.getAccessLevel());
            ps.setString(6, bean.getStudentId());
            ps.setString(7, bean.getAddress());
            ps.setString(8, bean.getEmail());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
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
    public void delete(User entity) {
        Integer id = entity.getId();
        delete(id);
    }

    /*
     * 只可修改用户的姓名、密码、学号、住址、电子邮箱，账户(ID)、手机号、权限等级不可修改
     * (non-Javadoc)
     * @see dao.DAO#updateUser(pojo.User)
     */
    @Override
    public void update(User bean) {
        String sql = "update user set user_name=?,password=?,student_id=?,address=?,e_mail=? where id=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.setString(3, bean.getStudentId());
            ps.setString(4, bean.getAddress());
            ps.setString(5, bean.getEmail());
            ps.setInt(6, bean.getId());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*
     * 根据id获取整个用户信息
     * (non-Javadoc)
     * @see dao.DAO#getUser(java.lang.String)
     */
    @Override
    public User get(int id) {
        String sql = "select user_id,user_name,password,phone_number,access_level,student_id,address,e_mail from user where user_id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                User bean = new User();
                bean.setId(rs.getInt("user_id"));
                bean.setName(rs.getString("user_name"));
                bean.setPassword(rs.getString("password"));
                bean.setPhoneNumber(rs.getString("phone_number"));
                bean.setAccessLevel(rs.getInt("access_level"));
                bean.setStudentId(rs.getString("student_id"));
                bean.setAddress(rs.getString("address"));
                bean.setEmail(rs.getString("e_mail"));
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
     * @see dao.DAO#getUser(java.lang.String, java.lang.String)
     */
    public User get(String id, String password) {
        String sql = "select user_id,user_name,password,phone_number,access_level,student_id,address,e_mail from user where user_id = ? and password = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, password);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                User bean = new User();
                bean.setId(rs.getInt("user_id"));
                bean.setName(rs.getString("user_name"));
                bean.setPassword(rs.getString("password"));
                bean.setPhoneNumber(rs.getString("phone_number"));
                bean.setAccessLevel(rs.getInt("access_level"));
                bean.setStudentId(rs.getString("student_id"));
                bean.setAddress(rs.getString("address"));
                bean.setEmail(rs.getString("e_mail"));
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
    
    @Override
    public List<User> listAll() {
        List<User> temp = null ;
        return temp;
    };
    
    @Override
    public List<User> listByPage(int pageNo, int pageSize) {
        List<User> temp = null ;
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
    public boolean isExist(String id) {
        String sql = "select * from User where id=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
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