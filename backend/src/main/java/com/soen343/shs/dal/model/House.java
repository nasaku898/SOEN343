package com.soen343.shs.dal.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Room> rooms;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Zone> zones;

    @ElementCollection
    private Set<Long> parents;

    @ElementCollection
    private Set<Long> children;

    @ElementCollection
    private Set<Long> guests;

    private String city;

    private double WINTER_TEMPERATURE;

    private double SUMMER_TEMPERATURE;

    @OneToOne(cascade = CascadeType.ALL)
    private SecuritySystem securitySystem;
}
