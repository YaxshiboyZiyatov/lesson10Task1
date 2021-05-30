package com.example.lesson10task1.controller;

import com.example.lesson10task1.entity.Hotel;
import com.example.lesson10task1.entity.Room;
import com.example.lesson10task1.paylod.ApiResponse;
import com.example.lesson10task1.paylod.RoomDTO;
import com.example.lesson10task1.repository.HotelRepository;
import com.example.lesson10task1.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/{hotelId}")
    public Page<Room> getRoomList(@PathVariable Integer hotelId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 2);

        Page<Room> allByHotel_id = roomRepository.findAllByHotel_Id(hotelId, pageable);
        return allByHotel_id;
    }



    @GetMapping("/allRoom")
    public List<Room> getAllRoom() {
        List<Room> all = roomRepository.findAll();
        return all;
    }

    @GetMapping("/roomById/{id}")
    public Room getByIdRoom(@PathVariable Integer id) {
        Optional<Room> byId = roomRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        return new Room();



    }
    @PostMapping("/add")
    public ApiResponse addRoom(@RequestBody RoomDTO roomDTO) {
        boolean exists = roomRepository.existsByNumberAndFloorAndSize(roomDTO.getNumber(), roomDTO.getFloor(), roomDTO.getSize());
        if (exists) {
            return new ApiResponse("Bunday number li, Floor, va Size li Room mavjud", false);
        }
        Room room = new Room();
        room.setNumber(roomDTO.getNumber());
        room.setFloor(roomDTO.getFloor());
        room.setSize(room.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDTO.getHotelId());
        if (!optionalHotel.isPresent()) {
            return new ApiResponse("Not Hotel", false);
        }

        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return new ApiResponse("saved", true);

    }
    @PutMapping("/{id}")
    public ApiResponse editRoom(@PathVariable Integer id, @RequestBody RoomDTO roomDTO){
        boolean exists = roomRepository.existsByNumberAndFloorAndSize(roomDTO.getNumber(), roomDTO.getFloor(), roomDTO.getSize());
        if (exists) {
            return new ApiResponse("Bunday number li, Floor, va Size li Room mavjud", false);
        }
        Optional<Room> byId = roomRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Not found Room ID", false);

        }
        Room room = byId.get();
        room.setNumber(roomDTO.getNumber());
        room.setFloor(roomDTO.getFloor());
        room.setSize(room.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDTO.getHotelId());
        if (!optionalHotel.isPresent()) {
            return new ApiResponse("Not Hotel", false);
        }
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return new ApiResponse("saved", true);


    }
    @DeleteMapping("/deleteHotel/{id}")
    public ApiResponse deleteHotelById(@PathVariable Integer id){
        Optional<Room> byId = roomRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Not found Id", false);

        }
        roomRepository.deleteById(id);
        return new ApiResponse("deleted Hotel", true);
    }


}
