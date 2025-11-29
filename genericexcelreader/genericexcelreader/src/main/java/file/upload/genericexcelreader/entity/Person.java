package file.upload.genericexcelreader.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    private String name;

    private String city;

}
