package com.rastapi.brs.Dto;

import com.rastapi.brs.enums.RentType;
import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BookTransactionDto {
    private Integer id;
    private String code;
    private String formDate;
    private String toDate;
    private Integer noOfDays;
    private RentType rentType;
//    private Boolean rentStatus;
    private BookDto bookDto;
    private MemberDto memberDto;
   private  Integer bookId;
   private Integer memberId;

}
