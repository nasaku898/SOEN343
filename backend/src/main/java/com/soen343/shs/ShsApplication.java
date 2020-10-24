package com.soen343.shs;

import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dal.model.Outside;
import com.soen343.shs.dal.repository.ExteriorDoorRepository;
import com.soen343.shs.dal.repository.OutsideRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShsApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ShsApplication.class, args);
    }

    @Bean
    CommandLineRunner init(final OutsideRepository outsideRepository, final ExteriorDoorRepository exteriorDoorRepository) {

        if (outsideRepository.count() == 0) {
            return args -> outsideRepository.save(
                    Outside.builder().door(exteriorDoorRepository
                            .save(ExteriorDoor
                                    .builder()
                                    .open(false)
                                    .locked(true)
                                    .build()))
                            .build()
            );
        }
        return null;
    }

}
