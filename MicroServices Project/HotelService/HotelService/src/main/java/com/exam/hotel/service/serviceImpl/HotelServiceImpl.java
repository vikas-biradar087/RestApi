package com.exam.hotel.service.serviceImpl;

import com.exam.hotel.entity.Hotel;
import com.exam.hotel.exception.ResourceNotFoundException;
import com.exam.hotel.repository.HotelRepository;
import com.exam.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String s = UUID.randomUUID().toString();
        hotel.setId(s);
        return hotelRepository.save(hotel);

    }

    @Override
    public List<Hotel> fetchAllHotel() {
        return hotelRepository.findAll();

    }

    @Override
    public Hotel getHotelById(String hotelId) {
        Hotel getHotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel With Given Id Not Found !!" + hotelId));
        return getHotel;
    }

}
