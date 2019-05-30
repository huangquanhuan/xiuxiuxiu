package com.xiuxiuxiu.service.impl;

import com.xiuxiuxiu.model.Reservation;
import com.xiuxiuxiu.model.Reservation;
import com.xiuxiuxiu.repository.ReservationRepository;
import com.xiuxiuxiu.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    
    @Override
	public Page<Reservation> findAll(int pageNum, int pageSize) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");  //降序
	    Pageable pageable = PageRequest.of(pageNum,pageSize,sort); 
	    Page<Reservation> pages = reservationRepository.findAll(pageable);
	    return pages;
	}

	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}
}


