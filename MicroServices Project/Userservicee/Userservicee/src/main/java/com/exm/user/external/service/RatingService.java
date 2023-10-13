package com.exm.user.external.service;

import com.exm.user.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@FeignClient(value = "RATING-SERVICE")
public interface RatingService {


    @PostMapping
    Rating createRating(Rating values);

//    @PutMapping("api/rating/{ratingId}")
//    public Rating updateRating(@PathVariable("ratingId") String ratingId,Rating rating);
//
//    @DeleteMapping("/api/rating/{ratingId}")
//    public void deleteRating(@PathVariable("ratingId") String ratingId);
}
