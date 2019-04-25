package xiuxiuxiu.pojo;

/**
 * User - 人员类
 *
 * @author 刘忠燏
 * @date 2019-04-20
 */
public class User {

	/**
	 * 用户的id
	 */
	protected Integer id;
	/**
	 * 用户密码
	 */
    protected String password;
    /**
	 * 用户的id
	 */
    protected String name;
    /**
	 * 用户的联系方式（电话号码）
	 */
    protected String phoneNumber;
    /**
	 * 用户的权限等级
	 */
    protected Integer accessLevel;  
    /**
	 * 用户的住址
	 */
    protected String address;
    /**
	 * 用户的邮箱
	 */
    protected String Email;
    
    /**
     * User构造函数，需输入( String password , String name , String phoneNumber , int accessLevel ,String address , String Email)
     */
    public User(String password , String name , String phoneNumber , int accessLevel ,String address , String Email){
    	this.password = password;
    	this.name = name;
    	this.phoneNumber = phoneNumber;
    	this.accessLevel = accessLevel;
    	this.address = address;
    	this.Email = Email;
    }
    public User(){
    	
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
}
