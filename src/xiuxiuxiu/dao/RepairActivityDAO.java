package xiuxiuxiu.dao;

import xiuxiuxiu.pojo.*;

import java.util.ArrayList;
import java.util.List;

/**
 * RepairActivityDAO - 封装有关RepairActivity表的操作
 * 对 RepairActivity表的所有操作应通过实现该接口实现
 *
 * @author masgak
 * @date 2019-04-29
 */
public interface RepairActivityDAO {
	/**
     * add() - 添加一个维修活动对象到对应id中中
     *
     * @param 活动时间，活动地点
     */
	void addRepairActivity(String time,String place);
	/**
     * delete() - 从活动表中删除某一对象，以 ID 为依据
     *
     * @param id 活动对应的 ID号
     */
	void deleteRepairActivity(int id);
	/**
     * update() - 把对 维修活动对象的修改写回数据库中
     *
     * @param 活动对应的id，活动时间，活动地点
     */
	void updateRepairActivity(int id, String time,String place);
	/**
     * getRepairActivity() - 根据id在数据库查找并返回对应的repairActivity对象
     *
     * @return 对应的repairActivity对象
     */
	RepairActivity getRepairActivity(int id);
	/**
     *  isRepairActivityExist() - 判断该id的维修场次是否存在
     * @return True or False
     */
	boolean isRepairActivityExist(int id);
	/**
	 * ListAll() - 返回所有的线下维修活动场次列表
	 */
	List<RepairActivity> ListAll();
	
}
