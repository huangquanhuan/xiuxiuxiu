package xiuxiuxiu.pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import xiuxiuxiu.util.DBUtil;
/**
 * ReservationComponent - 对预约零件的一条记录
 *
 * @author 苏明辉
 * @date 2019-04-24
 */

public class ReservationComponent {

	private int componentId;
	
	private String componentName;
	
	private double price;
	
	private int reservationId;
	
	private String stuId;
	
	private String userName;
	
	private String phoneNumber;
	
	public int getComponentId() {
		return componentId;
	}
	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
     * ReservationComponent构造函数
     */
	public ReservationComponent(int componentId,String componentName,double price,
			int reservationId,String stuID,String userName,String phoneNumber) 
	{
		this.componentId=componentId;
		this.componentName=componentName;
		this.price=price;
		this.reservationId=reservationId;
		this.stuId=stuID;
		this.userName=userName;
		this.phoneNumber=phoneNumber;
	}
	/**
	 * ReservationComponent默认构造函数
	 */
	public ReservationComponent() {
		// TODO Auto-generated constructor stub
	}
	
	/**
     * ReservationComponent getReservationComponert(int componentId)
     * 通过零件号返回该条预约记录
     *
     * @param componentId 零件id
     * @return 如果找到，则返回该Reservation 实例，否则返回 null
     */	 
	public ReservationComponent getReservationComponert(int componentId)
	{
		ReservationComponent rc =new ReservationComponent();
		return rc;
	}
	
	
}
