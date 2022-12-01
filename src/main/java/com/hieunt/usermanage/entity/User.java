package com.hieunt.usermanage.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private Date dateOfBirth;

    private String gender;
}
