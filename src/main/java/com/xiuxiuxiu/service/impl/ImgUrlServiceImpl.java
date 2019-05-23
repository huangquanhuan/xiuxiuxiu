package com.xiuxiuxiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiuxiuxiu.model.ReservationImgUrl;
import com.xiuxiuxiu.repository.ImgUrlRepository;
import com.xiuxiuxiu.service.ImgUrlService;

import java.util.List;

@Service
public class ImgUrlServiceImpl implements ImgUrlService{

	@Autowired
    private ImgUrlRepository imgUrlRepository;

    @Override
    public List<ReservationImgUrl> getReservationImgUrlList() {
        return imgUrlRepository.findAll();
    }

    @Override
    public ReservationImgUrl findReservationImgUrlById(int id) {
        return imgUrlRepository.findById(id);
    }

    @Override
    public void save(ReservationImgUrl imgUrl) {
        imgUrlRepository.save(imgUrl);
    }

    @Override
    public void edit(ReservationImgUrl imgUrl) {
        imgUrlRepository.save(imgUrl);
    }

    @Override
    public void delete(int id) {
        imgUrlRepository.deleteById(id);
    }
}


