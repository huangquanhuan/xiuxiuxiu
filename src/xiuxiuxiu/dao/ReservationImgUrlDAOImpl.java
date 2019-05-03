package xiuxiuxiu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xiuxiuxiu.util.DBUtil;

public class ReservationImgUrlDAOImpl implements ReservationImgUrlDAO{

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


	public void delete(int reservationID) {
		String sql = "delete from reservation_img_url where reservation_id =?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, reservationID);
			boolean num=ps.execute();
			if (num) {
				System.out.print("删除该预约单对应的图片URL成功");
			} else {
				System.out.print("删除该预约单对应的图片URL失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void updateReservationImgUrl(int id) {
		// TODO Auto-generated method stub
		String sql="update repair_activity set id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
		ps.setLong(1, id);
		boolean num=ps.execute();
		if (num) {
			System.out.print("更新图片URL成功");
		} else {
			System.out.print("更新图片URL失败");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public String getReservationImgUrl(int id) {
		// TODO Auto-generated method stub
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


	public int getReservationImgUrlOwnerID(int id) {
		// TODO Auto-generated method stub
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
	public List<String> List(int reservationID) {
		String sql = "select img_url from reservation_img_url where reservation_id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			List<String> imgUrlList = new ArrayList<String>();
			ps.setInt(1, reservationID);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				imgUrlList.add(rs.getString("img_url"));
			}
			return imgUrlList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public boolean isExist(int id) {
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
