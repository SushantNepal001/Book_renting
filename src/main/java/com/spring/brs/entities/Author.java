package com.spring.brs.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Author {

    @Id
    @SequenceGenerator(name="author_id_sequence", sequenceName = "author_id_sequence", allocationSize = 1)
    @GeneratedValue(generator="author_id_sequence",strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;


    private String email;

    @Column(name="mobile_number",length = 10)
    private String mobileNumber;


}
