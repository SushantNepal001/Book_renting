package com.rastapi.brs.service;

import com.rastapi.brs.Dto.AuthorDto;
import com.rastapi.brs.Dto.CategoryDto;

import java.util.List;

public interface AuthorService {
AuthorDto  saveOrUpdateAuthor(AuthorDto authorDto);
List<AuthorDto> findAll();
AuthorDto findById(Integer id);
void deleteAuthorById(Integer id);

}
