package com.soen343.shs.dal.service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class test {
    public static void main(final String[] args) {
        Clock clock = Clock.system(ZoneId.of("Canada/Eastern"));
        System.out.println(clock.millis());
        System.out.println(new Date(clock.millis()));
        Date date = new Date(clock.millis());
        LocalDateTime localDateTime = LocalDateTime.now(clock);
        System.out.println(localDateTime.getMonthValue());
    }
}
