package com.spring.brs.service;

import com.spring.brs.Dto.BookTransactionDto;

import java.text.ParseException;
import java.util.List;

public interface BookTransactionService {
    BookTransactionDto saveTransaction(BookTransactionDto bookTransactionDto) throws ParseException;
    //List<BookTransactionDto> findTransactionsByRentType(RentType rentType);
  List<BookTransactionDto> findAllTransaction();
    BookTransactionDto findById(Integer memberId);
    void deleteBookTransactionById(Integer id);
    List<String> getAllTransactionCode();
    List<BookTransactionDto> findAllTransactionCode(String code);
}
