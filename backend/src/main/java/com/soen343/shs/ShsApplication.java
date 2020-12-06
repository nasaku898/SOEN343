package com.soen343.shs;

import com.soen343.shs.dal.model.City;
import com.soen343.shs.dal.repository.CityRepository;
import com.soen343.shs.dal.repository.ExteriorDoorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ShsApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ShsApplication.class, args);
    }

    // temp...gotta think of something better...
    @Bean
    CommandLineRunner init(final CityRepository cityRepository, final ExteriorDoorRepository exteriorDoorRepository) {

        if (cityRepository.count() == 0) {
            return args -> cityRepository.save(
                    City.builder()
                            .temperatureOutside(0.0)
                            .name("Montreal")
                            .build()
            );
        }
        return null;
    }
}
