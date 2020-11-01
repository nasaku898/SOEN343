package com.soen343.shs.dal.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class SecuritySystem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long houseId;
    @OneToMany
    private Set<Room> rooms;
    private Boolean away;
    private Boolean auto;
    @OneToMany
    private Set<Light> lights;

}
