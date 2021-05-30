package com.example.lesson10task1.paylod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Integer number;
    private Integer floor;
    private Integer size;
    private Integer hotelId;
}
