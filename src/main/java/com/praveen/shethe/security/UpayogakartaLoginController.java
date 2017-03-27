package com.praveen.shethe.security;

import com.praveen.shethe.model.Upayogakarta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

/**
 * Created by praveenkumar on 12/27/2016.
 */
@RestController
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
public class UpayogakartaLoginController {


    @Autowired
    private UpayogakartaDetailsService upayogakartaDetailsService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(Transactional.TxType.NEVER)
    public void authenticate(@RequestBody Upayogakarta user, BindingResult bindingResult, HttpServletResponse httpServletResponse) {

        UserDetails userDetails = upayogakartaDetailsService.loadUserByUsername(user.getUsername());
        Upayogakarta upayogakarta = upayogakartaDetailsService.getUpayogakarta((User) userDetails);
        if (upayogakarta == null) {
            throw new LibraryUserAuthenticationException("Upayogakarta not found");
        }
        tokenAuthenticationService.addAuthenticationTokenInHeader(httpServletResponse
                , new UpayogakartaAuthentication(upayogakarta));
    }

}
