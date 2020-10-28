package com.soen343.shs.configuration.security;

import com.soen343.shs.dal.model.RealUser;
import com.soen343.shs.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SHSUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return new SHSUserDetails(userRepository.findByUsername(RealUser.class, username)
                .orElseThrow(() -> new UsernameNotFoundException(username)));
    }
}
