package com.praveen.shethe.security;

import com.praveen.shethe.model.Upayogakarta;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by plank-praveen on 12/27/2016.
 */
public class UpayogakartaAuthentication implements Authentication {

    private final Upayogakarta upayogakarta;
    private boolean isAuthenticated = true;

    public UpayogakartaAuthentication(Upayogakarta upayogakarta) {

        this.upayogakarta = upayogakarta;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return upayogakarta.getAuthorities();
    }

    @Override
    public Object getCredentials() {

        return upayogakarta.getPassword();
    }

    @Override
    public Object getDetails() {

        return upayogakarta;
    }

    @Override
    public Object getPrincipal() {

        return getName();
    }

    @Override
    public boolean isAuthenticated() {

        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {

        return upayogakarta.getUsername();
    }

}
