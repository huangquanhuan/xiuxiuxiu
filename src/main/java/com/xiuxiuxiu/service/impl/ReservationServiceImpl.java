package com.xiuxiuxiu.service.impl;

import com.xiuxiuxiu.model.Reservation;
import com.xiuxiuxiu.repository.ReservationRepository;
import com.xiuxiuxiu.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getReservationList() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findReservationById(int id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public void edit(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public void delete(int id) {
        reservationRepository.deleteById(id);
    }
}


