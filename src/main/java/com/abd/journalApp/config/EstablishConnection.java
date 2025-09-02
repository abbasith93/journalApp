package com.abd.journalApp.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class EstablishConnection {

    @Bean
    public PlatformTransactionManager DBConnection(MongoDatabaseFactory dbfFactory){
        return new MongoTransactionManager(dbfFactory);
    }
}
