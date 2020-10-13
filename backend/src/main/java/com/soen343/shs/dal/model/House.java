package com.soen343.shs.dal.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany
    private Set<Room> rooms;
    @Column(name = "temperature_outside")
    private double temperatureOutside;

    public House() {
    }
}
