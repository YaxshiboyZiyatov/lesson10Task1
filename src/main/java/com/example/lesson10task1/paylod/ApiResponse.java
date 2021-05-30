package com.example.lesson10task1.paylod;

import lombok.Data;

@Data
public class ApiResponse {

    String massage;
    boolean status;
    Object object;

    public ApiResponse(String massage, boolean status) {
        this.massage = massage;
        this.status = status;
    }

    public ApiResponse(String massage, boolean status, Object object) {
        this.massage = massage;
        this.status = status;
        this.object = object;
    }
}
