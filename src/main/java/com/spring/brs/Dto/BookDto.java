package com.spring.brs.Dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

//    @NotEmpty(message = "Book ISBN must not be empty")
    private String isbn;

//    @NotNull(message = "Provide a book rating")
//    @Min(value = 0, message = "Book rating can't be negative")
//    @Max(value = 5, message = "Book rating can't be greater than 5")
    private Double rating;

//    @NotNull(message = "Number of book pages must not be empty")
//    @Min(value = 1, message = "Book must have at least 1 page")
    private Integer noOfPages;

//    @NotNull(message = "Provide book stock count")
//    @Min(value = 0, message = "Stock count can't be negative")
    private Integer stockCount;

//    @NotEmpty(message = "Please provide a valid date")
//    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])", message = "Date format must be \"yyyy-MM-dd\" ")
    private LocalDate publishedDate;
    //used while saving

//    @NotNull(message = "Book photo can't be empty")
    private MultipartFile photo;
    //used while sending data to frontend
    private String photoUrl;
    // private MultipartFile multipartFile;
//    @NotNull(message = "Book category can't be empty")
    private Integer categoryId;

//    @NotEmpty(message = "Book authors can't be empty")
//    @NotNull(message = "Book authors can't be empty")
    private List<Integer> authorId;

    public String getPublishedDate() {
        return publishedDate != null ? this.publishedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = LocalDate.parse(publishedDate);
    }
}
