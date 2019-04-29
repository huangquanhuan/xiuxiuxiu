package xiuxiuxiu.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import xiuxiuxiu.pojo.*;
import xiuxiuxiu.util.DBUtil;

/*
 * 使用PreparedStatement可以解决SQL注入的问题
 */
public class SqlDAOImpl implements SqlDAO {

	@Override
	public void addStudent(Student bean) {


		String sql = "insert into user(password,user_name,phone_number,access_level,student_id,address,e_mail) values(? ,? ,? ,? ,? ,? ,? )";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, bean.getPassword());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getPhoneNumber());
			ps.setInt(4, bean.getAccessLevel());
			ps.setString(5, bean.getStudentID());
			ps.setString(6, bean.getAddress());
			ps.setString(7, bean.getEmail());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				bean.setID(id);
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void deleteStudent(int id) {
		String sql = "delete from user where user_id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/**
	 * 只可修改用户的姓名、密码、学号、住址、电子邮箱，账户(ID)、手机号、权限等级不可修改
	 */
	@Override
	public void updateStudent(Student bean) {
		String sql = "update user set user_name=?,password=?,student_id=?,address=?,e_mail=? where id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getPassword());
			ps.setString(3, bean.getStudentID());
			ps.setString(4, bean.getAddress());
			ps.setString(5, bean.getEmail());
			ps.setInt(6, bean.getID());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * 根据id获取整个用户信息
	 * (non-Javadoc)
	 * @see dao.DAO#getStudent(java.lang.String)
	 */
	@Override
	public Student getStudent(int id) {
		String sql = "select user_id,user_name,password,phone_number,access_level,student_id,address,e_mail from user where user_id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				Student bean = new Student();
				bean.setID(rs.getInt("user_id"));
				bean.setName(rs.getString("user_name"));
				bean.setPassword(rs.getString("password"));
				bean.setPhoneNumber(rs.getString("phone_number"));
				bean.setAccessLevel(rs.getInt("access_level"));
				bean.setStudentID(rs.getString("student_id"));
				bean.setAddress(rs.getString("address"));
				bean.setEmail(rs.getString("e_mail"));
				return bean;
			} else {
				System.out.println("该id不存在！！");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 可用于登录验证
	 * (non-Javadoc)
	 * @see dao.DAO#getStudent(java.lang.String, java.lang.String)
	 */
	public Student getStudent(int id, String password) {
		String sql = "select user_id,user_name,password,phone_number,access_level,student_id,address,e_mail from user where user_id = ? and password = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.setString(2, password);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				Student bean = new Student();
				bean.setID(rs.getInt("user_id"));
				bean.setName(rs.getString("user_name"));
				bean.setPassword(rs.getString("password"));
				bean.setPhoneNumber(rs.getString("phone_number"));
				bean.setAccessLevel(rs.getInt("access_level"));
				bean.setStudentID(rs.getString("student_id"));
				bean.setAddress(rs.getString("address"));
				bean.setEmail(rs.getString("e_mail"));
				return bean;
			} else {
				System.out.println("用户不存在或密码错误");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

/**
 * 根据条件
 * @return 输出一个按id升序的List<Student>
 */
	@Override
	public List<Student> searchStudent(String condition) {
		String sql = "select user_id,user_name,password,phone_number,access_level,student_id,address,e_mail from user ORDER BY user_id";
		List<Student> userList = new ArrayList<Student>();
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				Student bean = new Student();
				bean.setID(rs.getInt("user_id"));
				bean.setName(rs.getString("user_name"));
				bean.setPassword(rs.getString("password"));
				bean.setPhoneNumber(rs.getString("phone_number"));
				bean.setAccessLevel(rs.getInt("access_level"));
				bean.setStudentID(rs.getString("student_id"));
				bean.setAddress(rs.getString("address"));
				bean.setEmail(rs.getString("e_mail"));
				userList.add(bean);
			}
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int getTotalStudent() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

			String sql = "select count(*) from user";

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
	public boolean isStudentExist(int id) {
		String sql = "select * from User where id=?";
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

	/*
	 * isnull语句是判断该id是否存有属性值，如果存在单个属性那插入另一个属性用update语句
	 * 如果该id不存在则用insert插入一个新行
	 * @param type 维修类型，1表示硬件，2表示软件
	 * (non-Javadoc)
	 * @see dao.DAO#getRepairType(int, int)
	 */
	@Override
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
	@Override
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
	@Override
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

	@Override
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

	@Override
	public void addRepairActivity(int id, String time, String place) {
		// TODO Auto-generated method stub
		String sql = "insert into repair_activity(id,time,place) values(? ,? ,? )";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.setString(2, time);
			ps.setString(3, place);
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
				String manger_id=rs.getString(3);
				String mess="时间是"+time+"地点为"+place+"管理员id为"+manger_id+"";
				System.out.print("时间是"+time+"地点为"+place+"管理员id为"+manger_id+"");
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
	public Manger getManger(int id, String password) {
		// TODO Auto-generated method stub
		String sql = "select id,name,password,phone_number,access_level,email from manger where id = ? and password = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
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
	public void addEquipment(String equipmentName, String userID) {
		// TODO Auto-generated method stub
		String sql = "insert into equipment(equipment_name,user_id) values(?,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, equipmentName);
			ps.setString(2, userID);
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
	public void deleteEquipment(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from equipment where id =?";
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
	public void updateEquipment(int id) {
		// TODO Auto-generated method stub
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
	public String getEquipmentName(int id) {
		// TODO Auto-generated method stub
		String sql = "select equipment_name from equipment where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				String equipmentName=rs.getString(1);
				System.out.print("设备名是"+equipmentName+"");
				return equipmentName;
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
	public String getEquipmentOwner(int id) {
		// TODO Auto-generated method stub
		String sql = "select user_id from equipment where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				String userID=rs.getString(1);
				System.out.print("用户id是"+userID+"");
				return userID;
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
	public boolean isEquipmentExist(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from equipment where id=?";
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
	public void addArticle(Article article) {
		// TODO Auto-generated method stub
				String sql = "insert into article(id,author_id,author_name,title,text,time) values(? ,? ,? ,? ,? ,? )";
				try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
					//ps.setString(1, article.getID()); 
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

	@Override
	public void deleteArticle(int id) {
		// TODO Auto-generated method stub
				String sql = "delete from article where id = ?";
				try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
					ps.setLong(1, id);
					ps.execute();

				} catch (SQLException e) {

					e.printStackTrace();
				}
	}

	@Override
	public void updateArticle(Article article) {
		// TODO Auto-generated method stub
		String sql = "update article set author_id=?,author_name=?,title=?,text=?,time=? where id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			//ps.setString(1, article.getName());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Article getArtice(int id) {
		// TODO Auto-generated method stub
		String sql = "select id,author_id,author_name,title,text,time from article where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				Article article=new Article();
				//article.setID(rs.getString("id"));
				return article;
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
	public List<Article> searchArticle(String str) {
		// TODO Auto-generated method stub
		String sql = "select id,author_id,author_name,title,text,time from article from article ORDER BY id";
		List<Article> articleList = new ArrayList<Article>();
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				Article article = new Article();
				//reservation.setID(rs.getString("user_id"));
				articleList.add(article);
			}
			return articleList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void addComponent(Component component) {
		// TODO Auto-generated method stub
		String sql = "insert into component(id,name,price,quantity,type) values(? ,? ,? ,? ,? )";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			//ps.setString(1, article.getID()); 
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

	@Override
	public void deleteComponent(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from component where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateComponent(Component component) {
		// TODO Auto-generated method stub
		String sql = "update component set name=?,price=?,quantity=?,type=? where id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			//ps.setString(1, component.getName());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Component getComponent(int id) {
		// TODO Auto-generated method stub
		String sql = "select name,price,quantity,type from component where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				Component component=new Component();
				//article.setID(rs.getString("id"));
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

	@Override
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
				componentList.add(component);
			}
			return componentList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
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
	public int getTotalReservation() {
		// TODO Auto-generated method stub
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
			String sql = "select count(*) from Reservation";
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
	public int getTotalManger() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
			String sql = "select count(*) from Manger";
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
	public int getTotalArticle() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
			String sql = "select count(*) from Article";
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
	public int getTotalComponent() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
			String sql = "select count(*) from Component";
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
		// TODO Auto-generated method stub
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

	@Override
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
	public boolean isReservationImgUrlExist(int id) {
		// TODO Auto-generated method stub
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