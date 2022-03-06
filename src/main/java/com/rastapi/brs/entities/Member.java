package com.rastapi.brs.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Table(name="tbl_member")
public class Member {
    @Id
    @SequenceGenerator(name="member_id_sequence",sequenceName = "member_id_sequence")
    @GeneratedValue(generator="member_id_sequence",strategy = GenerationType.SEQUENCE)
    private Integer id;

    private  String name;

    private String email;

    @Column(name="mobile_number")
    private String mobileNumber;

    private String address;
}
