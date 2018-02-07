package com.praveen.shethe.controller;

/**
 * Created by Praveenkumar on 7/2/2018.
 * Controller class to handle REST API calles to /SystemConfig/**
 */

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/systemconfig", produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemConfigurationController {
}
