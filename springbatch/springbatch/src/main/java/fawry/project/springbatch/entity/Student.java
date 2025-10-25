package fawry.project.springbatch.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "age")
    private int age;
}
