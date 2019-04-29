package xiuxiuxiu.dao;

import java.sql.*;
import java.util.*;
import xiuxiuxiu.pojo.*;
import xiuxiuxiu.util.*;

/**
 * @author 黄权焕
 * 单例化程序，提高执行效率，暂未提供多线程保护
 * 将数据库代码封装，管理链接。
 * */
public class BaseDao {
    
    private static Connection instance = null;
    private static PreparedStatement preparedStatement;
    /**
     * 单例模式处理数据库链接，获取
     */
    public static Connection getInstance()
    {
        if(instance == null)
        {
            try {
                instance = DBUtil.getConnection();
            } catch (SQLException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
        return instance;
    }
    /**
    * 根据sql语句与参数返回结果集:适合查询
    * 优势：集中管理链接，避免多次实例化工具类
    * @author 黄权焕
    * @param sql 待处理的sql函数
    * @param params 携带参数的List
    * @return 查询到的数据集
    */
   public static ResultSet executeQuery(String sql, List<Object> params) throws SQLException {
       int index = 1;
       preparedStatement = BaseDao.getInstance().prepareStatement(sql);
       if(params!=null && !params.isEmpty() ) {
           for (int i = 0; i <params.size() ; i++) {
               preparedStatement.setObject(index++,params.get(i));
           }
       }
       return preparedStatement.executeQuery();
   }
   
   /**
    * 根据sql语句与参数返回结果集：适合插入
    * 优势：集中管理链接，避免多次实例化工具类
    * @author 黄权焕
    * @param sql 待处理的sql函数
    * @param params 携带参数的List
    * @return 查询到的数据集
    */
   public static ResultSet getGeneratedKeys(String sql, List<Object> params) throws SQLException {
       int index = 1;
       preparedStatement = BaseDao.getInstance().prepareStatement(sql);
       if(params!=null && !params.isEmpty() ) {
           for (int i = 0; i <params.size() ; i++) {
               preparedStatement.setObject(index++,params.get(i));
           }
       }
       return preparedStatement.getGeneratedKeys();
   }
   
   /**
    * 根据sql语句与参数返回影响条数：适合更新、删除
    * 优势：集中管理链接，避免多次实例化工具类
    * @author 黄权焕
    * @param sql 待处理的sql函数
    * @param params 携带参数的List
    * @return 影响条数
    */
   public static int executeUpdate(String sql, List<Object> params) throws SQLException {
       int index = 1;
       preparedStatement = BaseDao.getInstance().prepareStatement(sql);
       if(params!=null && !params.isEmpty() ) {
           for (int i = 0; i <params.size() ; i++) {
               preparedStatement.setObject(index++,params.get(i));
           }
       }
       return preparedStatement.executeUpdate();
   }
    /**
         * 判断元素是否存在
     * @param id 主键
     * @return true 存在，false 不存在
     * */
    //boolean isExist( final String id ) {
     //   return true;
    //}
    
    /**
         * 根据id返回基本类
     * @param id 主键
     * @return 实体类
     * */
    //Object getObject(final String id);
    
    /**
    
    
    /**
     * 根据id生成查询语句（预处理后的sql语句）
     * 可以安照这个思想自己添加专用的查询语句
     * 排序可以实现此接口后再重载
     * @param object 需要修改的类
     * @return 预处理完的sql语句
     * */
    
    
    /**
     * 根据id生成删除语句（预处理后的sql语句）
     * 
     * @param id 
     * @return 预处理完的sql语句
     * */
   // PreparedStatement deleted(String id);
    
    /**
     * 根据传入类生成更新语句（预处理后的sql语句）
     * 可以安照这个思想自己添加专用的更新语句
     * @param object 需要修改的类
     * @return 预处理完的sql语句
     * */
    //PreparedStatement update(Object object);
    
    /**
     * 根据传入类生成插入语句（预处理后的sql语句）
     * 可以安照这个思想自己添加专用的插入语句
     * @param object 需要修改的类
     * @return 预处理完的sql语句
     * */
    //PreparedStatement insert(Object object);
    
    
    
}
