package org.tongji.programming.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author cinea
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MessageDigestPasswordEncoder("MD5");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("loadUser被调用。");
        if ("2152955".equals(username)) {
            return User.withUsername("2152955")
                    .password(passwordEncoder().encode("admin123"))
                    .roles("ADMIN", "TA", "USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
