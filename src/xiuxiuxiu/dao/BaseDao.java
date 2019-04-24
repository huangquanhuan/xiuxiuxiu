package xiuxiuxiu.dao;

import java.awt.List;
import com.mysql.jdbc.PreparedStatement;

/**
 * @author 黄权焕
 * 增删查改函数必须返回预处理后的sql语句，PreparedStatement
 * 使用getData调用需要返回数据的函数 如List l = getDate(select(id));
 * 使用execute调用无须返回数据的函数
 * */
public interface BaseDao {
    /**
         * 判断元素是否存在
     * @param id 主键
     * @return true 存在，false 不存在
     * */
    boolean isExist( final String id );
    
    /**
         * 根据id返回基本类
     * @param id 主键
     * @return 实体类
     * */
    Object getObject(final String id);
    
    /**
     * 根据sql语句进行需要返回数据的操作
     * 进行数据库链接与关闭
     * @param ps 预处理完的sql语句
     * @return List 包含查询到的数据集
     */
    List getData(PreparedStatement ps);
    
    /**
     * 根据sql语句进行不返回数据的操作
     * 进行数据库链接与关闭
     * @param ps 预处理完的sql语句
     */
    void execute(PreparedStatement ps);
    
    /**
     * 根据id生成查询语句（预处理后的sql语句）
     * 可以安照这个思想自己添加专用的查询语句
     * 排序可以实现此接口后再重载
     * @param object 需要修改的类
     * @return 预处理完的sql语句
     * */
    PreparedStatement select(String id);
    
    /**
     * 根据id生成删除语句（预处理后的sql语句）
     * 
     * @param id 
     * @return 预处理完的sql语句
     * */
    PreparedStatement deleted(String id);
    
    /**
     * 根据传入类生成更新语句（预处理后的sql语句）
     * 可以安照这个思想自己添加专用的更新语句
     * @param object 需要修改的类
     * @return 预处理完的sql语句
     * */
    PreparedStatement update(Object object);
    
    /**
     * 根据传入类生成插入语句（预处理后的sql语句）
     * 可以安照这个思想自己添加专用的插入语句
     * @param object 需要修改的类
     * @return 预处理完的sql语句
     * */
    PreparedStatement insert(Object object);
    
    
    
}
