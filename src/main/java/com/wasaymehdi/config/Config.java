package com.wasaymehdi.config;


import com.wasaymehdi.config.singleton.Singleton;
import com.wasaymehdi.email.EmailClient;

public abstract class Config {

    @Singleton
    public EmailClient getEmailClient() {

        return new EmailClient();

    }

}
