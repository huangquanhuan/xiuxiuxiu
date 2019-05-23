package com.xiuxiuxiu.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Reservation - 预约单类
 * 
 * @author 向鹏
 * @date 2019-5-23
 */
@Entity
@Table(name = "reservation")
public class Reservation {

	/** 在数据库中对应预约单的id */
	@Id
	@GeneratedValue
	private int id;

	/** 预约单的3种状态：0对应"未受理"状态，1对应"已受理,未完成"状态，2对应"已完成"状态 */
	private int state;

	/** 提交该预约单的用户； 定义名为user_id的外键列，该外键引用student表的主键(id)列,采用懒加载 */
	@ManyToOne(targetEntity = Student.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private Student student;

	/** 预约单的2种预约类型：0表示"活动预约"，1表示"上门维修预约" */
	private int applicationType;

	/** 提交预约申请的时间 */
	private String applicationTime;

	/** 预约的维修时间 */
	private String requiredTime;

	/**
	 * 维修地点，若预约类型type为0-活动预约，place自动设置为活动地点；
	 * 若预约类型type为1-上门维修预约，place自动设置为user_address
	 */
	private String place;

	/**
	 * 对应预约场次id，若选择的是上门维修（application_type为1），此项为空；
	 * 若选择的是活动预约（application_type为0）repair_actity_id设置为当前活动的场次号;
	 * 定义名为repair_activity_id的外键列，该外键引用Activity表的id列
	 */
	@ManyToOne(targetEntity = Activity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "repair_activity_id", nullable = true)
	private Activity activity;

	/** 选择维修的设备; 定义名为equipment_id的外键列，该外键引用equipment表的id列 */
	@ManyToOne(targetEntity = Equipment.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "equipment_id", nullable = true)
	private Equipment equipment;

	/**
	 * 维修的问题类型:硬件问题or软件问题
	 */
	private String repairType;

	/** 详细问题的描述 */
	private String detail;

	/** 维修结束后的评价 */
	private String remark;

	/** 维修结束后的反馈 */
	private String feedback;

	/**
	 * 用户在预约单中选中的零件列表；定义该Reservation实体所有关联的Component实体；
	 */
	@ManyToMany(targetEntity = Component.class)
	// 映射连接表为apply_component
	@JoinTable(name = "apply_component",
			// 定义连接表中名为reservation_id的外键列，该外键列参照当前实体对应表(reservation)的主键列(id)
			joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"),
			// 定义连接表中名为component_id的外键列，该外键列参照当前实体关联实体(Component)对应表(component)的主键列(id)
			inverseJoinColumns = @JoinColumn(name = "component_id", referencedColumnName = "id"))
	private List<Component> componentList;

	/**
	 * 预约单中包含的图片url列表； 定义该Reservation实体所有关联的ReservationImgUrl实体；
	 * 指定mappedBy属性表明该Reservation实体不控制关联关系
	 */
	@OneToMany(targetEntity = ReservationImgUrl.class, mappedBy = "reservation")
	private List<ReservationImgUrl> imgUrlList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getApplicationType() {
		return applicationType;
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

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
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

	public List<ReservationImgUrl> getImgUrlList() {
		return imgUrlList;
	}

	public void setImgUrlList(List<ReservationImgUrl> imgUrlList) {
		this.imgUrlList = imgUrlList;
	}

	public List<Component> getComponentList() {
		return componentList;
	}

	public void setComponentList(List<Component> componentList) {
		this.componentList = componentList;
	}

}
