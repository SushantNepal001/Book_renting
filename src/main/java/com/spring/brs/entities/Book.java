package com.spring.brs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Book")
@Builder
public class Book {
    @Id
    @SequenceGenerator(name="book_id_sequence",sequenceName = "book_id_sequence")
    @GeneratedValue(generator = "book_id_sequence",strategy = GenerationType.SEQUENCE)
    private Integer id;

   @Column(name = "name",length = 100)
    private String name;

   @Column(name="no_of_pages")
    private  Integer noOfPages;

   @Column(name="isbn", length = 30)
    private String isbn;

    @Column(name="rating")
    private Double rating;

    @Column(name="stock_count")
    private Integer stockCount;

    private LocalDate publishedDate;

    @Column(name="photo_url")
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id",foreignKey = @ForeignKey(name="fk_book_category"))
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="book_author_mapping")
    private List<Author> authorList;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<BookTransactions> bookTransactions;
}
