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
public class Room extends Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    private double temperature;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Set<Door> doors;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Light> lights;

    @ElementCollection
    private Set<Long> userIds;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<HouseWindow> houseWindows;

    @Override
    public void addObserver(final Observer observer) {
        getObservers().add(observer);
    }

    @Override
    public void removeObserver(final Observer observer) {
        getObservers().remove(observer);
    }

    @Override
    public void notifyObservers() {
        getObservers().forEach(Observer::update);
    }
}
