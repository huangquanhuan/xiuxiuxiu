package xiuxiuxiu.dao;

import xiuxiuxiu.pojo.*;
import java.util.List;

/**
 * UserDAO - 封装有关 User 表的操作
 * 对 User 表的所有操作应通过实现该接口实现
 *
 * @author Lenovo
 * @date 2019-04-20
 */
public interface UserDao {
    /**
     * add() - 添加一个 user 对象到表中
     *
     * @param entity 实例化后的 User 对象
     */
    void add(Student entity);

    /**
     * delete() - 从用户表中删除某一对象，以 ID 为依据
     *
     * @param id 用户的 ID 号（不是手机号）
     */
    void delete(Integer id);

    /**
     * delete() - 从 user 表中删除给定的 User 对象
     *
     * @param entity 要删除的 User 对象
     */
    void delete(Student entity);

    /**
     * update() - 把对 User 对象的修改写回数据库中
     *
     * @param entity 要保存的 User 对象
     */
    void update(Student entity);

    /**
     * get() - 通过 ID 获取 User 对象
     *
     * @param id 要查找的 ID 号
     * @return 如果找到，则返回该 User 实例，否则返回 null
     */
    Student get(int id);

    /**
     * get() - 通过手机号和密码获取 User 对象（用于登录验证）
     *
     * @param phoneNumber 用户的手机号
     * @param password 用户的密码
     * @return 如果找到，返回 User 实例，否则返回 null
     */
    Student get(String phoneNumber, String password);

    /**
     * listAll() - 从数据库中获取所有记录，返回一个列表
     *
     * @return 一个包含 User 对象的 List
     */
    List<Student> listAll();

    /**
     * listByPage() - （分页查询用）从数据库中获取部分记录
     *
     * @param pageNo 起始页码
     * @param pageSize 每个页面的记录数目
     * @return 一个包含 User 对象的 List，且该 List 的长度小于或等于 pageSize
     */
    List<Student> listByPage(int pageNo, int pageSize);

    /**
     * getTotal() - 返回 user 表的总记录数
     *
     * @return 一个整数，表示 user 表的记录数，也就是用户总数
     * TODO: 斟酌该方法的返回值应该设为 int 还是 long
     */
    int getTotal();

    /**
     * isExist() - 判断用户是否已经存在（注册验证时用）
     *
     * @param phoneNumber 用于注册的手机号
     * @return 如果手机号对应的用户表存在，则返回 true，否则返回 false
     */
    boolean isExist(String phoneNumber);
}

