package com.example.lesson10task1.controller;

import com.example.lesson10task1.entity.Hotel;
import com.example.lesson10task1.paylod.ApiResponse;
import com.example.lesson10task1.paylod.HotelDTO;
import com.example.lesson10task1.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @PostMapping
    public ApiResponse adHotel(@RequestBody HotelDTO hotelDTO) {
        boolean exists = hotelRepository.existsByName(hotelDTO.getName());
        if (exists) {
            return new ApiResponse("already exists", false);
        }

        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotelRepository.save(hotel);
        return new ApiResponse("success", true);
    }

    @GetMapping
    public List<Hotel> getAllHotel() {
        List<Hotel> all = hotelRepository.findAll();
        return all;
    }

    @GetMapping("/byId/{id}")
    public Hotel getById(@PathVariable Integer id) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()) {
            Hotel hotel = byId.get();
            return hotel;
        }
        return new Hotel();
    }
    @PutMapping("/edit/{id}")
    public ApiResponse editHotel(@PathVariable Integer id, @RequestBody HotelDTO hotelDTO) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("not found Hotel ID", false);
        }
        Hotel hotel = byId.get();
        hotel.setName(hotelDTO.getName());
        boolean exists = hotelRepository.existsByName(hotelDTO.getName());
        if (exists) {
            return new ApiResponse("already exists", false);
        }
        hotelRepository.save(hotel);
        return new ApiResponse("Saved Hotel Name", true);
    }

    @DeleteMapping("/deleted/{id}")
    public ApiResponse deleteById(@PathVariable Integer id) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()){
        hotelRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
            return new ApiResponse("not found hotel id", false);
        }

    }



