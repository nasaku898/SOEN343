package com.soen343.shs.dal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Window {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean blocked;
    private boolean open;
}
