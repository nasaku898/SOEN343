package com.soen343.shs.dal.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(unique = true, name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @OneToOne
    private Room location;
}
