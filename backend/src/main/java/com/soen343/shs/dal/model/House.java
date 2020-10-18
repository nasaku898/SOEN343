package com.soen343.shs.dal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private long id;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Room> rooms;
    @Column(name = "temperature_outside")
    private double temperatureOutside;

}
