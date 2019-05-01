package xiuxiuxiu.pojo;

public class RepairType {
    //维修问题id
    private int id;
    //类型
    private String type;
    //详细分类
    private String classify;
    public int getId() {
        return id;
    }
    
    public RepairType() {
        id = 0;
        type = "software";
        classify = "蓝屏";
    }
    public RepairType(RepairType r) {
        this.id = r.id;
        this.type = r.type;
        this.classify = r.type;
    }
   
    public String getType() {
        return type;
    }
    public String getClassify() {
        return classify;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setClassify(String classify) {
        this.classify = classify;
    }
    
}
