package xiuxiuxiu.dao;

import java.util.List;

import xiuxiuxiu.pojo.Component;

public interface ComponentDao {
    //零件库表操作
    void addComponent(Component component);
    void deleteComponent(int id);
    public int getTotalComponent();
    void updateComponent(Component component);
    Component getComponent(int id);
    List<Component> searchComponent(String str);//返回id的List
    boolean isComponentExist(int id);
}
