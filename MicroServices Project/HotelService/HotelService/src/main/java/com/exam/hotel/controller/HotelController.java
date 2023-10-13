package com.exam.hotel.controller;

import com.exam.hotel.entity.Hotel;
import com.exam.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel createHotels = hotelService.createHotel(hotel);
        return new ResponseEntity<>(createHotels, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        List<Hotel> hotelList = hotelService.fetchAllHotel();
        return ResponseEntity.ok(hotelList);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("hotelId") String hotelId){
        return  ResponseEntity.ok(hotelService.getHotelById(hotelId));
    }

}
