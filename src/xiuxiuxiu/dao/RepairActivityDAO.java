package xiuxiuxiu.dao;

import xiuxiuxiu.pojo.*;
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
     * getRepairActivity() - 返回 RepairActivity 表详细信息
     *
     * @return 相应id的活动表详细信息
     */
	String getRepairActivity(int id);
	/**
     *
     * @return True or False
     */
	boolean isRepairActivityExist(int id);
	
    /**
     * listRecentActivity
     * 返回最近的几个活动场次
     * @param numberOfActivities 期待返回的活动数量
     * @return 返回一个活动列表，列表长度小于或等于 numberOfActivities
     * @author 刘忠燏
     */
    List<RepairActivity> listRecentActivities(int numberOfActivities);
}
