package com.laredo.service.impl;

import com.laredo.UserEntity;
import com.laredo.repository.UserRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRespository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity authUser =  this.usuarioRepository.findByUserName(username.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));

        return new User(
                authUser.getUsername(),
                authUser.getPassword(),
                authUser.isEnabled(),
                true,
                authUser.isAccountNonExpired(),
                true,
                authUser.getAuthorities()
        );
    }
    
}
