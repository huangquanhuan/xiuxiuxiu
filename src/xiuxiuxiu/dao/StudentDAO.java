package xiuxiuxiu.dao;

import xiuxiuxiu.pojo.*;

import java.util.List;

/**
 * StudentDAO - 封装有关 Student (学生用户)表的操作 对 Student 表的所有操作应通过实现该接口实现
 *
 * @author Lenovo
 * @date 2019-04-20
 */
public interface StudentDAO {
	/**
	 * add() - 添加一个 student 对象到表中
	 *
	 * @param student 实例化后的 Student 对象
	 */
	void add(Student student);

	/**
	 * delete() - 从用户表中删除某一对象，以 ID 为依据
	 *
	 * @param id 用户的 ID 号（不是手机号）
	 */
	void delete(int id);

	/**
	 * delete() - 从 student 表中删除给定的 Student 对象
	 *
	 * @param student 要删除的 Student 对象
	 */
	void delete(Student student);

	/**
	 * update() - 把对 Student 对象的修改根据id写回数据库中
	 *
	 * @param student 要保存的 Student 对象
	 */
	void update(Student student);

	/**
	 * get() - 通过 ID 获取 Student 对象
	 *
	 * @param id 要查找的 ID 号
	 * @return 如果找到，则返回该 Student 实例，否则返回 null
	 */
	Student get(int id);

	/**
	 * get() - 通过手机号和密码获取 Student 对象（可用于登录验证）
	 *
	 * @param phoneNumber 用户的手机号
	 * @param password    用户的密码
	 * @return 如果找到，返回 Student 实例，否则返回 null
	 */
	Student get(String phoneNumber, String password);

	/**
	 * listAll() - 从数据库中获取所有记录，返回一个列表
	 *
	 * @return 一个包含 Student 对象的 List
	 */
	List<Student> listAll();

	/**
	 * listByPage() - （分页查询用）从数据库中获取部分记录
	 *
	 * @param pageNo   起始页码
	 * @param pageSize 每个页面的记录数目
	 * @return 一个包含 Student 对象的 List，且该 List 的长度小于或等于 pageSize
	 */
	List<Student> listByPage(int pageNo, int pageSize);

	/**
	 * getTotal() - 返回 student 表的总记录数
	 *
	 * @return 一个整数，表示 student 表的记录数，也就是用户总数 TODO: 斟酌该方法的返回值应该设为 int 还是 long
	 */
	int getTotal();

	/**
	 * isExist() - 用手机号判断用户是否已经存在（注册验证时用）
	 *
	 * @param phoneNumber 用于注册的手机号
	 * @return 如果手机号对应的用户表存在，则返回 true，否则返回 false
	 */
	boolean isExist(String phoneNumber);

	/**
    * isExist() - 根据用户的id、password、name、phone_number、student_id联合判断用户是否已经存在
    *
    * @param student 要匹配的用户
    * @return 如果对应的用户存在，则返回 true，否则返回 false
    */
    boolean isExist(Student student);
}
