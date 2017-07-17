package com.praveen.shethe.controller;

import com.praveen.shethe.model.Role;
import com.praveen.shethe.model.Upayogakarta;
import com.praveen.shethe.repository.RoleRepository;
import com.praveen.shethe.repository.UpayogakartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.security.Principal;

/**
 * Created by user on 3/3/2017.
 */
@RestController(value = "/register")
public class RegistrationController {

    @Autowired
    private UpayogakartaRepository upayogakartaRepository;

    @Autowired
    private RoleRepository roleRepository;

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

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(Transactional.TxType.NEVER)
    public Iterable<Upayogakarta> getAllUpayogakarta() {
        return upayogakartaRepository.findAll();
    }


}
