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

    @ElementCollection
    private Set<Long> parents;

    @ElementCollection
    private Set<Long> children;

    @ElementCollection
    private Set<Long> guests;

    private String city;
}
