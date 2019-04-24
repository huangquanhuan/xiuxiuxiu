package xiuxiuxiu.pojo;

/**
 * ReservationUser - 对预约人员的一条记录
 *
 * @author 苏明辉
 * @date 2019-04-24
 */

public class ReservationUser 
{

	private String stuId;
	
	private String userName;
	
	private String phoneNumber;

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
     * ReservationUser构造函数
     */
	public ReservationUser (String stuID,String userName,String phoneNumber) 
	{
		
		this.stuId=stuID;
		this.userName=userName;
		this.phoneNumber=phoneNumber;
	}
	/**
	 * ReservationUser默认构造函数
	 */
	public ReservationUser() {
		// TODO Auto-generated constructor stub
	}

}
