package xiuxiuxiu.dao;

import xiuxiuxiu.pojo.*;
import java.util.List;
/**
 * ManagerDAO - 封装有关Manager表的操作
 * 对Manager表的所有操作应通过实现该接口实现
 *
 * @author masgak
 * @date 2019-04-29
 */
public interface ManagerDAO {
	/**
     * add() - 添加一个 manager 对象到表中
     *
     * @param manager 实例化后的 Manager 对象
     */
	void addManger(Manger manger);
	/**
     * delete() - 从管理员表中删除某一对象，以 ID 为依据
     *
     * @param id 管理员的 ID 号（不是手机号）
     */
	void deleteManger(int id);
	/**
     * getTotal() - 返回 manger 表的总记录数
     *
     * @return 一个整数，表示 manger 表的记录数，也就是用户总数
     */
	public int getTotalManger();
	/**
     * update() - 把对 Manger 对象的修改写回数据库中
     *
     * @param manger 要保存的 Manger 对象
     */
	void updateManger(Manger manger);
	/**
     * get() - 通过 ID 获取 Manger 对象
     *
     * @param id 要查找的 ID 号
     * @return 如果找到，则返回该 Manger 实例，否则返回 null
     */
	Manger getManger(int id);
	/**
     * get() - 通过手机号和密码获取 Manger 对象（用于登录验证）
     *
     * @param phoneNumber 用户的手机号
     * @param password 用户的密码
     * @return 如果找到，返回 User 实例，否则返回 null
     */
	Manger getManger(String phoneNumber,String password);//可用来判断密码是否正确
	/**
     * isExist() - 判断管理员是否已经存在（注册验证时用）
     *
     * @param phoneNumber 用于注册的手机号
     * @return 如果手机号对应的管理员表存在，则返回 true，否则返回 false
     */
	boolean isMangerExist(int id);
	public List<Manger> list(int start,int count);
	/**
	 * listAll() - 从数据库中获取所有记录，返回一个按id升序的列表
	 *
	 * @return 一个包含 Student 对象的 List
	 */
	List<Manger> listAll();

	/**
	 * listByPage() - （分页查询用）从数据库中获取部分记录
	 *
	 * @param pageNo   起始页码
	 * @param pageSize 每个页面的记录数目
	 * @return 一个包含 Student 对象的 List，且该 List 的长度小于或等于 pageSize
	 */
	List<Manger> listByPage(int pageNo, int pageSize);

	/**
	 * getTotal() - 返回 student 表的总记录数
	 *
	 * @return 一个整数，表示 student 表的记录数，也就是用户总数 TODO: 斟酌该方法的返回值应该设为 int 还是 long
	 */
}
