package com.spring.brs.service.impl;

import com.spring.brs.Dto.BookTransactionDto;
import com.spring.brs.entities.BookTransactions;
import com.spring.brs.repo.BookRepo;
import com.spring.brs.repo.BookTransactionsRepo;
import com.spring.brs.repo.MemberRepo;
import com.spring.brs.service.BookTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookTransactionServiceImpl implements BookTransactionService {

    @Autowired
    private BookTransactionsRepo bookTransactionsRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private MemberRepo memberRepo;


    @Override
    public BookTransactionDto saveTransaction(BookTransactionDto bookTransactionDto) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date toDate = simpleDateFormat.parse(bookTransactionDto.getToDate());
//        Date fromDate=simpleDateFormat.parse(bookTransactionDto.getFormDate());stock_count
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = fromDate.plusDays(bookTransactionDto.getNoOfDays());
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
                .formDate(fromDate)
                .toDate(toDate)
                .memberId(entity.getMember().getId())
                .bookId(entity.getBook().getId())
                .build();
    }

    @Override
    public List<BookTransactionDto> findAllTransaction() {
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<BookTransactions> transactionsList = bookTransactionsRepo.findAll();
        return transactionsList.stream().map(entity -> BookTransactionDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .bookName(entity.getBook().getName())
                .bookId(entity.getBook().getId())
                .memberId(entity.getMember().getId())
                .build()).collect(Collectors.toList());
    }

    @Override
    public BookTransactionDto findById(Integer bookTransactionId) {
        BookTransactions b;

        Optional<BookTransactions> optionalBookTransactions = bookTransactionsRepo.findById(bookTransactionId);

        if (optionalBookTransactions.isPresent()) {

            b = optionalBookTransactions.get();
            Period period = Period.between(b.getFormDate(), b.getToDate());
            int diff = period.getDays();
            return BookTransactionDto.builder()
                    .bookId(b.getBook().getId())
                    .memberId(b.getMember().getId())
                    .code(b.getCode())
                    .noOfDays(diff)
                    .build();
        }
        return null;
    }

    @Override
    public void deleteBookTransactionById(Integer id) {
        bookTransactionsRepo.deleteById(id);
    }

    @Override
    public List<String> getAllTransactionCode() {
        return bookTransactionsRepo.findAllTransactionCode();
    }

    @Override
    public List<BookTransactionDto> findAllTransactionCode(String code) {
        return null;


    }
}
