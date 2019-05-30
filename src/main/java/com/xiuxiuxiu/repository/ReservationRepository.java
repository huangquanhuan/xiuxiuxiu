package com.xiuxiuxiu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.xiuxiuxiu.model.Reservation;
import com.xiuxiuxiu.model.Student;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Reservation findById(int id);

    void deleteById(int id);
    
    public Page<Reservation> findAll(Pageable pageable);
}