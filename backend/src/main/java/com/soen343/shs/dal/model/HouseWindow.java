package com.soen343.shs.dal.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class HouseWindow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean blocked;
    private boolean open;
}
