package com.xiuxiuxiu.service.impl;

import com.xiuxiuxiu.model.Student;
import com.xiuxiuxiu.repository.StudentRepository;
import com.xiuxiuxiu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
    public StudentRepository studentRepository;

    @Override
    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(int id) {
        return studentRepository.findById(id);
    }
    
    @Override
    public Student findStudentByPhoneNumber(String phoneNumber) {
    	return studentRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void edit(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(int id) {
        studentRepository.deleteById(id);
    }

     
    public List<Student> findAll()
    {
    	return studentRepository.findAll();
    }
    
	// 分页获得列表
	@Override
	public Page<Student> findAll(int pageNum, int pageSize) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");  //降序
	    Pageable pageable = PageRequest.of(pageNum,pageSize,sort); 
	    Page<Student> pages = studentRepository.findAll(pageable);
	    return pages;
	}
}


