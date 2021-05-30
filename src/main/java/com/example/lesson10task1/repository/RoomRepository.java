package com.example.lesson10task1.repository;

import com.example.lesson10task1.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Page<Room> findAllByHotel_Id(Integer hotel_id, Pageable pageable);

    boolean existsByNumberAndFloorAndSize(Integer number, Integer floor, Integer size);
}
