package com.rastapi.brs.Dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MemberDto {
    private Integer id;
    private String email;
    private String name;
    private String mobileNumber;
    private String address;
}
