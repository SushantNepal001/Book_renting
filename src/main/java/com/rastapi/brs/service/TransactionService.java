package com.rastapi.brs.service;

import com.rastapi.brs.Dto.BookTransactionDto;
import com.rastapi.brs.Dto.MemberDto;

import java.util.List;

public interface TransactionService {
    BookTransactionDto saveTransaction(BookTransactionDto bookTransactionDto);
    List<BookTransactionDto> findAllTransaction();
    BookTransactionDto findById(Integer memberId);
    void deleteBookTransactionById(Integer id);
}
