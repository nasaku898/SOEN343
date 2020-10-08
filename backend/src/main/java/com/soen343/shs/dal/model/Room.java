package com.soen343.shs.dal.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double temperature;
    @ManyToMany
    private Set<Door> doors;
    @OneToMany
    private Set<Light> lights;
    @ElementCollection
    private Set<Long> userIds;
    @OneToMany
    private Set<HouseWindow> houseWindows;
}
