package com.soen343.shs.dal.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
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
    @Column(name = "name")
    private String name;
    @Column(name = "temperature")
    private double temperature;
    @Transient
    private List<Door> doors;
    @Transient
    private List<Light> lights;
    @Transient
    private Set<Long> userIds;
    @Transient
    private List<Window> windows;
}
