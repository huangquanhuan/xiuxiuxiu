package xiuxiuxiu.dao;

/**
 * RepairTypeDAO - 封装有关repair_type 表的操作
 * 对repair_type 表的所有操作应通过实现该接口实现
 *
 */
public interface RepairTypeDAO {
	/**
     * add() - 添加一个 repairtype对象到表中
     *
     * @param 维修类型id,类型，具体内容
     */
	void addRepairType(int id,int type,String content);
	/**
     * delete() - 从预约类型表中删除某一对象，以 ID与类型 为依据
     *
     * @param 对象所属id与类型 （由于一个id对应两种类型，因此需要区分是硬件还是软件
     */
	void deleteRepairType(int id , int type);//id相当于横坐标，type相当于纵坐标（1：hardware；2：software）
	/**
     * update() - 更新一个 repairtype对象到表中
     *
     * @param 维修类型id,类型，具体内容
     */
	void updateRepairType(int id , int type , String content);
	/**
     * getRepairType() - 返回 RepairType表详细信息
     *
     * @return 该id与类型下对应的维修内容
     */
	String getRepairType(int id , int type);
}
