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
    private final UserRepository userRepository;

    @Autowired
    public SHSUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final Optional<User> user = userRepository.findByUsername(username);
        return user.map(SHSUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
