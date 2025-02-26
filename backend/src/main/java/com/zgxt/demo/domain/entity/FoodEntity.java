package com.zgxt.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodEntity {
    String timestamp;
    String produce;
    String name;
    String current;
    String address;
    String quality;
}
