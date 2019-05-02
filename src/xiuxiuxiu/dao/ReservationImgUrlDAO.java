package xiuxiuxiu.dao;

import xiuxiuxiu.pojo.*;
import java.util.List;

/**
 * ReservationImgUrlDAO - 封装有关ReservationImgUrl表的操作
 * 对 ReservationImgUrl表的所有操作应通过实现该接口实现
 *
 * @author masgak
 * @date 2019-04-29
 */
public interface ReservationImgUrlDAO {
	/**
     * add() - 添加一个 ReservationImgUrl 对象到表中
     *
     * @param 图片url地址与相应预约单id
     */
	void addReservationImgUrl( String imgUrl,int reservation_id);
	/**
     * delete() - 以 ID 为依据删除该链接
     *
     * @param id 图片链接的 ID 号
     */
	void deleteReservationImgUrl(int id);
	/**
     * update() - 更新图片链接
     *
     * @param 图片链接的 ID 号
     */
	void updateReservationImgUrl(int id);
	/**
     * get() - 由传入的id获取图片链接
     *
     * @param 图片链接的 ID 号
     */
	String getReservationImgUrl(int id);
	/**
     * getOwner() - 由传入的id获取预约单id
     *
     * @param 图片链接的 ID 号
     */
	int getReservationImgUrlOwnerID(int id);
	
	/**
	 * List() - 根据预约单id查找该预约单包含的图片的url列表
	 * 
	 * @param reservationID 预约单id
	 * @return 该预约单包含的图片的url列表
	 */
	List<String> List(int reservationID);
	
	boolean isExist(int id);
}
