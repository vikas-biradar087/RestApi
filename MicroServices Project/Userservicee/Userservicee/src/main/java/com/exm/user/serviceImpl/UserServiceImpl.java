package com.exm.user.serviceImpl;

import com.exm.user.entity.Hotel;
import com.exm.user.entity.Rating;
import com.exm.user.entity.User;
import com.exm.user.exception.ResourceNotFoundException;
import com.exm.user.external.service.HotelService;
import com.exm.user.repository.UserRepository;
import com.exm.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User createUser(User user) {

        String randomUUID = UUID.randomUUID().toString();
        user.setUserId(randomUUID);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        //implement of rating service call using rest template
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public User getUser(String userId) {

        //get user from database with the help of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found Given With Id !!" + userId));

        // fetch rating of the above user from rating service

        //http://localhost:9093/api/rating/user/55f440fe-ba1e-4d98-8cd4-70aa7fc4ae05

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/api/rating/user/"+user.getUserId(), Rating[].class);

        logger.info("{}",ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingsList = ratings.stream().map(rating -> {

            //api call to hotel service to get the hotel
            //http://localhost:9092/hotel/3918ecb7-ebf2-47c9-959e-c035160885e4

//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            
//

            //set the hotel to rating
            rating.setHotel(hotel);

            //return the rating

            return rating;


        }).collect(Collectors.toList());


        user.setRatings(ratingsList);


        return user;
    }
}
