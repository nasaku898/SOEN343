package com.soen343.shs.dal.service;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TimeServiceTest {
    private final static long DATE_IN_MILLISECONDS = 1534694562000L;
    private Clock clock;

    @Mock
    private TimeService timeService;

    private Instant instant;

    @BeforeEach
    public void setup() {
        instant = Instant.parse("2018-08-19T16:02:42.00Z");
        clock = Clock.fixed(instant, ZoneId.of("Canada/Eastern"));
    }

    @Test
    public void getCurrentDateInMillisecondsTest() {
        when(timeService.getCurrentDateInMilliseconds()).thenReturn(clock.millis());
        final long mockDate = timeService.getCurrentDateInMilliseconds();
        Assert.assertEquals(DATE_IN_MILLISECONDS, mockDate);
    }

    @Test
    public void setNewDateSuccessfully() {
        final long newTime = DATE_IN_MILLISECONDS + 86400000;
        clock = Clock.offset(clock, Duration.ofMillis(newTime));
        when(timeService.setNewDate(newTime)).thenReturn(clock.millis());

        final long offSetTime = timeService.setNewDate(newTime);

        Assert.assertEquals(clock.millis(), offSetTime);
    }
}
