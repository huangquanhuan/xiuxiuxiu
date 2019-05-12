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
    @Override
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

    @Override
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

    @Override
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
    
    @Override
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
    
    @Override
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

    @Override
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
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

			String sql = "select count(*) from manger";

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
	public List<Manger> listAll() {
		// TODO Auto-generated method stub
		String sql = "select id,name,password,phone_number,access_level,student_id,address,e_mail from student ORDER BY id";
		List<Manger> mangerList = new ArrayList<Manger>();
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				EquipmentDAO equipmentDao = new EquipmentDAOImpl();
				Manger bean = new Manger();
				bean.setID(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setPassword(rs.getString("password"));
				bean.setPhoneNumber(rs.getString("phone_number"));
				bean.setAccessLevel(rs.getInt("access_level"));
				bean.setAddress(rs.getString("address"));
				bean.setEmail(rs.getString("e_mail"));
				mangerList.add(bean);
			}
			return mangerList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Manger> listByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Manger> list(int start, int count) {
		// TODO Auto-generated method stub
				String sql = "select * from manger";
				List<Manger> managerList = new ArrayList<Manger>();
		        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
		            ps.execute();
		            ResultSet rs = ps.getResultSet();
		            int n=0;
		            while(rs.next()) 
		            {
		            	Manger bean = new Manger();
		            	if( n>=start && n<(start+count) )
		            	{
		            		bean.setID(rs.getInt(1));
		            		bean.setName(rs.getString(2));
		               		bean.setPhoneNumber(rs.getString(4));
		            		bean.setAddress(rs.getString(6));
		            		bean.setEmail(rs.getString(7));
		            		managerList.add(bean);
		            	}          
		            	n++;
		            }
		            return managerList;
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
				return null;
	}


}
