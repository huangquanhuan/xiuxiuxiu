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

public class EquipmentDAOImpl implements EquipmentDAO{
    @Override
	public void addEquipment(String equipmentName, Integer userID) {
		// TODO Auto-generated method stub
		String sql = "insert into equipment(equipment_name,user_id) values(?,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, equipmentName);
			ps.setInt(2, userID);
			ps.execute();
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    @Override
	public void deleteEquipment(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from equipment where id =?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.execute();
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    @Override
	public void updateEquipment(String equipmentName,int id) {
		// TODO Auto-generated method stub
		String sql="update equipment set equipment_name=? where id =?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
		ps.setString(1,equipmentName);
		ps.setInt(2, id);
		ps.execute();
		ps.close();
		c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    @Override
	public String getEquipmentName(int id) {
		// TODO Auto-generated method stub
		String sql = "select equipment_name from equipment where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1,id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				String equipmentName=rs.getString(1);
				System.out.print("设备名是"+equipmentName+"");
				ps.close();
				c.close();
				return equipmentName;
			} else {
				System.out.println("该id不存在！！");
				ps.close();
				c.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
    @Override
	public String getEquipmentOwner(int id) {
		// TODO Auto-generated method stub
		String sql = "select user_id from equipment where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				String userID=rs.getString(1);
				System.out.print("用户id是"+userID+"");
				ps.close();
				c.close();
				return userID;
			} else {
				System.out.println("该id不存在！！");
				ps.close();
				c.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean isEquipmentExist(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from equipment where id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				ps.close();
				c.close();
				return true;
			} else {
				ps.close();
				c.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Integer> List(int userID) {
		String sql = "select id from equipment where user_id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			List<Integer> equimentList = new ArrayList<Integer>();
			ps.setInt(1, userID);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				equimentList.add(rs.getInt("id"));
			}
			ps.close();
			c.close();
			return equimentList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
    /**
     * @author 刘忠燏
     */
    @Override
    public List<Equipment> listEquipmentsByUser(Integer userId) {
        List<Equipment> equipments = new ArrayList<Equipment>();
        String sql = "select id, equipment_name from equipment where user_id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Equipment entity = new Equipment();
                entity.setId(rs.getInt("id"));
                entity.setEquipmentName(rs.getString("equipment_name"));
                entity.setUserId(userId);
                equipments.add(entity);
            }
            ps.close();
    		c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return equipments;
    }
}
