package com.rastapi.brs.service.impl;

import com.rastapi.brs.Dto.BookTransactionDto;
import com.rastapi.brs.entities.BookTransactions;
import com.rastapi.brs.repo.BookTransactionsRepo;
import com.rastapi.brs.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private BookTransactionsRepo bookTransactionsRepo;


    @Override
    public BookTransactionDto saveTransaction(BookTransactionDto bookTransactionDto) {
        BookTransactions entity = new BookTransactions().builder()
                .id(bookTransactionDto.getId())
                .code(bookTransactionDto.getCode())
                .form_date(bookTransactionDto.getFormDate())
                .toDate(bookTransactionDto.getToDate())
                .build();
        entity = bookTransactionsRepo.save(entity);

        return BookTransactionDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .formDate(entity.getForm_date())
                .toDate(entity.getToDate())
                .build();
    }

    @Override
    public List<BookTransactionDto> findAllTransaction() {
        List<BookTransactions> transactionsList = bookTransactionsRepo.findAll();
        return transactionsList.stream().map(entity -> BookTransactionDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .formDate(entity.getForm_date())
                .toDate(entity.getToDate())
                .build()).collect(Collectors.toList());
    }

    @Override
    public BookTransactionDto findById(Integer memberId) {
        BookTransactions b;
        Optional<BookTransactions> optionalBookTransactions = bookTransactionsRepo.findById(memberId);
        if (optionalBookTransactions.isPresent()) {
            b = optionalBookTransactions.get();
            return BookTransactionDto.builder()
                    .id(b.getId())
                    .code(b.getCode())
                    .formDate((b.getForm_date()))
                    .toDate((b.getToDate()))
                    .build();
        }
        return null;
    }

    @Override
    public void deleteBookTransactionById(Integer id) {
        bookTransactionsRepo.deleteById(id);
    }
}
