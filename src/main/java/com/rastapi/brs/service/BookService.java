package com.rastapi.brs.service;

import com.rastapi.brs.Dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto saveBook(BookDto bookDto);
    List<BookDto> findAllBook();
    BookDto findById(Integer bookId);
    void deleteBookById(Integer bookId);
}
