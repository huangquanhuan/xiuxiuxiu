package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.ReservationImgUrl;

import java.util.List;

public interface ImgUrlService {

    public List<ReservationImgUrl> getReservationImgUrlList();

    public ReservationImgUrl findReservationImgUrlById(int id);

    public void save(ReservationImgUrl imgUrl);

    public void edit(ReservationImgUrl imgUrl);

    public void delete(int id);


}
