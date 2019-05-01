package xiuxiuxiu.pojo;

import java.util.ArrayList;
import java.util.List;

/**

 * -Student 对应数据库的user表 继承User类
 * 
 * @author 向鹏
 *
 */
public class Student extends User {

	/**
	 * 学生的学号
	 */
	private String studentID;
	/**
	 * 学生拥有的设备列表,每个元素为设备表中的设备号
	 */
	private List<Integer> equipment = new ArrayList<Integer>();

	/**
	 * Student类的构造函数,调用父类User的带参构造函数，再补充学号和拥有设备的设备号列表 需输入(int id , String
	 * password , String name , String phoneNumber , int accessLevel ,String
	 * address , String email , String studentID, List<Integer> equipment)
	 */
	public Student(String password, String name, String phoneNumber, int accessLevel, String address,
			String email, String studentID, List<Integer> equipment) {
		super( password, name, phoneNumber, accessLevel, address, email);
		this.studentID = studentID;
		if (equipment != null) {
			for (int i = 0; i < equipment.size(); i++) {
				this.equipment.add(equipment.get(i));
			}
		}
	}

	public Student() {
		// TODO 自动生成的构造函数存根
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public List<Integer> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<Integer> equipment) {
		this.equipment.clear();
		for (int i = 0; i < equipment.size(); i++) {
			this.equipment.add(equipment.get(i));
		}
	}
}
