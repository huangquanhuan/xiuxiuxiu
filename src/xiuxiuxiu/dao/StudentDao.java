package xiuxiuxiu.dao;

import java.util.List;

import xiuxiuxiu.pojo.Student;

public interface StudentDao {
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
