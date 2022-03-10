package com.spring.brs.service;

import com.spring.brs.Dto.AuthorDto;

import java.util.List;

public interface AuthorService {
AuthorDto saveOrUpdateAuthor(AuthorDto authorDto);
List<AuthorDto> findAll();
AuthorDto findById(Integer id);
void deleteAuthorById(Integer id);

}
