package com.spring.brs.Dto;

import com.spring.brs.enums.RentType;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class BookTransactionDto {

    private Integer id;

//    @NotEmpty(message = "Book code can't be empty")
//    @Pattern(regexp = "^\\w{1,10}",
//            message = "Book code must be alphanumeric and at maximum upto 10 characters long")
    private String code;
    private LocalDate formDate;
    private LocalDate toDate;

//    @NotNull(message = "No. of book renting days can't be null")
//    @Min(value = 1, message = "No. of days can't be negative")
    private Integer noOfDays = 0;
    private RentType rentType;
    //    private Boolean rentStatus;
    private BookDto bookDto;
    private MemberDto memberDto;

//    @NotNull(message = "Please provide a book")
    private Integer bookId;

//    @NotNull(message = "Please provide a member")
    private Integer memberId;
    private String bookName;

}
