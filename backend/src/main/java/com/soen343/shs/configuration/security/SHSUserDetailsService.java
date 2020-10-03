package com.soen343.shs.configuration.security;

import com.soen343.shs.dal.model.User;
import com.soen343.shs.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SHSUserDetailsService implements UserDetailsService {
    private final UserRepository USER_REPOSITORY;

    @Autowired
    public SHSUserDetailsService(UserRepository USER_REPOSITORY) {
        this.USER_REPOSITORY = USER_REPOSITORY;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(USER_REPOSITORY.findByUsername(username));

        user.orElseThrow(() -> new UsernameNotFoundException(username));

        return user.map(SHSUserDetails::new).get();
    }
}
