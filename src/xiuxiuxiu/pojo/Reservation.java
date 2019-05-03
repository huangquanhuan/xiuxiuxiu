package xiuxiuxiu.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * -Reservation 预约单类，用户提交的预约单的信息的封装
 * 
 * @author 向鹏
 *
 */
public class Reservation {

	/**
	 * 在数据库中对应预约单的id
	 */
	private int id;
	/**
	 * 预约单的3种状态：0表示未受理状态，1表示已受理未完成状态，2表示已完成状态
	 */
	private int state;
	/**
	 * 提交该预约单的用户对应的ID
	 */
	private int userID;
	/**
	 * 2种预约类型：0表示活动预约，1表示上门维修预约
	 */
	private int applicationType;
	/**
	 * 提交预约申请的时间
	 */
	private String applicationTime;
	/**
	 * 预约的维修时间
	 */
	private String requiredTime;
	/**
	 * 维修地点，若预约类型type为0-活动预约，place自动设置为活动地点；若预约类型type为1-上门维修预约，place自动设置为user_address
	 */
	private String place;
	/**
	 * 对应预约场次id，若选择的是上门维修（application_type为1），此项为空；若选择的是活动预约（application_type为0）repair_actity_id设置为当前活动的场次号
	 */
	private int repairActivityID;
	/**
	 * 选择维修的设备的设备号
	 */
	private int equipmentID;
	/**
	 * 维修类型，根据用户选择的维修的问题类型填入字符串
	 */
	private String repairType;
	/**
	 * 详细问题的描述
	 */
	private String detail;
	/**
	 * 维修结束后的评价
	 */
	private String remark;
	/**
	 * 维修结束后的反馈
	 */
	private String feedback;
	/**
	 * 预约单中的需求零件（id）列表
	 */
	private List<Integer> componentList = new ArrayList<Integer>();
	/**
	 * 预约单中包含的图片url列表
	 */
	private List<String> imgUrltList = new ArrayList<String>();

	/**
	 * Reservation类的构造函数,输入为（预约单id，提交的用户id，预约类型<0/1/2>，预约的维修时间，对应预约场次id<可null>，维修的设备id，维修的问题种类<（可能要改下存储方法）String总结描述>，详细问题的描述<String>）
	 * 
	 * 
	 * @param id               在数据库中对应的id
	 * @param state            3种状态：0表示未受理状态，1表示已受理未完成状态，2表示已完成状态
	 * @param userID           提交该预约单的用户对应的ID
	 * @param applicationType  2种预约类型：0表示活动预约，1表示上门维修预约
	 * @param applicationTime  提交预约申请的时间
	 * @param requiredTime     预约的维修时间
	 * @param place            维修地点，若预约类型type为0-活动预约，place自动设置为活动地点；若预约类型type为1-上门维修预约，place自动设置为user_address
	 * @param repairActivityID 对应预约场次id，若选择的是上门维修（applicationType为1），此项为空；若选择的是活动预约（applicationType为0）repair_actity_id设置为当前活动的场次号
	 * @param equipmentID      选择维修的设备的设备号
	 * @param repairType       选择维修的问题种类
	 * @param detail           详细问题的描述
	 */
	public Reservation(int id, int userID, int applicationType, String requiredTime, int repairActivityID,
			int equipmentID, String repairType, String detail,List<Integer> componentList, List<String> imgUrltList) {
		super();
		this.id = id;
		this.state = 0; // 刚创建的预约单状态为0
		this.userID = userID;
		this.applicationType = applicationType;
		// this.applicationTime = 当前时间;
		this.requiredTime = requiredTime;
		// this.place = 根据applicationType决定;
		this.repairActivityID = repairActivityID;
		this.equipmentID = equipmentID;
		this.repairType = repairType;
		this.detail = detail;
		setComponentList(componentList);
		setImgUrltList(imgUrltList);
	}

	public Reservation() {
		// TODO 自动生成的构造函数存根
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}
	
	//0表示未受理状态，1表示已受理未完成状态，2表示已完成状态
	public String getState() {
	    String stateString= "";
	    if(state <= 0) {
	        stateString = "未受理";
	    }
	    else if(state == 1) {
	        stateString = "已受理未完成";
	    }
	    else if(state >= 2) {
	        stateString = "已完成";
	    }
		return stateString;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int UserID) {
		this.userID = UserID;
	}
	
	//2种预约类型：0表示活动预约，1表示上门维修预约
	public String getApplicationType() {
	    String ApplicationTypeString= "";
        if(state <= 0) {
            ApplicationTypeString = "活动预约";
        }
        else if(state >= 1) {
            ApplicationTypeString = "上门维修";
        }
        return ApplicationTypeString;
	}
	
	public void setApplicationType(int applicationType) {
        this.applicationType = applicationType;
    }
	
	public String getApplicationTime() {
        return applicationTime;
    }
	
	public void setApplicationTime(String applicationTime) {
		this.applicationTime = applicationTime;
	}

	public String getRequiredTime() {
		return requiredTime;
	}

	public void setRequiredTime(String requiredTime) {
		this.requiredTime = requiredTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getRepairActivityID() {
		return repairActivityID;
	}

	public void setRepairActivityID(int repairActivityID) {
		this.repairActivityID = repairActivityID;
	}

	public int getEquipmentID() {
		return equipmentID;
	}

	public void setEquipmentID(int equipmentID) {
		this.equipmentID = equipmentID;
	}

	public String getRepairType() {
		return repairType;
	}

	public void setRepairType(String repairType) {
		this.repairType = repairType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public List<Integer> getComponentList() {
		return componentList;
	}

	public void setComponentList(List<Integer> componentList) {
		this.componentList.clear();
		for (int i = 0; i <= componentList.size(); i++)
			this.componentList.add(componentList.get(i));
	}

	public List<String> getImgUrltList() {
		return imgUrltList;
	}

	public void setImgUrltList(List<String> imgUrltList) {
		this.imgUrltList.clear();
		for (int i = 0; i <= imgUrltList.size(); i++)
			this.imgUrltList.add(imgUrltList.get(i));
	}

}
