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

public class ReservationDAOImpl implements ReservationDAO {

	
	public void addReservation(Reservation reservation) {

		String sql = "insert into reservation(state,user_id,application_type,application_time,required_time"
				+ ",place,repair_activity_id,equipment_id,repair_type,detail,remark,feedback) values(? ,? ,? ,? ,? ,? ,? ,?,?,?,?,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, reservation.getState());
			ps.setInt(2, reservation.getUserID());
			ps.setInt(3, reservation.getApplicationType());
			ps.setString(4, reservation.getApplicationTime());
			ps.setString(5, reservation.getRequiredTime());
			ps.setString(6, reservation.getPlace());
			ps.setInt(7, reservation.getRepairActivityID());
			ps.setInt(8, reservation.getEquipmentID());
			ps.setString(9, reservation.getRepairType());
			ps.setString(10, reservation.getDetail());
			ps.setString(11, reservation.getRemark());
			ps.setString(12, reservation.getFeedback());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			ps.close();
			if (rs.next()) {
				int id = rs.getInt(1);	//获取被加入的预约单的id
				reservation.setID(id);
				
				//插入apply_component（预约单-零件对应表）
				sql = "insert into apply_component(reservation_id,component_id) values(?,?)";
				PreparedStatement ps2 = c.prepareStatement(sql);
				for(int ComponentID : reservation.getComponentIDList()) {
					ps2.setInt(1, id);
					ps2.setInt(2, ComponentID);
					ps2.executeUpdate();
				}
				ps2.close();
				
				//插入reservation_img_url（预约单-图片URL对应表）
				sql = "insert into reservation_img_url(reservation_id,img_url) values(?,?)";
				PreparedStatement ps3 = c.prepareStatement(sql);
				for(String imgUrl : reservation.getImgUrlList()) {
					ps3.setInt(1, id);
					ps3.setString(2, imgUrl);
					ps3.executeUpdate();
				}
				ps3.close();
			}
			ps.close();
			c.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void deleteReservation(int id) {
		String sql = "delete from reservation where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			c.close();
		} catch (SQLException e) {
			System.out.println("删除预约单失败！");
			e.printStackTrace();
		}
	}

	public void updateReservation(Reservation reservation) {

		String sql = "update reservation set userid=?,state=?,application_type=?,application_time=?,"
				+ "required_time=?,place=?,repair_activity_id=?,equipment_id=?,repair_type=?,detail=?,remark=?,feedback=? where id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, reservation.getUserID());
			ps.setInt(2, reservation.getState());
			ps.setInt(3, reservation.getApplicationType());
			ps.setString(4, reservation.getApplicationTime());
			ps.setString(5, reservation.getRequiredTime());
			ps.setString(6, reservation.getPlace());
			ps.setInt(7, reservation.getRepairActivityID());
			ps.setInt(8, reservation.getEquipmentID());
			ps.setString(9, reservation.getRepairType());
			ps.setString(10, reservation.getDetail());
			ps.setString(11, reservation.getRemark());
			ps.setString(12, reservation.getFeedback());
			ps.setInt(13, reservation.getID());
			ps.executeUpdate();
			
			//更新apply_component（预约单-零件对应表）-其实就是先删除原有记录，在添加修改后的记录
			ApplyComponentDAO applyComponentDao = new ApplyComponentDAOImpl();
			applyComponentDao.delete(reservation.getID());	//删除
			sql = "insert into apply_component(reservation_id,component_id) values(?,?)";
			PreparedStatement ps2 = c.prepareStatement(sql);
			for(int ComponentID : reservation.getComponentIDList()) {
				ps2.setInt(1, reservation.getID());
				ps2.setInt(2, ComponentID);
				ps2.executeUpdate();	//添加
			}
			ps2.close();
			
			//更新reservation_img_url（预约单-图片URL对应表）-其实就是先删除原有记录，在添加修改后的记录
			ReservationImgUrlDAO reservationImgUrlDao = new ReservationImgUrlDAOImpl();
			reservationImgUrlDao.delete(reservation.getID());	//删除
			sql = "insert into reservation_img_url(reservation_id,img_url) values(?,?)";
			PreparedStatement ps3 = c.prepareStatement(sql);
			for(String imgUrl : reservation.getImgUrlList()) {
				ps3.setInt(1, reservation.getID());
				ps3.setString(2, imgUrl);
				ps3.executeUpdate();	//添加
			}
			ps3.close();
			
			ps.close();
			c.close();
		} catch (SQLException e) {
			System.out.println("更新预约单失败！");
			e.printStackTrace();
		}
	}

	public Reservation getReservation(int id) {
		String sql = "select state,user_id,application_type,application_time,required_time,place,repair_activity_id,equipment_id,repair_type,detail,remark,feedback from reservation where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				Reservation reservation = new Reservation();
				reservation.setID(id);
				reservation.setState(rs.getInt("state"));
				reservation.setUserID(rs.getInt("user_id"));
				reservation.setApplicationType(rs.getInt("application_type"));
				reservation.setApplicationTime(rs.getString("equipment_id"));
				reservation.setRequiredTime(rs.getString("required_time"));
				reservation.setPlace(rs.getString("place"));
				reservation.setRepairActivityID(rs.getInt("repair_activity_id"));
				reservation.setEquipmentID(rs.getInt("equipment_id"));
				reservation.setRepairType(rs.getString("repair_type"));
				reservation.setDetail(rs.getString("detail"));
				reservation.setRemark(rs.getString("remark"));
				reservation.setFeedback(rs.getString("feedback"));

				ApplyComponentDAO applyComponentDao = new ApplyComponentDAOImpl();
				reservation.setComponentIDList(applyComponentDao.List(id)); // 根据预约单id获取其包含的零件id列表并将列表set进该预约单的信息中
				ReservationImgUrlDAO ImgUrlDao = new ReservationImgUrlDAOImpl();
				reservation.setImgUrlList(ImgUrlDao.List(id));// 根据预约单id获取其包含的图片url列表并将列表set进该预约单的信息中

				return reservation;
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
	public List<Reservation> List(int userID) {
		String sql = "select id from reservation where user_id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, userID);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			List<Reservation> reservationList = new ArrayList<Reservation>();
			while (rs.next()) {
				reservationList.add(getReservation(rs.getInt("id")));
			}
			return reservationList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean isExist(int id) {

		String sql = "select * from reservation where id=?";
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
	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
			String sql = "select count(*) from reservation";
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
}
