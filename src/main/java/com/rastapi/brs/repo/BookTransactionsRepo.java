package com.rastapi.brs.repo;

import com.rastapi.brs.entities.BookTransactions;
import com.rastapi.brs.enums.RentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookTransactionsRepo extends JpaRepository<BookTransactions,Integer> {
    @Query(
            value = "SELECT code from tbl_book_transaction",
            nativeQuery = true
    )
    List<String> findAllTransactionCode();

    List<BookTransactions> findAllByRentType(RentType rentType);
}

