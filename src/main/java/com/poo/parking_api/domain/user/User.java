package com.poo.parking_api.domain.user;

import com.poo.parking_api.domain.parking.Parking;
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

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.role);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
