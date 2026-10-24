package com.campusmarket.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.db")
public record DbProperties(
        String url,
        String username,
        String password,
        Integer poolSize
) {
}
