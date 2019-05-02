package xiuxiuxiu.pojo;

/**
 * Equipment - 对应到设备（equipment）表的实体类
 * 
 * @author 刘忠燏
 */
public class Equipment {
    /** 设备的 ID 号 */
    private Integer id;
    
    /** 设备的名称 */
    private String equipmentName;
    
    /** 设备所属的用户，与 user 相对应 */
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
