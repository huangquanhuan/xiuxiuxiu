package xiuxiuxiu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import xiuxiuxiu.pojo.Student;
import xiuxiuxiu.util.DBUtil;

public class StudentDAOImpl implements StudentDAO {
    @Override
	public void add(Student bean) {
		String sql = "insert into student(password,name,phone_number,access_level,student_id,address,e_mail) values(? ,? ,? ,? ,? ,? ,? )";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, bean.getPassword());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getPhoneNumber());
	 		//设置用户等级，目前默认为0
			ps.setInt(4, 0);
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
	public void delete(int id) {
		String sql = "delete from student where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
    @Override
	public void delete(Student student) {
		StudentDAO studentDao = new StudentDAOImpl();
		if(!studentDao.isExist(student))	//如果没有匹配的用户
		{
			System.out.println("未找到相匹配用户，删除失败！");
			return;	
		}
		String sql = "delete from student where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, student.getID());
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 把对 Student 对象的修改根据id写回数据库中
	 *  只可修改用户的姓名、密码、学号、住址、电子邮箱，账户(ID)、手机号、权限等级不可修改
	 */
    @Override
	public void update(Student bean) {
		String sql = "update student set name=?,password=?,student_id=?,address=?,e_mail=? where id=?";
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

	/**
	 * 根据id获取整个用户信息 
	 */
    @Override
	public Student get(int id) {
		String sql = "select id,name,password,phone_number,access_level,student_id,address,e_mail from student where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				EquipmentDAO equipmentDao = new EquipmentDAOImpl();
				Student bean = new Student();
				bean.setID(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setPassword(rs.getString("password"));
				bean.setPhoneNumber(rs.getString("phone_number"));
				bean.setAccessLevel(rs.getInt("access_level"));
				bean.setStudentID(rs.getString("student_id"));
				bean.setAddress(rs.getString("address"));
				bean.setEmail(rs.getString("e_mail"));
				bean.setEquipment(equipmentDao.listEquipmentsByUser(id));// 根据学生id获取设备列表并将列表set进该学生的信息中
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

	/**
	 * get() - 通过手机号和密码获取 Student 对象（可用于登录验证）
     *
     * @param phoneNumber 用户的手机号
     * @param password 用户的密码
     * @return 如果找到，返回 Student 实例，否则返回 null
	 */
    @Override
	public Student get(String phoneNumber, String password) {
		String sql = "select id,name,password,phone_number,access_level,student_id,address,e_mail from student where phone_number = ? and password = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, phoneNumber);
			ps.setString(2, password);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				EquipmentDAO equipmentDao = new EquipmentDAOImpl();
				Student bean = new Student();
				bean.setID(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setPassword(rs.getString("password"));
				bean.setPhoneNumber(rs.getString("phone_number"));
				bean.setAccessLevel(rs.getInt("access_level"));
				bean.setStudentID(rs.getString("student_id"));
				bean.setAddress(rs.getString("address"));
				bean.setEmail(rs.getString("e_mail"));
				bean.setEquipment(equipmentDao.listEquipmentsByUser(bean.getID()));// 根据学生id获取设备列表并将列表set进该学生的信息中
				return bean;
			} else {
				System.out.println("用户不存在");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

    @Override
	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

			String sql = "select count(*) from student";

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
	public boolean isExist(String phoneNumber) {
		String sql = "select * from student where phone_number=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, phoneNumber);
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
	public boolean isExist(Student student) {
		String sql = "select * from student where id=? and password=? and name=? and phone_number=? and student_id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, student.getID());
			ps.setString(2, student.getPassword());
			ps.setString(3, student.getName());
			ps.setString(4, student.getPhoneNumber());
			ps.setString(5, student.getStudentID());
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
	public List<Student> listAll() {
		String sql = "select id,name,password,phone_number,access_level,student_id,address,e_mail from student ORDER BY id";
		List<Student> studentList = new ArrayList<Student>();
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				EquipmentDAO equipmentDao = new EquipmentDAOImpl();
				Student bean = new Student();
				bean.setID(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setPassword(rs.getString("password"));
				bean.setPhoneNumber(rs.getString("phone_number"));
				bean.setAccessLevel(rs.getInt("access_level"));
				bean.setStudentID(rs.getString("student_id"));
				bean.setAddress(rs.getString("address"));
				bean.setEmail(rs.getString("e_mail"));
				bean.setEquipment(equipmentDao.listEquipmentsByUser(bean.getID()));// 根据学生id获取设备列表并将列表set进该学生的信息中
				studentList.add(bean);
			}
			return studentList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Student> listByPage(int pageNo, int pageSize) {
//		String sql = "select id,name,password,phone_number,access_level,student_id,address,e_mail from student ORDER BY id";
//		List<Student> studentList = new ArrayList<Student>();
//		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
//			ps.execute();
//			ResultSet rs = ps.getResultSet();
//			while (rs.next()) {
//				EquipmentDAO equipmentDao = new EquipmentDAOImpl();
//				Student bean = new Student();
//				bean.setID(rs.getInt("id"));
//				bean.setName(rs.getString("name"));
//				bean.setPassword(rs.getString("password"));
//				bean.setPhoneNumber(rs.getString("phone_number"));
//				bean.setAccessLevel(rs.getInt("access_level"));
//				bean.setStudentID(rs.getString("student_id"));
//				bean.setAddress(rs.getString("address"));
//				bean.setEmail(rs.getString("e_mail"));
//				bean.setEquipment(equipmentDao.List(bean.getID()));// 根据学生id获取设备列表并将列表set进该学生的信息中
//				studentList.add(bean);
//			}
//			return studentList;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
		
		//这个方法目前不知道要怎么用，因此目前没实现
		return null;
	}

}
