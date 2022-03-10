package com.spring.brs.Dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MemberDto {
    private Integer id;

//    @NotEmpty(message = "Member email must not be empty")
//    @Email(message = "Member email invalid")
    private String email;

//    @NotEmpty(message = "Member name must not be empty")
//    @Size(max = 80, message = "Member name can only be upto 80 characters")
    private String name;

//    @NotEmpty(message = "Member mobile number must not be empty")
//    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be of 10 digits")
    private String mobileNumber;

//    @NotEmpty(message = "Member address must not be empty")
//    @Size(max = 80, message = "Member address can only be upto 80 characters")
    private String address;
}
