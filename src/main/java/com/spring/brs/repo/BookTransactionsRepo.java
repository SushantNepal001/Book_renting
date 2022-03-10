package com.spring.brs.repo;

import com.spring.brs.entities.BookTransactions;
import com.spring.brs.enums.RentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookTransactionsRepo extends JpaRepository<BookTransactions,Integer> {
    @Query(
            value = "SELECT code from tbl_book_transaction",
            nativeQuery = true
    )
    List<String> findAllTransactionCode();

}

