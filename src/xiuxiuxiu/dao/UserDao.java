package xiuxiuxiu.dao;

import xiuxiuxiu.pojo.*;

import java.util.List;

/**
 * UserDAO - 封装有关 User 表的操作
 * 对 User 表的所有操作应通过实现该接口实现
 *
 * @author Lenovo
 * @date 2019-04-20
 */
public interface UserDao {
  //用户表操作
    void addStudent(Student user);
    void deleteStudent(int id);
    void updateStudent(Student user);
    Student getStudent(int id);
    Student getStudent(int id,String password);//可用来判断密码是否正确
    List<Student> searchStudent(String condition);//返回userID的List
    public int getTotalStudent();
    boolean isStudentExist(int id);
}

