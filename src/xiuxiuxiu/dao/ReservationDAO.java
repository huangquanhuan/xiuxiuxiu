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
     * add() - 添加一个 Reservation 对象到表中
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
     * getTotal() - 返回 Reservation 表的总记录数
     *
     * @return 一个整数，表示 reservation 表的记录数，也就是预约表总数
     * TODO: 斟酌该方法的返回值应该设为 int 还是 long
     */
	public int getTotalReservation();
	/**
     * update() - 把对 Reservation 对象的修改写回数据库中
     *
     * @param reservation 要保存的 Reservation 对象
     */
	void updateReservation(Reservation reservation);
	/**
     * getReservation() - 返回 Reservation 表详细信息
     *
     * @return 相应id的预约表详细信息
     */
	Reservation getReservation(int id);
	/**
     *@return 一个包含预约表对象的List
     * 
     */
	List<Reservation> searchReservation(String condition);//返回id的List
	/**
     * isExist() - 判断预约表是否已经存在
     *
     * @param id 预约表的相应id
     * @return 如果预约表存在，则返回 true，否则返回 false
     */
	boolean isReservationExist(int id);
}
