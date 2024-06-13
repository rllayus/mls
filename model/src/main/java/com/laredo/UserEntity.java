package com.laredo;

import com.laredo.enums.EstadoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Usuario")
@Comment("Esta tabla es para almacenar informacion de usuario")
public class UserEntity implements UserDetails {
    @Comment("Identificador de tabla")
    @Id
    private Long id;

    @Comment("Nombre del usuario")
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "user_name", length = 20,nullable = false)
    private String userName;

    @Column(name = "estado_usuario",length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoUsuario estadoUsuario;

    @Column(name = "password", length = 250, nullable = false)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ROOT"));
        return authorities ;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.estadoUsuario == EstadoUsuario.ACTIVO;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
