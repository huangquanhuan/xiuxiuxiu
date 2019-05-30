package com.xiuxiuxiu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.xiuxiuxiu.model.Manager;
import com.xiuxiuxiu.model.Reservation;
import com.xiuxiuxiu.model.Student;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    Manager findById(int id);

    void deleteById(int id);

	Manager findByPhoneNumber(String phoneNumber);

	public Page<Manager> findAll(Pageable pageable);
}