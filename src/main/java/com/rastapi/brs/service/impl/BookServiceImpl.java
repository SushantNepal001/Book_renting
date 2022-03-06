package com.rastapi.brs.service.impl;

import com.rastapi.brs.Dto.BookDto;
import com.rastapi.brs.Dto.CategoryDto;
import com.rastapi.brs.entities.Book;
import com.rastapi.brs.entities.Category;
import com.rastapi.brs.repo.BookRepo;
import com.rastapi.brs.repo.CategoryRepo;
import com.rastapi.brs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Override
    public BookDto saveBook(BookDto bookDto) {
        Book entity=new Book().builder()
                .id(bookDto.getId())
                .name(bookDto.getName())
                .isbn(bookDto.getIsbn())
                .build();
        entity=bookRepo.save(entity);

        return BookDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isbn(bookDto.getIsbn())
                .build();
    }
    @Override
    public List<BookDto> findAllBook() {
        List<Book> bookList=bookRepo.findAll();
        return  bookList.stream().map(entity->BookDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isbn(entity.getIsbn())
                .build()).collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Integer bookId) {
        Book b;
        Optional<Book> optionalBook=bookRepo.findById(bookId);
        if(optionalBook.isPresent()){
            b=optionalBook.get();
            return BookDto.builder()
                    .id(b.getId())
                    .name(b.getName())
                    .isbn(b.getIsbn())
                    .build();
        }
        return null;
    }

    @Override
    public void deleteBookById(Integer bookId) {
        bookRepo.deleteById(bookId);
    }
}
