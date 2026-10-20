package com.campusmarket.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ItemCreateRequest(
        @NotBlank(message = "标题不能为空")
        @Size(max = 50, message = "标题不能超过50个字")
        String title,

        @NotNull(message = "价格不能为空")
        @Min(value = 1, message = "价格应大于零")
        Integer price,

        @NotNull(message = "商品分类不能为空")
        Integer categoryId,

        @Size(max = 500, message = "描述不能超过500字")
        String description,

        @NotNull(message = "库存量不能为空")
        @Min(value = 0, message = "库存量不能小于零")
        @Max(value = 999, message = "库存量不能大于999")
        Integer stock,

        @NotNull(message = "商品状态不能为空")
        @Pattern(regexp = "ON_SALE|SOLD", message = "商品状态只能是ON_SALE或SOLD")
        String status
) {
}
