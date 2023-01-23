package com.catacid.catacidtg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class BotConfig {

    @Value("${bot.name}")
    String name = "catacid";

    @Value("${bot.token}")
    String token = "5917455658:AAFy7JcrYABri-plrq9GmDfzN-WRARj6LjE";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
