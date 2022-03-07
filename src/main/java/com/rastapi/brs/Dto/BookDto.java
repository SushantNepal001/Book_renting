package com.rastapi.brs.Dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BookDto {
    private Integer id;
    private String name;
    private String isbn;
    private Double rating;
    private Integer noOfPages;
    private Integer stockCount;
    private String publishedDate;
    //used while saving
    private MultipartFile photo;
    //used while sending data to frontend
    private String photoUrl;
    // private MultipartFile multipartFile;
    private Integer categoryId;
    private List<Integer> authorId;
}
