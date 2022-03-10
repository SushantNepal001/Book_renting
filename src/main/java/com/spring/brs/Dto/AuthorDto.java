package com.spring.brs.Dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AuthorDto {
    private Integer id;
    @NotEmpty(message = "Author name should not be empty")
    @Size(max = 50, message = "Author name can only be upto 50 characters")
    private String name;

//    @NotEmpty(message =  "Author email should not be empty")
//    @Email(message = "Author email invalid")
    private String email;

//    @NotEmpty(message = "Author mobile number must not be empty")
//    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be of 10 digits")
    private String mobileNumber;
}
