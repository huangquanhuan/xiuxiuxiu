package com.xiuxiuxiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xiuxiuxiu.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Reservation findById(int id);

    void deleteById(int id);
}