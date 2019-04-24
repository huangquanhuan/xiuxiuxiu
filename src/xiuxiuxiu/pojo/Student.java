package xiuxiuxiu.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * -Student 对应数据库的user表  继承User类
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
	 * 
	 * 
	 */
	public Student(int id, String password, String name, String phoneNumber, int accessLevel, String address, String Email, String studentID, List<Integer> equipment) {
		super(id, password, name, phoneNumber, accessLevel, address, Email);
    	this.studentID = studentID;
    	for(int i = 0 ; i<equipment.size(); i++ )
    		this.equipment.add(equipment.get(i));
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
		for(int i = 0 ; i<equipment.size(); i++ )
    		this.equipment.add(equipment.get(i));
	}
}
