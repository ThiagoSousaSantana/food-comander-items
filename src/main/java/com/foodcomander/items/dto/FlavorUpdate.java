package com.foodcomander.items.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class FlavorUpdate {

    private String name;
    private String description;
    private Boolean enabled;
    private BigDecimal price;
}
