package com.campusmarket.model;
public record Item (
        Long id,
        String title,
        Integer price,
        Long categoryId,
        String description,
        Integer stock,
        String status
){
}
