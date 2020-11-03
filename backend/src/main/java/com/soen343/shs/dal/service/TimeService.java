package com.soen343.shs.dal.service;

import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class TimeService {
    private Clock clock;

    public TimeService() {
        clock = ClockSingleton.getClock();
    }

    public long getCurrentDateInMilliseconds() {
        return clock.millis();
    }

    public long setNewDate(final long newTime) {
        final long currentTime = getCurrentDateInMilliseconds();
        final long offSet = newTime - currentTime;
        clock = Clock.offset(clock, Duration.ofMillis(offSet));
        return clock.millis();
    }

    // 0 correspond to midnight and 1440000 24h
    public long getCurrentTimeInMilliseconds() {
        final LocalDateTime localDateTime = LocalDateTime.now(clock);
        final long hoursInMilliseconds = localDateTime.getHour() * 60000;
        final long minuteInMilliSeconds = localDateTime.getMinute() * 60000;
        final long secondsInMilliSeconds = localDateTime.getSecond() * 60000;
        return hoursInMilliseconds + minuteInMilliSeconds + secondsInMilliSeconds;
    }
}
