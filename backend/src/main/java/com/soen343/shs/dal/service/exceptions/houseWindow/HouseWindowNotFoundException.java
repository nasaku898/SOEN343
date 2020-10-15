package com.soen343.shs.dal.service.exceptions.houseWindow;

public class HouseWindowNotFoundException extends RuntimeException{
    public HouseWindowNotFoundException(){
        super("Window not found");
    }

    public HouseWindowNotFoundException(String msg){
        super(msg);
    }
}
