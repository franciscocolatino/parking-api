package com.poo.parking_api.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @NotNull
    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.ORDINAL)
    private UserRole role;

    public User(RequestUser requestUser) {
        this.name = requestUser.name();
        this.email = requestUser.email();
        this.password = requestUser.password();
        this.role = requestUser.role();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return "";
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
