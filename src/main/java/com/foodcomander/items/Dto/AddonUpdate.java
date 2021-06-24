package com.foodcomander.items.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddonUpdate {

    private String name;
    private String description;
    private String imageUrl;
    private Boolean enabled;
    private BigDecimal price;
}
