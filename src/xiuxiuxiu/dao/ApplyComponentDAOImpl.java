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

public class ApplyComponentDAOImpl implements ApplyComponentDAO{

	public void add(int reservationID, int componentID) {
		String sql = "insert into apply_component(reservation_id,component_id) values(?,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, reservationID);
			ps.setInt(2, componentID);
			boolean flag=ps.execute();
			if (flag) {
				System.out.print("插入预约单id-零件id：（"+reservationID+"-"+componentID+"）成功");
			} else {
				System.out.print("插入失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		String sql = "delete from apply_component where id =?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
			boolean num=ps.execute();
			if (num) {
				System.out.print("删除预约单id-零件id成功");
			} else {
				System.out.print("没有找到要删除的内容");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public int getReservationID(int id) {
		String sql = "select reservation_id from apply_component where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				int reservationID=rs.getInt("reservation_id");
				return reservationID;
			} else {
				System.out.println("对应id不存在！！");
				return -1;
			}
		} catch (SQLException e) {
			System.out.println("获取数据失败！！");
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public int getComponentID(int id) {
		String sql = "select component_id from apply_component where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				int componentID=rs.getInt("component_id");
				return componentID;
			} else {
				System.out.println("对应id不存在！！");
				return -1;
			}
		} catch (SQLException e) {
			System.out.println("获取数据失败！！");
			e.printStackTrace();
			return -1;
		}
	}

	public boolean isExist(int id) {
		String sql = "select * from apply_component where id=?";
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
	public List<Integer> List(int reservationID) {
		String sql = "select component_id from apply_component where reservation_id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			List<Integer> componentList = new ArrayList<Integer>();
			ps.setInt(1, reservationID);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				componentList.add(rs.getInt("component_id"));
			}
			return componentList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
