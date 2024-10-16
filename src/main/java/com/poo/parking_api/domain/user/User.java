package com.poo.parking_api.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Table(name = "users")
@Entity(name = "users")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String password;
    private Integer role;

    public User(RequestUser requestUser) {
        this.name = requestUser.name();
        this.email = requestUser.email();
        this.password = requestUser.password();
        this.role = requestUser.role();
    }
}
