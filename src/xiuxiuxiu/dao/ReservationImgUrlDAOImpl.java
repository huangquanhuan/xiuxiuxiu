package xiuxiuxiu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import xiuxiuxiu.util.DBUtil;

public class ReservationImgUrlDAOImpl implements ReservationImgUrlDAO{
    @Override
	public void addReservationImgUrl(String imgUrl, int reservationID) {
		String sql = "insert into reservation_img_url(img_url,reservation_id) values(?,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, imgUrl);
			ps.setInt(2, reservationID);
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
	public void deleteReservationImgUrl(int id) {
		String sql = "delete from reservation_img_url where id =?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			boolean num=ps.execute();
			if (num) {
				System.out.print("删除设备成功");
			} else {
				System.out.print("删除设备失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
	public void updateReservationImgUrl(int id) {
		String sql="update repair_activity set id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
		ps.setLong(1, id);
		boolean num=ps.execute();
		if (num) {
			System.out.print("更新设备表成功");
		} else {
			System.out.print("更新设备表失败");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
	public String getReservationImgUrl(int id) {
		String sql = "select img_url from reservation_img_url where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				String imgUrl=rs.getString(1);
				System.out.print("图片URL是"+imgUrl+"");
				return imgUrl;
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
	public int getReservationImgUrlOwnerID(int id) {
		String sql = "select reservation_id from reservation_img_url where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				int reservationID=rs.getInt("reservation_id");
				System.out.print("预约单id是"+reservationID+"");
				return reservationID;
			} else {
				System.out.println("该id不存在！！");
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

    @Override
	public boolean isReservationImgUrlExist(int id) {
		String sql = "select * from reservation_img_url where id=?";
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
