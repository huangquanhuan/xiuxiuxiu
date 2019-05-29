package com.xiuxiuxiu.service.impl;

import com.xiuxiuxiu.model.Reservation;
import com.xiuxiuxiu.model.Article;
import com.xiuxiuxiu.repository.ReservationRepository;
import com.xiuxiuxiu.service.ReservationService;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;

import java.util.List;

@Service
public class TestReservationServiceImpl{

	@Autowired
    private ReservationServiceImpl service = new ReservationServiceImpl();
	@Autowired
	private Reservation reservation = new Reservation();
	
    @Test
    public void getReservationListTest() {
    	int id	= 0;
    	System.out.println(service.getReservationList().toString());
        Assert.assertThat(service.getReservationList().get(0).getId(),is(id) );
        int i = 0;
        i++;
    }

    @Test
    public void findArticleByIdTest() {
    	reservation = service.getReservationList().get(0);
    	int id = reservation.getId();
    	Assert.assertThat(reservation , is(service.findReservationById(id))); 
    }

    @Test
    public void saveTest() {
    	StudentServiceImpl studentService = new StudentServiceImpl();
    	ActivityServiceImpl activityService = new ActivityServiceImpl();
    	reservation = new Reservation();
    	reservation.setId(10086);
    	reservation.setApplicationType(1);
    	reservation.setActivity(activityService.getActivityList().get(0));
    	reservation.setStudent(studentService.getStudentList().get(0));
    	service.save(reservation);
    	Assert.assertThat(reservation.getId(), is(service.findReservationById(10086).getId()));
    }

    @Test
    @Transactional
    public void editTest() {
    	reservation = service.getReservationList().get(0);
    	int id = reservation.getId();
    	reservation.setApplicationType(1);
    	service.edit(reservation);
    	Assert.assertThat(reservation.getApplicationType(), is(service.findReservationById(id).getApplicationType()));
    }

    @Test
    @Transactional
    public void deleteTest() {
    	reservation = service.getReservationList().get(0);
    	int id = reservation.getId();
    	service.delete(id);
    	Assert.assertNotEquals(null, service.getReservationList().get(0));
    }
}


