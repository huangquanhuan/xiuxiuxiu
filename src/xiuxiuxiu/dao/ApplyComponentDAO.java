package xiuxiuxiu.dao;

import xiuxiuxiu.pojo.*;
import java.util.List;

/**
 * ApplyComponentDAO - 封装有关apply_component表的操作 对apply_component表的所有操作应通过实现该接口实现
 *
 * @author xp
 * @date 2019-04-29
 */
public interface ApplyComponentDAO {
	/**
	 * add() - 在apply_component表中添加一个预约单id-零件id对应记录
	 * 
	 * @param reservationID 预约单id
	 * @param componentID   零件id
	 */
	void add(int reservationID, int componentID);

	/**
	 * delete() - 从apply_component(预约单-零件对应表)中删除相应预约单的所有记录
	 *
	 * @param reservationID 预约单的id
	 */
	void delete(int reservationID);

	/**
	 * getReservationID() - 获取相应id对应的预约单id
	 *
	 * @param id apply_component（预约单-零件对应表）表中的 ID 号
	 * 
	 * @return 对应的预约单id，若为-1表示查找失败
	 */
	int getReservationID(int id);

	/**
	 * getComponentID() - 获取相应id对应的零件id
	 *
	 * @param id apply_component（预约单-零件对应表）表中的 ID 号
	 *
	 ** @return 对应的零件id，若为-1表示查找失败
	 */
	int getComponentID(int id);

	/**
	 * isExist() - 判断设备是否已经存在
	 *
	 * @param id apply_component（预约单-零件对应表）表中的 ID 号
	 * @return 如果id对应的设备表存在，则返回 true，否则返回 false
	 */
	boolean isExist(int id);

	/**
	 * 根据预约单id获取该预约单包含的需求零件id列表
	 * 
	 * @param reservationID 预约单的id
	 * @return 该预约单包含的需求零件id列表
	 */
	List<Integer> List(int reservationID);

}
