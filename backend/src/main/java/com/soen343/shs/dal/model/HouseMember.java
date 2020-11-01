package com.soen343.shs.dal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@Entity
public class HouseMember extends User {
}
