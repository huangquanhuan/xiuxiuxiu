package xiuxiuxiu.dao;

import xiuxiuxiu.pojo.*;
import java.util.List;

/**
 * ReservationDAO - 封装有关Reservation表的操作
 * 对 Reservation表的所有操作应通过实现该接口实现
 *
 * @author masgak
 * @date 2019-04-29
 */
public interface ReservationDAO {
	/**
     * add() - 将 Reservation 对象的信息插入到reservation表、apply_component表以及reservation_img_url表中
     *
     * @param reservation 实例化后的 Reservation 对象
     */
	void addReservation(Reservation reservation);
	
	/**
     * delete() - 从预约表中删除某一对象，以 ID 为依据
     *
     * @param id 预约表的 ID 号
     */
	void deleteReservation(int id);
	
	/**
     * update() - 把对 Reservation 对象的修改写回数据库中
     *
     * @param reservation 要保存的 Reservation 对象
     */
	void updateReservation(Reservation reservation);
	/**
     * getReservation() - 根据id从数据库获取对应Reservation(预约单)对象
     *
     * @return 相应id的Reservation(预约单)对象
     */
	Reservation getReservation(int id);
	/**
	 * List() - 根据学生id查找对应的预约单
	 * 
     *@return 该学生的预约单(对象)列表
     */
	List<Reservation> List(int userID);
	/**
     * isExist() - 判断预约表是否已经存在
     *
     * @param id 预约表的相应id
     * @return 如果预约表存在，则返回 true，否则返回 false
     */
	boolean isExist(int id);
	/**
     * getTotal() - 返回 Reservation 表的总记录数
     *
     * @return 一个整数，表示 reservation 表的记录数，也就是预约表总数
     */
	public int getTotal();
}
