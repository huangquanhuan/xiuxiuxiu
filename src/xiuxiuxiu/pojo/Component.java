package xiuxiuxiu.pojo;

//零件表
public class Component {
    //id
    private int id;
    //零件名
    private String name;
    //零件价格
    private double price;
    //零件类型
    private String type;
    //零件数量
    private int quantity;
  
    public Component()
    {
        name="未命名";
        price = 0;
        type = "未知";
    }
    
    public Component(final Component c) {
        this.id = c.id;
        this.name = c.name;
        this.price = c.price;
        this.quantity = c.quantity;
        this.type = c.type;
    }


    public void setQuantity(final int quantity) {

        this.quantity = quantity;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setName(String name) {
        if(!name.equals(""))
            this.name = name;
    }
    
    public String getName() {
        return name;
    }
   
    public void setPrice(double price) {
        if(price >= 0)
            this.price = price;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        if(!type.equals(""))
            this.type = type;
    }
    
    Component getComponent() {
        return this;
    }
    
    Component setComponent(final String name,final double price,final String type,final int quantity) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
        return this;
    }
    
    Component setComponent(final Component c) {
        this.id = c.id;
        this.name = c.name;
        this.price = c.price;
        this.quantity = c.quantity;
        this.type = c.type;
        return this;
    }
}
