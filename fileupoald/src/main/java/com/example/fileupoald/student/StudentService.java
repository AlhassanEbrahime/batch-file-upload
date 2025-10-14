package com.example.fileupoald.student;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Integer uploadStudents(MultipartFile file) throws IOException {
        Set<Student> students = parseCSV(file);
        studentRepository.saveAll(students);
        return students.size();
    }


    private Set<Student> parseCSV(MultipartFile file) throws IOException {
        try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            HeaderColumnNameMappingStrategy<StudentCSVRepresentation> strategy =
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(StudentCSVRepresentation.class);

            //mapping this CSV file to a bean of type StudentCSVRepresentation
            CsvToBean<StudentCSVRepresentation> csvToBean =
                    new CsvToBeanBuilder<StudentCSVRepresentation>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();


            // parsing csv and map it to object of type student
            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> Student.builder()
                            .id(csvLine.getId())
                            .firstName(csvLine.getFname())
                            .lastName(csvLine.getLname())
                            .age(csvLine.getAge())
                            .build())
                    .collect(Collectors.toSet());
        }
    }
}
