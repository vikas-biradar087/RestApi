package com.exam.rating.service.serviceImpl;

import com.exam.rating.entity.Rating;
import com.exam.rating.exception.ResourceNotFoundException;
import com.exam.rating.repository.RatingRepository;
import com.exam.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;


    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);

    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();

    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);

    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {

        return ratingRepository.findByHotelId(hotelId);
    }


}
