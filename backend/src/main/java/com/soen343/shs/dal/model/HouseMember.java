package com.soen343.shs.dal.model;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;


@SuperBuilder
@AllArgsConstructor
@Entity
public class HouseMember extends User {
}
