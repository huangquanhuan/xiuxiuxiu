package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.Reservation;
import com.xiuxiuxiu.model.Student;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ReservationService {

    public List<Reservation> getReservationList();

    public Reservation findReservationById(int id);

    public void save(Reservation reservation);

    public void edit(Reservation reservation);

    public void delete(int id);

    public Page<Reservation> findAll(int pageNum,int pageSize);
    
    public List<Reservation> findAll();
}
