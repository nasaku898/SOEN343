package com.soen343.shs.dal.model;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "rooms")
    private Set<Room> rooms;
}
