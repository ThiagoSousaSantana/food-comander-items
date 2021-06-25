package com.foodcomander.items.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class FlavorUpdate {

  @NotNull(message = "id flavos is not null")
  private UUID id;

  private String name;
  private String description;
  private Boolean enabled;
  private BigDecimal price;
}
