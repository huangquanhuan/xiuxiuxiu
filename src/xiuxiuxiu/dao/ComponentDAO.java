package xiuxiuxiu.dao;

import xiuxiuxiu.pojo.*;
import java.util.List;

/**
 * ComponentDAO - 封装有关Component表的操作
 * 对Component表的所有操作应通过实现该接口实现
 *
 * @author masgak
 * @date 2019-04-29
 */
public interface ComponentDAO {
	/**
     * add() - 添加一个 component 对象到表中
     *
     * @param component 实例化后的 Component 对象
     */
	void addComponent(Component component);
	/**
     * delete() - 从零件表中删除某一对象，以 ID 为依据
     *
     * @param id 零件的 ID 号
     */
	void deleteComponent(int id);
	/**
     * getTotal() - 返回 Component 表的总记录数
     *
     * @return 一个整数，表示 component 表的记录数，也就是用户总数
     * 
     */
	public int getTotalComponent();
	/**
     * update() - 把对 Component 对象的修改写回数据库中
     *
     * @param component 要保存的 Component 对象
     */
	void updateComponent(Component component);
	/**
     * getComponent() - 返回 Component 表详细信息
     *
     * @return 相应id的零件详细信息
     */
	Component getComponent(int id);
	/**
     *@return 一个包含零件对象的List
     * 
     */
	List<Component> searchComponent(String str);//返回id的List
	/**
     * isExist() - 判断零件是否已经存在
     *
     * @param id 该零件id
     * @return 如果id对应的零件表存在，则返回 true，否则返回 false
     */
	boolean isComponentExist(int id);
	
}
