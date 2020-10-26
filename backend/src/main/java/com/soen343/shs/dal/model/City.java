package com.soen343.shs.dal.model;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Immutable
    private String name;

    @OneToMany
    private Set<House> houses;

    @Column(name = "temperature_outside")
    private Double temperatureOutside;

}
