package com.xiuxiuxiu.model;

import cn.afterturn.easypoi.excel.annotation.Excel;


import javax.persistence.*;


 
@Entity
public class DemoExcel {
 
	@Id
	@GeneratedValue
@Excel(name = "姓名" ,orderNum = "0")
	private String name;

@Excel(name = "学号",orderNum = "1")
	private String studentId;

@Excel(name = "联系电话",orderNum = "2")
	private String phoneNum;

@Excel(name = "预约地点",orderNum = "3")
	private String address;

@Excel(name = "预约场次",orderNum = "4")
	private String appointments;

@Excel(name = "所需零件",orderNum = "5")
	private String acComponent;

@Excel(name = "预约状态",replace = { "未受理_0", "已受理未完成_1","已完成_2" },orderNum = "6")
	private int repairState;

private int reservationid;
 
    public DemoExcel(Reservation reservation) {
    	reservationid=reservation.getId();
    	name=reservation.getStudent().getName();
    	studentId=reservation.getStudent().getStudentId();
    	phoneNum=reservation.getStudent().getPhoneNumber();
    	//地点
    	address=reservation.getPlace();
    	//预约场次
    	appointments=reservation.getRequiredTime();
    	//零件表 
    	if(reservation.getComponentList().isEmpty()) {
    		acComponent="未选择零件";
    	}else {
    		acComponent=reservation.getComponentList().get(0).getName();
    	}
    	System.out.print(acComponent);
    	//当前维修状态 
    	repairState=reservation.getState();
    }
    public DemoExcel() {
    	
    }

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStudentId() {
		return studentId;
	}


	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}


	@Override
    public String toString() {
        return "DemoExcel{" +
                ",name='" + name +
                ", studentId=" + studentId +
                 ", phoneNum=" + phoneNum +
                 ", address=" + address +
                ", appointments=" + appointments +
                ", component=" + acComponent +
                ", repairState=" + repairState + '}';
    }
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAppointments() {
		return appointments;
	}
	public void setAppointments(String appointments) {
		this.appointments = appointments;
	}
	public String getAcComponent() {
		return acComponent;
	}
	public void setAcComponent(String acComponent) {
		this.acComponent = acComponent;
	}
	public int getRepairState() {
		return repairState;
	}
	public void setRepairState(int repairState) {
		this.repairState = repairState;
	}
	public int getReservationid() {
		return reservationid;
	}
	public void setReservationid(int reservationid) {
		this.reservationid = reservationid;
	}


}
