package com.campusmarket.controller;

import com.campusmarket.common.Result;
import com.campusmarket.dto.ItemCreateRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @PostMapping("/api/items")
    public Result<Void> create(@Valid @RequestBody ItemCreateRequest request) {
        return Result.ok(null);
    }

    @PutMapping("/api/items/{id}")
    public Result<Void> updateItem(@PathVariable Integer id,
                                   @Valid @RequestBody ItemCreateRequest request) {
        return Result.ok(null);
    }
}
