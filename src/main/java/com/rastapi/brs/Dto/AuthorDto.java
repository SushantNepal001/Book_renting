package com.rastapi.brs.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AuthorDto {
    private Integer id;
    private String name;
    private String email;
    private String mobileNumber;
}
