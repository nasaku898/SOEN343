package com.soen343.shs.dal.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Door {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean open;
    @ElementCollection
    Set<String> rooms;
}
