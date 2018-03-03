package com.praveen.shethe.controller;

import com.praveen.shethe.model.Role;
import com.praveen.shethe.model.Upayogakarta;
import com.praveen.shethe.repository.RoleRepository;
import com.praveen.shethe.repository.UpayogakartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

/**
 * Created by Praveenkumar on 3/3/2017.
 */
@RestController(value = "/register")
public class RegistrationController {

    @Autowired
    private UpayogakartaRepository upayogakartaRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * @return An iterable of the list of Books without filter
     * Http.ok will be returned{@code 200 OK}.
     * Http.NOT_FOUND will be returned if not found {@code 404 Not Found}.
     */
    //Todo Need to add doc for principle
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void insertUpayogakarta(@RequestBody Upayogakarta upayogakarta) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        upayogakarta.setExpires(360000);
        Role role = roleRepository.findOne(upayogakarta.getId());
        upayogakarta.setRole(role);
        upayogakarta.setPassword(passwordEncoder.encode(upayogakarta.getPassword()));
        upayogakartaRepository.save(upayogakarta);
    }


    /**
     * @return An iterable of the list of Upayogakarta without filter
     * Http.ok will be returned{@code 200 OK}.
     * Http.NOT_FOUND will be returned if not found {@code 404 Not Found}.
     */
    //Todo Need to add doc for principle
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(Transactional.TxType.NEVER)
    public Iterable<Upayogakarta> getAllUpayogakarta() {
        return upayogakartaRepository.findAll();
    }
}
