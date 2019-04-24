package xiuxiuxiu.pojo;

import java.util.List;
/**
 *ReservationUserList - 对预约人员的记录列表，提供一些操作
 *
 * @author 苏明辉
 * @date 2019-04-24
 */ 
public class ReservationUserList
{
	public List<ReservationUser> ruList;
	/**
     * void Add(ReservationUser ru)
     * 向列表中添加一条记录
     *
     * @param ReservationUser 单条预约人员记录
     * @return 空
     */	 
	public void Add(ReservationUser ru)
	{
		ruList.add(ru);
	}
	/**
     * void Delete(ReservationUser ru)
     * 删除列表中的一条记录
     *
     * @param ReservationUser 单条预约零件记录
     * @return 空
     */	 
	public void Delete(ReservationUser ru)
	{
		
	}
	/**
     * void Update(ReservationUser ru)
     * 更新列表中的一条记录
     * @param ReservationUser 单条预约零件记录
     * @return 空
     */	 
	public void Update(ReservationUser ru)
	{

	}
	
	/**
     * ReservationUserList getReservationUserList(int reservationId)
     * 通过预约单号返回预约人员列表
     * @param reservationId 预约单号
     * @return ReservationUserList 预约人员列表
     */	 
	public ReservationUserList getReservationUserList(int reservationId)
	{
		ReservationUserList ruList=new ReservationUserList();
		return ruList;
	}
	
}
