package com.example.springrediscache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="user_name")
    @Size(min = 2, max = 15)
    @NotBlank //String'de uzunluğun boş olup olmadığını denetliyor
    private String userName;

    @Column(name="first_name")
    @Size(min = 2, max = 15)
    private String firstName;

    @Column(name="last_name")
    @Size(min = 2, max = 15)
    private String lastName;
}
