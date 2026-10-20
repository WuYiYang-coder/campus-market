package com.campusmarket.controller;

import com.campusmarket.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public Result<Map<String, String>> health(
            @RequestParam(defaultValue = "world") String name) {
        return Result.ok(Map.of(
                "status", "ok",
                "message", "hello" + name
        ));
    }
}
