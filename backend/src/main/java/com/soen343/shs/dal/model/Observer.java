package com.soen343.shs.dal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.security.auth.Subject;

@Entity
public abstract class Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Subject subject;

    abstract public void update();
}
