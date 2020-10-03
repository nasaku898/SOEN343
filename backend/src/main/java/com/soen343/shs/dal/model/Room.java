package com.soen343.shs.dal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "doors")
    private List<Door> doors;
    @Column(name = "lights")
    private List<Light> lights;
    @Column(name = "userIds")
    private List<Long> userIds;
    @Column(name = "windows")
    private List<Window> windows;
    @Column(name = "temperature")
    private double temperature;
}
