package com.praveen.shethe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SystemConfigService {

    @Autowired
    private Environment environment;

    public void envConfig(){
        environment.getDefaultProfiles();
    }

}
