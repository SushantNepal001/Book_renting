package com.rastapi.brs.service;

import com.rastapi.brs.Dto.BookDto;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface BookService {
    BookDto saveBook(BookDto bookDto) throws IOException, ParseException;
    List<BookDto> findAllBook();
    BookDto findById(Integer bookId);
    void deleteBookById(Integer bookId);
}
