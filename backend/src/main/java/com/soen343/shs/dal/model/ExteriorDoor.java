package com.soen343.shs.dal.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@Setter
@Entity
@EqualsAndHashCode(callSuper = false)
public class ExteriorDoor extends Door {
    private Boolean locked;
}