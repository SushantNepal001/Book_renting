package com.rastapi.brs.service;

import com.rastapi.brs.Dto.BookTransactionDto;
import com.rastapi.brs.Dto.MemberDto;
import com.rastapi.brs.enums.RentType;

import java.text.ParseException;
import java.util.List;

public interface BookTransactionService {
    BookTransactionDto saveTransaction(BookTransactionDto bookTransactionDto) throws ParseException;
    //List<BookTransactionDto> findTransactionsByRentType(RentType rentType);
  List<BookTransactionDto> findAllTransaction();
    BookTransactionDto findById(Integer memberId);
    void deleteBookTransactionById(Integer id);
    List<String> findAllTransactionCode();
}
