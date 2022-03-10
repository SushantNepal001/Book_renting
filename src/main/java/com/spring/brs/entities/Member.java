package com.spring.brs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Table(name = "tbl_member")
public class Member {
    @Id
    @SequenceGenerator(name = "member_id_sequence", sequenceName = "member_id_sequence")
    @GeneratedValue(generator = "member_id_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    private String address;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookTransactions> bookTransactions;
}
