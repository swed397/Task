package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FiltersDto {
    private Integer year;
    private String name;
    private String type;
    private Integer page;
    private Integer size;
}
