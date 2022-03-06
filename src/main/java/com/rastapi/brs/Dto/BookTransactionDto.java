package com.rastapi.brs.Dto;

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
    private Date formDate;
    private Date toDate;
    private Boolean rentStatus;
    private Boolean activeClosed;

}
