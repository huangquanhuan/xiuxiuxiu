package xiuxiuxiu.pojo;
/**
 * -Manger 对应数据库的Manger表     继承User类
 * 
 * @author 向鹏
 * @date  2019-04-24
 *
 */
public class Manger extends User {

	/**
	 * 
	 * Manger构造函数，直接调用父类构造函数
	 * 需输入(int id, String password, String name, String phoneNumber, int accessLevel, String address,String Email)
	 */
	
	

	public Manger(String password, String name, String phoneNumber, int accessLevel, String address,
			String email) {
		super(password, name, phoneNumber, accessLevel, address, email);

		// TODO 自动生成的构造函数存根
	}

	public Manger() {
		// TODO 自动生成的构造函数存根
	}

}
