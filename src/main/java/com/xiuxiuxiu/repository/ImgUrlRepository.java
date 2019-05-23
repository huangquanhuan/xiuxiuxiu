package com.xiuxiuxiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xiuxiuxiu.model.ReservationImgUrl;

public interface ImgUrlRepository extends JpaRepository<ReservationImgUrl, Integer> {

    ReservationImgUrl findById(int id);

    void deleteById(int id);
}