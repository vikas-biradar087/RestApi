package com.exam.hotel.service;

import com.exam.hotel.entity.Hotel;

import java.util.List;

public interface HotelService {


    Hotel createHotel(Hotel hotel);

    List<Hotel> fetchAllHotel();

    Hotel getHotelById(String hotelId);
}
