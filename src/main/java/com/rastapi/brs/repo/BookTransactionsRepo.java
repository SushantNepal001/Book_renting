package com.rastapi.brs.repo;

import com.rastapi.brs.entities.BookTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTransactionsRepo extends JpaRepository<BookTransactions,Integer> {
}
