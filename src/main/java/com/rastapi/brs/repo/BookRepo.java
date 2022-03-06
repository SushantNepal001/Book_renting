package com.rastapi.brs.repo;

import com.rastapi.brs.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {
}
