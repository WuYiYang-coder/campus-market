package com.campusmarket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.campusmarket.common.Result;
import com.campusmarket.config.DbProperties;

@RestController
public class DbConfigController {
    private final DbProperties dbProperties;

    public DbConfigController(DbProperties dbProperties) {
        this.dbProperties = dbProperties;
    }

    @GetMapping("/api/config/db")
public Result<DbProperties> dbConfig()
    {
        return Result.ok(dbProperties);
    }
}
