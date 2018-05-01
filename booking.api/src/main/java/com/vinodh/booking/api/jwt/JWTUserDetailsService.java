package com.vinodh.booking.api.jwt;

import com.vinodh.booking.api.collections.User;
import com.vinodh.booking.api.domain.JWTUser;
import com.vinodh.booking.api.repository.JWTUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JWTUserDetailsService implements UserDetailsService {


    private JWTUserRepository jwtUserRepository;

    public JWTUserDetailsService(JWTUserRepository jwtUserRepository) {
        this.jwtUserRepository = jwtUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = jwtUserRepository.findByUsername(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Username " + userName + " Not Found"));
        return user.map(JWTUser::new).get();
    }
}
