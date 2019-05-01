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

public class RepairActivityDAOImpl implements RepairActivityDAO{
	
    @Override
	public void addRepairActivity(String time, String place) {
		// TODO Auto-generated method stub
		String sql = "insert into repair_activity(time,place) values(? ,? )";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, time);
			ps.setString(2, place);
			boolean num=ps.execute();
			if (num) {
				System.out.print("插入成功");
			} else {
				System.out.print("插入失败");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

    @Override
	public void deleteRepairActivity(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from reservation where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			boolean num=ps.execute();
			if (num) {
				System.out.print("删除成功");
			} else {
				System.out.print("删除失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
	public void updateRepairActivity(int id, String time, String place) {
		// TODO Auto-generated method stub
		String sql = "update repair_activity set time=?,place=? where id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, time);
			ps.setString(2, place);
			ps.setLong(3, id);
			int num=ps.executeUpdate();
			if (num>0) {
				System.out.print("更新成功");
			} else {
				System.out.print("更新失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
	public String getRepairActivity(int id) {
		// TODO Auto-generated method stub
		String sql = "select time,place,manager_id from repair_activity where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1,id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				String time=rs.getString(1);
				String place=rs.getString(2);
				String managerId=rs.getString(3);
				String mess="时间是"+time+"地点为"+place+"管理员id为"+managerId+"";
				System.out.print("时间是"+time+"地点为"+place+"管理员id为"+managerId+"");
				return mess;
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
	public boolean isRepairActivityExist(int id) {
		// TODO Auto-generated method stub
		String sql = "select id,time,place,manager_id from repair_activity where id=?";
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
