package xiuxiuxiu.pojo;

import java.util.List;
/**
 *ReservationComponentList - 对预约零件的记录列表，提供一些操作
 *
 * @author 苏明辉
 * @date 2019-04-24
 */ 
public class ReservationComponentList 
{
	public List<ReservationComponent> rcList;
	/**
     * void Add(ReservationComponent rc)
     * 向列表中添加一条记录
     *
     * @param ReservationComponent 单条预约零件记录
     * @return 空
     */	 
	public void add(ReservationComponent rc)
	{
		rcList.add(rc);
	}
	/**
     * void Delete(ReservationComponent rc)
     * 删除列表中的一条记录
     *
     * @param ReservationComponent 单条预约零件记录
     * @return 空
     */	 
	public void delete(ReservationComponent rc)
	{
		
	}
	/**
     * void Update(ReservationComponent rc)
     * 更新列表中的一条记录
     * @param ReservationComponent 单条预约零件记录
     * @return 空
     */	 
	public void update(ReservationComponent rc)
	{

	}
	/**
     * ReservationComponentList getReservationComponentList(int reservationId)
     * 通过预约单号返回预约零件列表
     * @param reservationId 预约单号
     * @return ReservationComponentList 预约零件列表
     */	 
	public ReservationComponentList getReservationComponentList(int reservationId)
	{
		ReservationComponentList reList=new ReservationComponentList();
		return reList;
	}
	/**
     * ReservationComponentList getReservationComponentList(String stuId)
     * 通过学号返回预约零件列表
     * @param stuId 学号
     * @return ReservationComponentList 预约零件列表
     */	 
	public ReservationComponentList getReservationComponentList(String stuId)
	{
		ReservationComponentList reList=new ReservationComponentList();
		return reList;
	}
}
