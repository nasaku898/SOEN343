package com.soen343.shs.configuration.security;

import com.soen343.shs.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SHSUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public SHSUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return new SHSUserDetails(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username)));
    }
}
