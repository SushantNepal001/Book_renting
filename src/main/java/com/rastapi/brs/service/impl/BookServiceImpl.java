package com.rastapi.brs.service.impl;

import com.rastapi.brs.Dto.BookDto;
import com.rastapi.brs.entities.Book;
import com.rastapi.brs.repo.AuthorRepo;
import com.rastapi.brs.repo.BookRepo;
import com.rastapi.brs.repo.CategoryRepo;
import com.rastapi.brs.service.BookService;
import com.rastapi.brs.utils.FIleStorageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private FIleStorageUtils fIleStorageUtils;

    @Override
    public BookDto saveBook(BookDto bookDto) throws IOException, ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date pubDate = simpleDateFormat.parse(bookDto.getPublishedDate());
        MultipartFile muiltipartFile = bookDto.getPhoto();
        String filepath = fIleStorageUtils.StoreFile(muiltipartFile);

        Book entity = new Book().builder()
                .id(bookDto.getId())
                .name(bookDto.getName())
                .category(categoryRepo.findById(bookDto.getCategoryId()).get())
                .authorList(authorRepo.findAllById(bookDto.getAuthorId()))
                .noOfPages(bookDto.getNoOfPages())
                .rating(bookDto.getRating())
                .stockCount(bookDto.getStockCount())
                .isbn(bookDto.getIsbn())
                .publishedDate(pubDate)
                .photoUrl(filepath)
                .build();
        entity = bookRepo.save(entity);

        return BookDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isbn(entity.getIsbn())
                .categoryId(entity.getCategory().getId())
                .authorId(entity.getAuthorList().stream().map(
                        x -> x.getId()).collect(Collectors.toList()))
                .noOfPages(entity.getNoOfPages())
                .rating(entity.getRating())
                .stockCount(entity.getStockCount())
                .photoUrl(entity.getPhotoUrl())
                .publishedDate(simpleDateFormat.format(entity.getPublishedDate()))
                .build();
    }

    @Override
    public List<BookDto> findAllBook() {
        List<Book> bookList = bookRepo.findAll();
        return bookList.stream().map(entity -> BookDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isbn(entity.getIsbn())
                .build()).collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Integer bookId) {
        Book b;
//        BookDto bookDto;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date pubDate = simpleDateFormat.parse(bookDto.getPublishedDate());
        Optional<Book> optionalBook = bookRepo.findById(bookId);
        if (optionalBook.isPresent()) {
            b = optionalBook.get();
            return BookDto.builder()
                    .name(b.getName())
                    .isbn(b.getIsbn())
                    .photoUrl(b.getPhotoUrl())
                    .stockCount(b.getStockCount())
//                    .publishedDate(simpleDateFormat.format(b.getPublishedDate()))
                    .rating(b.getRating())
                    .noOfPages(b.getNoOfPages())
                    .categoryId(b.getCategory().getId())
                    .authorId((b.getAuthorList().stream().map(x -> x.getId()).collect(Collectors.toList())))
                    .build();
        }
        return null;
    }

    @Override
    public void deleteBookById(Integer bookId) {
        bookRepo.deleteById(bookId);
    }
}
