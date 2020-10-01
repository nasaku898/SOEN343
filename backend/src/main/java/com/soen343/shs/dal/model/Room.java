package com.soen343.shs.dal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

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
    private ArrayList<Door>  doors;
    @Column(name = "lights")
    private ArrayList<Light> lights;
    @Column(name = "userIds")
    private ArrayList<Long> userIds;
    @Column(name = "windows")
    private ArrayList<Window> windows;
    @Column(name = "temperature")
    private double temperature;
}
