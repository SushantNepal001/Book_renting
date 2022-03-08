package com.rastapi.brs.service.impl;

import com.rastapi.brs.Dto.BookTransactionDto;
import com.rastapi.brs.entities.BookTransactions;
import com.rastapi.brs.repo.BookRepo;
import com.rastapi.brs.repo.BookTransactionsRepo;
import com.rastapi.brs.repo.MemberRepo;
import com.rastapi.brs.service.BookTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookBookTransactionServiceImpl implements BookTransactionService {

    @Autowired
    private BookTransactionsRepo bookTransactionsRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private MemberRepo memberRepo;


    @Override
    public BookTransactionDto saveTransaction(BookTransactionDto bookTransactionDto) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date toDate = simpleDateFormat.parse(bookTransactionDto.getToDate());
        Date fromDate=simpleDateFormat.parse(bookTransactionDto.getFormDate());
        BookTransactions entity = new BookTransactions().builder()
                .id(bookTransactionDto.getId())
                .code(bookTransactionDto.getCode())
                .formDate(fromDate)
                .toDate(toDate)
                .book(bookRepo.findById(bookTransactionDto.getBookId()).get())
                .member(memberRepo.findById(bookTransactionDto.getMemberId()).get())
                .build();
        entity = bookTransactionsRepo.save(entity);

        return BookTransactionDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .formDate(simpleDateFormat.format(entity.getFormDate()))
                .toDate(simpleDateFormat.format(entity.getToDate()))
                .memberId(entity.getMember().getId())
                .bookId(entity.getBook().getId())
                .build();
    }

    @Override
    public List<BookTransactionDto> findAllTransaction() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<BookTransactions> transactionsList = bookTransactionsRepo.findAll();
        return transactionsList.stream().map(entity -> BookTransactionDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .formDate(simpleDateFormat.format(entity.getFormDate()))
                .toDate(simpleDateFormat.format(entity.getToDate()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public BookTransactionDto findById(Integer memberId) {
        BookTransactions b;
        Optional<BookTransactions> optionalBookTransactions = bookTransactionsRepo.findById(memberId);
        if (optionalBookTransactions.isPresent()) {
            b = optionalBookTransactions.get();
//            return BookTransactionDto.builder()
//                    .id(b.getId())
//                    .code(b.getCode())
//                    .formDate((b.getFormDate()))
//                    .toDate((b.getToDate()))
//                    .build();
        }
        return null;
    }

    @Override
    public void deleteBookTransactionById(Integer id) {
        bookTransactionsRepo.deleteById(id);
    }

    @Override
    public List<String> findAllTransactionCode() {
        return bookTransactionsRepo.findAllTransactionCode();
    }
}
