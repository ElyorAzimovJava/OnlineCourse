package org.example.onlinecourse.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.onlinecourse.entities.baseEntity.BaseEntity;
import org.example.onlinecourse.enums.UserType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity implements UserDetails {
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String telegramUsername;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserType userType;
    private String password;
    private Boolean isVerified;
    private Boolean isBlocked;
    @JoinTable(name = "user_course")
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Course> courses;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Device device;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_"+this.userType.toString())
        );
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
