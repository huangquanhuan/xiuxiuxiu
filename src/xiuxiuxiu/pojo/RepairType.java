package xiuxiuxiu.pojo;

public class RepairType {
    //维修问题id
    private int id;
    //类型
    private String Type;
    //详细分类
    private String classify;
    public int getId() {
        return id;
    }
    
    public RepairType() {
        id = 0;
        Type = "software";
        classify = "蓝屏";
    }
    public RepairType(RepairType r) {
        this.id = r.id;
        this.Type = r.Type;
        this.classify = r.Type;
    }
   
    public String getType() {
        return Type;
    }
    public String getClassify() {
        return classify;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public void setType(String type) {
        Type = type;
    }
    public void setClassify(String classify) {
        this.classify = classify;
    }
    
}
