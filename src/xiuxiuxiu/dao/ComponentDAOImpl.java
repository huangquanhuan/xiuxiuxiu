package xiuxiuxiu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xiuxiuxiu.pojo.*;
import xiuxiuxiu.util.DBUtil;

public class ComponentDAOImpl implements ComponentDAO{

	public void addComponent(Component component) {
		// TODO Auto-generated method stub
		String sql = "insert into component(name,price,quantity,type) values(? ,? ,? ,? )";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, component.getName()); 
			ps.setDouble(2, component.getPrice());
			ps.setInt(3, component.getQuantity());
			ps.setString(4, component.getType());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				//article.setID(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void deleteComponent(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from component where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void updateComponent(Component component) {
		// TODO Auto-generated method stub
		String sql = "update component set name=?,price=?,quantity=?,type=? where id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
		    ps.setString(1, component.getName()); 
            ps.setDouble(2, component.getPrice());
            ps.setInt(3, component.getQuantity());
            ps.setString(4, component.getType());
            ps.setInt(5, component.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public Component getComponent(int id) {
		// TODO Auto-generated method stub
		String sql = "select name,price,quantity,type from component where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				Component component=new Component();
				//article.setID(rs.getString("id"));
				component.setId(id);
				component.setName(rs.getString("name"));
				component.setPrice(rs.getDouble("price"));
				component.setQuantity(rs.getInt("quantity"));
				component.setType(rs.getString("type"));
				return component;
			} else {
				System.out.println("该id不存在！！");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Component> searchComponent(String str) {
		// TODO Auto-generated method stub
		String sql = "select id,name,price,quantity,type from component ORDER BY id";
		List<Component> componentList = new ArrayList<Component>();
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				Component component = new Component();
				//reservation.setID(rs.getString("user_id"));
				component.setId(rs.getInt("id"));
                component.setName(rs.getString("name"));
                component.setPrice(rs.getDouble("price"));
                component.setQuantity(rs.getInt("quantity"));
                component.setType(rs.getString("type"));
				componentList.add(component);
			}
			return componentList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean isComponentExist(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from component where id=?";
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
	public int getTotalComponent() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
