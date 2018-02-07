package com.praveen.shethe.controller;


import com.praveen.shethe.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;


/**
 * Created by Praveenkumar on 4/9/2017.
 * Controller class to handle REST API calles to /books/**
 */
@RestController
@RequestMapping(value = "/digitalstores", produces = MediaType.APPLICATION_JSON_VALUE)
public class DigitalLibraryController {

    @Autowired
    private FileUploadService fileUploadService;


    /**
     * Add a Library to database.
     * @param inputFile The Digital files object to be inserted
     * {@code 201 Created}. for creating an object
     * {@code 400 Bad Request}. for all other requests
     */
    @RequestMapping(headers = ("content-type=multipart/*"), method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void upload(@RequestParam("file") MultipartFile inputFile) throws IOException {
        fileUploadService.saveDigitalCopy(inputFile);
    }


}