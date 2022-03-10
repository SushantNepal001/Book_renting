package com.spring.brs.Dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Integer id;

//    @NotEmpty(message = "Category name should not be empty")
//    @Size(max = 50, message = "Category name can only be upto 50 characters")
    private  String name;

//    @NotEmpty(message = "Category description should not be empty")
//    @Size(max = 500, message = "Category description can only be upto 250 characters")
    private String description;
}
