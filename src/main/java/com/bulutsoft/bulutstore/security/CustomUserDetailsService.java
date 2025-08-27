package com.bulutsoft.bulutstore.security;

import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + username));
        if (user.getStatus() != com.bulutsoft.bulutstore.entity.UserStatus.ACTIVE) {
            throw new UsernameNotFoundException("Kullanıcı aktif değil: " + username);
        }
        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name());
        return builder.build();
    }
}
