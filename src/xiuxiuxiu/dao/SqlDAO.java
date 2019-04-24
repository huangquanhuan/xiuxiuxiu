package xiuxiuxiu.dao;

import java.util.List;

import xiuxiuxiu.pojo.*;

public interface SqlDAO{
	
	
	//用户表操作
	void addStudent(Student user);
	void deleteStudent(int id);
	void updateStudent(Student user);
	Student getStudent(int id);
	Student getStudent(int id,String password);//可用来判断密码是否正确
	List<Student> searchStudent(String condition);//返回userID的List
	public int getTotalStudent();
	boolean isStudentExist(int id);
	
	
	//预约单操作
	void addReservation(Reservation reservation);
	void deleteReservation(int id);
	public int getTotalReservation();
	void updateReservation(Reservation reservation);
	Reservation getReservation(int id);
	List<Reservation> searchReservation(String condition);//返回id的List
	boolean isReservationExist(int id);
	
	
	//维修问题类型操作
	void addRepairType(int id,int type,String content);
	void deleteRepairType(int id , int type);//id相当于横坐标，type相当于纵坐标（1：hardware；2：software）
	void updateRepairType(int id , int type , String content);
	String getRepairType(int id , int type);
	
	
	//维修场次表操作
	void addRepairActivity(int id, String time,String place);
	void deleteRepairActivity(int id);
	void updateRepairActivity(int id, String time,String place);
	String getRepairActivity(int id);
	boolean isRepairActivityExist(int id);
	
	
	//管理员表操作
	void addManger(Manger manger);
	void deleteManger(int id);
	public int getTotalManger();
	void updateManger(Manger manger);
	Manger getManger(int id);
	Manger getManger(int id,String password);//可用来判断密码是否正确
	boolean isMangerExist(int id);
	
	
	//设备、用户对应表操作
	void addEquipment( String equipmentName,String userID);
	void deleteEquipment(int id);
	void updateEquipment(int id);
	String getEquipmentName(int id);
	String getEquipmentOwner(int id);
	boolean isEquipmentExist(int id);
	

	//文章表操作
	void addArticle(Article article);
	void deleteArticle(int id);
	void updateArticle(Article article);
	public int getTotalArticle();
	Article getArtice(int id);
	List<Article> searchArticle(String str);//返回id的List
	

	//零件库表操作
	void addComponent(Component component);
	void deleteComponent(int id);
	public int getTotalComponent();
	void updateComponent(Component component);
	Component getComponent(int id);
	List<Component> searchComponent(String str);//返回id的List
	boolean isComponentExist(int id);
	
	
	
	//预约单、图片url对应表操作
	void addReservationImgUrl( String imgUrl,int reservation_id);
	void deleteReservationImgUrl(int id);
	void updateReservationImgUrl(int id);
	String getReservationImgUrl(int id);
	int getReservationImgUrlOwnerID(int id);
	boolean isReservationImgUrlExist(int id);
}
