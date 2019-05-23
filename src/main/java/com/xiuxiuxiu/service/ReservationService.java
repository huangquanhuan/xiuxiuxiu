package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.Reservation;

import java.util.List;

public interface ReservationService {

    public List<Reservation> getReservationList();

    public Reservation findReservationById(int id);

    public void save(Reservation reservation);

    public void edit(Reservation reservation);

    public void delete(int id);


}
