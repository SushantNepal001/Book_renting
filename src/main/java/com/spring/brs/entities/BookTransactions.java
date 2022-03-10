package com.spring.brs.entities;

import com.spring.brs.enums.RentType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_book_transactions")
public class BookTransactions {

    @Id
    @SequenceGenerator(name="book_transactions_sequence",sequenceName = "book_transactions_sequence")
    @GeneratedValue(generator = "book_transactions_sequence",strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String code;

    private LocalDate formDate;

    private LocalDate toDate;

    @Enumerated(value =EnumType.STRING)
    private RentType rentType;
//    private Boolean rent_status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id",foreignKey = @ForeignKey(name="fk_book_transaction_member"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id",foreignKey = @ForeignKey(name = "fk_book_transaction_book"))
    private Book book;


}
