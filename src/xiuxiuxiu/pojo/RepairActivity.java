package xiuxiuxiu.pojo;

//维修场次表
public class RepairActivity {
	// 场次id
	private int id;
	// 格式2019-04-25(13:30~17:30)
	private String time;
	// 场次地点
	private String place;
	// 管理员id
	private int managerID;

	public RepairActivity() {
		id = 0;
		time = "未定";
		place = "未定";
		managerID = 0;
	}

	public RepairActivity(final RepairActivity r) {
		this.id = r.id;
		this.managerID = r.managerID;
		this.place = r.place;
		this.time = r.time;
	}

	public int getID() {
		return id;
	};

	public int getmanagerID() {
		return managerID;
	}

	public String getPlace() {
		return place;
	}

	public String getTime() {
		return time;
	}

	public void setID(int id) {
		if (id >= 0)
			this.id = id;
	}

	public void setmanagerID(int managerID) {
		if (managerID >= 0)
			this.managerID = managerID;
	}

	public void setPlace(String place) {
		if (!place.equals(""))
			this.place = place;
	}

	public void setTime(String time) {
		if (!time.equals(""))
			this.time = time;
	}

	public RepairActivity getRepairActivity() {
		return this;
	}

	public RepairActivity setRepairActivity(int id, String time, String place, int managerID) {
		if (id >= 0 && !time.equals("") && !place.equals("") && id >= 0) {
			this.id = id;
			this.time = time;
			this.place = place;
			this.managerID = managerID;
		}
		return this;
	}

	public RepairActivity setRepairActivity(final RepairActivity r) {
		this.id = r.id;
		this.time = r.time;
		this.place = r.place;
		this.managerID = r.managerID;
		return this;
	}
}
