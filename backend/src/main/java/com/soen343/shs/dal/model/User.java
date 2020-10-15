package com.soen343.shs.dal.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToOne
    private Room location;
    private UserRole role;
}
