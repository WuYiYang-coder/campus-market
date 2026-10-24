package com.campusmarket.controller;

import com.campusmarket.common.Result;
import com.campusmarket.config.AppProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {
    private final AppProperties appProperties;

    public ConfigController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping("/api/config")
    public Result<AppProperties> config() {
        return Result.ok(appProperties);
    }
}