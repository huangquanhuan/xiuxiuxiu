package xiuxiuxiu.pojo;

//维修场次表
public class RepairActivity {
    //场次id
    private int id;
    //格式2019-04-25(13:30~17:30)
    private String time;
    //场次地点
    private String place;
    //管理员id
    private int managerId;
    
    public RepairActivity()
    {
        id = 0;
        time = "未定";
        place = "未定";
        managerId = 0;
    }
    
    public RepairActivity(final RepairActivity r)
    {
        this.id = r.id;
        this.managerId = r.managerId;
        this.place = r.place;
        this.time = r.time;
    }
    public int getId() {
        return id;
    };
    public int getmanagerId() {
        return managerId;
    }
    public String getPlace() {
        return place;
    }
    public String getTime() {
        return time;
    }
    
    public void setId(int id) {
        if(id >= 0)
            this.id = id;
    }
    public void setmanagerId(int managerId) {
        if(managerId >= 0)
            this.managerId = managerId;
    }
    public void setPlace(String place) {
        if(!place.equals(""))
            this.place = place;
    }
    public void setTime(String time) {
        if(!time.equals(""))
            this.time = time;
    }
    
    public RepairActivity getRepairActivity() {
        return this;
    }
    
    public RepairActivity setRepairActivity(int id,String time,String place,int managerId) {
        if (id >= 0 && !time.equals("") && !place.equals("") && id >= 0)
        {
            this.id = id;
            this.time = time;
            this.place = place;
            this.managerId = managerId;
        }
        return this;
    }
    
    public RepairActivity setRepairActivity(final RepairActivity r) {
        this.id = r.id;
        this.time = r.time;
        this.place = r.place;
        this.managerId = r.managerId;
        return this;
    }
}
