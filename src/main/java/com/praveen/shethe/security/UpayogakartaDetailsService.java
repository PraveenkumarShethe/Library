package com.praveen.shethe.security;

import com.praveen.shethe.model.Upayogakarta;
import com.praveen.shethe.repository.UpayogakartaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * Created by plank-praveen on 12/27/2016.
 */
@Service
public class UpayogakartaDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UpayogakartaDetailsService.class);


    @Autowired
    private UpayogakartaRepository upayogakartaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user;
        final Upayogakarta upayogakarta = upayogakartaRepository.findByUsername(username);
        if (upayogakarta != null) {
            user = new User(
                    upayogakarta.getUsername(),
                    upayogakarta.getPassword(),
                    upayogakarta.isEnabled(),
                    upayogakarta.isAccountNonExpired(),
                    upayogakarta.isCredentialsNonExpired(),
                    upayogakarta.isAccountNonLocked(),
                    upayogakarta.getAuthorities()
            );
        } else {
            throw new LibraryUserAuthenticationException("This Upayogakarta was not found");
        }

        return user;
    }

    public Upayogakarta getUpayogakarta(User user) {
        return upayogakartaRepository.findByUsername(user.getUsername());
    }

    public Upayogakarta getUpayogakarta(Principal principal) {
        return getUpayogakarta(
                (User) loadUserByUsername(principal.getName()));
    }
}
