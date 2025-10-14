package com.example.fileupoald.student;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCSVRepresentation {

    @CsvBindByName(column = "id")
    private Long id;

    @CsvBindByName(column = "firstname")
    private String fname;

    @CsvBindByName(column = "lastname")
    private String lname;

    @CsvBindByName(column = "age")
    private int age;
}
