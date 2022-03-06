package com.rastapi.brs.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="category")
@Builder
public class Category {

    @Id
    @SequenceGenerator(name="category_id_sequence",sequenceName = "category_id_sequence")
    @GeneratedValue(generator = "category_id_sequence",strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
}
