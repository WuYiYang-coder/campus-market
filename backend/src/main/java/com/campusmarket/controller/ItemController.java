package com.campusmarket.controller;

import com.campusmarket.common.Result;
import com.campusmarket.dto.ItemCreateRequest;
import com.campusmarket.model.Item;
import com.campusmarket.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/api/items")
    public Result<Item> create(@Valid @RequestBody ItemCreateRequest request) {
        Item item = itemService.create(request);
        return Result.ok(item);
    }

    @GetMapping("/api/items/{id}")
    public Result<Item> getById(@PathVariable Long id) {
        Item item = itemService.getById(id);
        if (item == null) {
            return Result.fail(404, "商品不存在");
        }
        return Result.ok(item);
    }

    @PutMapping("/api/items/{id}")
    public Result<Item> updateItem(@PathVariable Long id,
                                   @Valid @RequestBody ItemCreateRequest request) {
        Item item = itemService.update(id, request);
        if (item == null) {
            return Result.fail(404, "商品不存在");
        }
        return Result.ok(item);
    }

    @DeleteMapping("/api/items/{id}")
    public Result<Void> deleteItem(@PathVariable Long id) {
        boolean success = itemService.deleteById(id);
        if (!success) {
            return Result.fail(404, "商品不存在");
        }
        return Result.ok(null);
    }

    @GetMapping("/api/items")
    public Result<List<Item>> getByStatus(@RequestParam(required = false) String status) {
        List<Item> items = itemService.getByStatus(status);
        return Result.ok(items);
    }
}