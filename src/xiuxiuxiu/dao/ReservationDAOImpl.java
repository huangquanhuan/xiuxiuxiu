package xiuxiuxiu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xiuxiuxiu.pojo.*;
import xiuxiuxiu.util.DBUtil;

public class ReservationDAOImpl implements ReservationDAO{
    @Override
	public void addReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		String sql = "insert into reservation(id,state,user_id,application_type,application_time,required_time,place,repair_activity_id,equipment_id,repair_type,detail,remark,feedback) values(? ,? ,? ,? ,? ,? ,? ,?,?,?,?,?,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			//ps.setString(1, reservation.getID());    
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				//reservation.setID(id);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

    @Override
	public void deleteReservation(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from reservation where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
 
    @Override
	public void updateReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		String sql = "update reservation set userid=?,state=?,application_type=?,application_time=?,required_time=?,place=?,repair_activity_id=?,equipment_id=?,repair_type=?,detail=?,remark=?,feedback=? where id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			//ps.setString(1, bean.getName());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
	public Reservation getReservation(int id) {
		// TODO Auto-generated method stub
		String sql = "select state,user_id,application_type,application_time,required_time,place,repair_activity_id,equipment_id,repair_type,detail,remark,feedback from reservation where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				Reservation reservation = new Reservation();
				//reservation.setID(rs.getString("user_id"));
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
	public List<Reservation> searchReservation(String condition) {
		// TODO Auto-generated method stub
		String sql = "select id,state,user_id,application_type,application_time,required_time,place,repair_activity_id,equipment_id,repair_type,detail,remark,feedback from reservation ORDER BY id";
		List<Reservation> reservationList = new ArrayList<Reservation>();
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				Reservation reservation = new Reservation();
				//reservation.setID(rs.getString("user_id"));
				reservationList.add(reservation);
			}
			return reservationList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
    
    @Override
	public boolean isReservationExist(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from reservation where id=?";
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
	public int getTotalReservation() {
		// TODO Auto-generated method stub
		return 0;
	}
}
