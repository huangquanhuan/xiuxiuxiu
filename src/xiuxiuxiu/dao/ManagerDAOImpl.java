package xiuxiuxiu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import xiuxiuxiu.pojo.*;
import xiuxiuxiu.util.DBUtil;
public class ManagerDAOImpl implements ManagerDAO{
	
	public void addManger(Manger manger) {
		// TODO Auto-generated method stub

		String sql = "insert into manger(password,name,phone_number,access_level,address,email) values(? ,? ,? ,? ,? ,? )";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
//			ps.setLong(1, manger.getID());
			ps.setString(1, manger.getPassword());
			ps.setString(2, manger.getName());
			ps.setString(3, manger.getPhoneNumber());
			ps.setInt(4, manger.getAccessLevel());
			ps.setString(5, manger.getAddress());
			ps.setString(6, manger.getEmail());

			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				manger.setID(id);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}


	public void deleteManger(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from user where user_id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}


	public void updateManger(Manger manger) {
		// TODO Auto-generated method stub
		String sql = "update manger set name=?,password=?,address=?,email=? where id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, manger.getName());
			ps.setString(2, manger.getPassword());
			ps.setString(3, manger.getAddress());
			ps.setString(4, manger.getEmail());
			ps.setLong(5, manger.getID());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Manger getManger(int id) {
		// TODO Auto-generated method stub
		String sql = "select id,name,password,phone_number,access_level,email from manger where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				Manger manger=new Manger();
				manger.setID(rs.getInt("id"));
				manger.setName(rs.getString("name"));
				manger.setPassword(rs.getString("password"));
				manger.setPhoneNumber(rs.getString("phone_number"));
				manger.setAccessLevel(rs.getInt("access_level"));
				manger.setAddress(rs.getString("address"));
				manger.setEmail(rs.getString("email"));
				return manger;
			} else {
				System.out.println("该id不存在！！");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Manger getManger(String phoneNumber, String password) {
		// TODO Auto-generated method stub
		String sql = "select id,name,password,phone_number,access_level,email from manger where phone_number = ? and password = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, phoneNumber);
			ps.setString(2, password);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				Manger manger=new Manger();
				manger.setID(rs.getInt("id"));
				manger.setName(rs.getString("name"));
				manger.setPassword(rs.getString("password"));
				manger.setPhoneNumber(rs.getString("phone_number"));
				manger.setAccessLevel(rs.getInt("access_level"));
				manger.setAddress(rs.getString("address"));
				manger.setEmail(rs.getString("email"));
				return manger;
			} else {
				System.out.println("用户不存在或密码错误");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean isMangerExist(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from manger where id=?";
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


	@Override
	public int getTotalManger() {
		// TODO Auto-generated method stub
		return 0;
	}


}
