package com.soen343.shs.dal.service;

import java.time.Clock;
import java.time.ZoneId;

public class ClockSingleton {
    private static Clock clock;

    private ClockSingleton(){

    }

    /**
     *
     * @return singleton instance of Clock
     */
    public static Clock getClock() {
        if (clock == null) {
            clock = Clock.system(ZoneId.of("Canada/Eastern"));
        }
        return clock;
    }

}
