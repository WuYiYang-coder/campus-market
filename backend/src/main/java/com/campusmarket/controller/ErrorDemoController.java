package com.campusmarket.controller;

import com.campusmarket.common.Result;
import com.campusmarket.common.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorDemoController {

    @GetMapping("/api/error")
    public Result<Void> error() {
        throw new BusinessException(1001, "用户不存在");
    }

    @GetMapping("/api/boom")
    public Result<Void> boom() {
        throw new IllegalStateException("boom");
    }
}