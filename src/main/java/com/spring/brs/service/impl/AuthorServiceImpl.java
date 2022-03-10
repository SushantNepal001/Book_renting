package com.spring.brs.service.impl;

import com.spring.brs.Dto.AuthorDto;
import com.spring.brs.entities.Author;
import com.spring.brs.repo.AuthorRepo;
import com.spring.brs.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepo authorRepo;

    @Override
    public AuthorDto saveOrUpdateAuthor(AuthorDto authorDto) {
        Author entity = new Author().builder()
                .id(authorDto.getId())
                .name(authorDto.getName())
                .email(authorDto.getEmail())
                .mobileNumber(authorDto.getMobileNumber())
                .build();
        entity = authorRepo.save(entity);

        return AuthorDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .mobileNumber(entity.getMobileNumber())
                .build();


    }

    @Override
    public List<AuthorDto> findAll() {
        List<Author> authorList=authorRepo.findAll();
        return authorList.stream().map(entity->AuthorDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .email(entity.getEmail())
        .mobileNumber(entity.getMobileNumber())
        .build()).collect(Collectors.toList());
    }

    @Override
    public AuthorDto findById(Integer id) {
        Author a;
        Optional<Author> optionalAuthor=authorRepo.findById(id);
        if(optionalAuthor.isPresent()){
            a=optionalAuthor.get();
            return AuthorDto.builder()
                    .id(a.getId())
                    .name(a.getName())
                    .email(a.getEmail())
                    .mobileNumber(a.getMobileNumber())
                    .build();
        }
        return null;

    }

    @Override
    public void deleteAuthorById(Integer id) {
        authorRepo.deleteById(id);

    }
}
