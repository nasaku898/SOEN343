package com.soen343.shs.dal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
@Setter
@Entity
public class ExteriorDoor extends Door {
    private boolean locked;
}