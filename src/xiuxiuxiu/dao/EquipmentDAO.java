package xiuxiuxiu.dao;

import xiuxiuxiu.pojo.*;
import java.util.List;

/**
 * EquipmentDAO - 封装有关Equipment表的操作
 * 对Equipment表的所有操作应通过实现该接口实现
 *
 * @author masgak
 * @date 2019-04-29
 */
public interface EquipmentDAO {
	/**
     * add() - 添加一个 设备对象到对应用户id中中
     *
     * @param 设备名称与用户id
     */
	void addEquipment( String equipmentName,String userID);
	/**
     * delete() - 从设备表中删除某一对象，以 ID 为依据
     *
     * @param id 设备的 ID 号
     */
	void deleteEquipment(int id);
	/**
     * update() - 把对 Equipment对象的修改写回数据库中
     *
     * @param 设备名称与对应表中的 ID 号
     */
	void updateEquipment(String equipmentName,int id);
	/**
     * getName() - 获取相应id对应的设备名
     *
     * @param 设备名称与对应表中的 ID 号
     */
	String getEquipmentName(int id);
	/**
     * getOwner() - 获取相应id对应的主人姓名
     *
     * @param 设备名称与对应表中的 ID 号
     */
	String getEquipmentOwner(int id);
	/**
     * isExist() - 判断设备是否已经存在
     *
     * @param id 该设备id
     * @return 如果id对应的设备表存在，则返回 true，否则返回 false
     */
	boolean isEquipmentExist(int id);
	/**
	 * 根据用户ID获取该用户的设备（id）列表
	 * @param userID 用户ID
	 * @return
	 */
	List<Integer> List(int userID);

}
