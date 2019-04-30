package xiuxiuxiu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import xiuxiuxiu.util.DBUtil;

public class RepairTypeDAOImpl implements RepairTypeDAO{

	/*
	 * isnull语句是判断该id是否存有属性值，如果存在单个属性那插入另一个属性用update语句
	 * 如果该id不存在则用insert插入一个新行
	 * @param type 维修类型，1表示硬件，2表示软件
	 * (non-Javadoc)
	 * @see dao.DAO#getRepairType(int, int)
	 */

	public void addRepairType(int id,int type, String content) {
		// TODO Auto-generated method stub
		String isnull = "select id,software,hardware from repair_type where id=? and (hardware is null or software is null)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(isnull)) {
			ps.setLong(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				String sql = "";
				if(type==1) {
					sql="update repair_type set hardware=? where id =?";
				}else {
					sql="update repair_type set software=? where id =?";
				}
				PreparedStatement ps1 = c.prepareStatement(sql);
				ps1.setString(1, content);
				ps1.setLong(2, id);
				boolean num=ps1.execute();
				if (!num) {
					System.out.print("增加 成功");
				} else {
					System.out.print("增加失败");
				}
			} else {
				String sql="";
				if(type==1) {
					sql="insert into repair_type(id,hardware) values(?,?)";
				}else {
					sql="insert into repair_type(id,software) values(?,?)";
				}
				PreparedStatement ps1 = c.prepareStatement(sql);
				ps1.setLong(1, id);
				ps1.setString(2, content);
				int num = ps1.executeUpdate();
				if(num>0) {
					System.out.print("增加成功");
				} else {
					System.out.print("增加失败");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/*
	 * isnull语句是判断这个id中是否只有一个属性，如果只有硬件或者软件的单个属性就把id整行删掉
	 * 如果硬件或软件都有就把要“删除”的属性变为null值
	 * (non-Javadoc)
	 * @see dao.DAO#deleteRepairType(int, int, java.lang.String)
	 */

	public void deleteRepairType(int id, int type) {
		// TODO Auto-generated method stub
		String isnull = "select id,software,hardware from repair_type where id=? and (hardware is null or software is null)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(isnull)) {
			ps.setLong(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				String sql = "delete from repair_type where id = ?";
				PreparedStatement ps1 = c.prepareStatement(sql);
				ps1.setLong(1, id);
				boolean num=ps1.execute();
				if (!num) {
					System.out.print("删除成功");
				} else {
					System.out.print("删除失败");
				}
			} else {
				String sql="";
				if(type==1) {
					sql="update repair_type set hardware=null where id =?";
				}else {
					sql="update repair_type set software=null where id =?";
				}
				PreparedStatement ps1 = c.prepareStatement(sql);
				ps1.setLong(1, id);
				int num = ps1.executeUpdate();
				if(num>0) {
					System.out.print("删除成功");
				} else {
					System.out.print("删除失败");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*String sql = "delete from repair_type where id = ?";
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
		String sql="";
		if(type==1) {
			sql="update repair_type set hardware=null where hardware =?";
		}else {
			sql="update repair_type set software=null where software =?";
		}
		try (Connection c = DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql) ){
			ps.setString(1, content);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}*/
	}

	/*
	 * @param type 维修类型，1表示硬件，2表示软件
	 * (non-Javadoc)
	 * @see dao.DAO#getRepairType(int, int)
	 */

	public void updateRepairType(int id, int type, String content) {
		// TODO Auto-generated method stub
		String sql = "";
		if(type==1) {
			//UPDATE Person SET Address = 'Zhongshan 23' WHERE LastName = 'Wilson'
			sql = "update repair_type set hardware=? where id=?";
		}else {
			sql = "update repair_type set software=? where id=?";
		}
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, content);
			ps.setLong(2, id);
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

	public String getRepairType(int id, int type) {
		// TODO Auto-generated method stub
		String sql = "select id,software,hardware from repair_type where id = ?,type= ? ";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.setLong(2, type);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				return rs.getString(1);
			} else {
				System.out.println("该id不存在！！");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
