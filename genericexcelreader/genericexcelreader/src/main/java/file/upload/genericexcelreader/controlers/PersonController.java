package file.upload.genericexcelreader.controlers;

import file.upload.genericexcelreader.entity.Person;
import file.upload.genericexcelreader.util.ExcelReader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("person")
public class PersonController {

    private final ExcelReader excelReader;

    @PostMapping(value= "upload", consumes ="multipart/form-data")
    public ResponseEntity<List<Person>> upload(@RequestParam("file")MultipartFile file) throws IOException{
        List<Person> personData = new ArrayList<>();
        personData=excelReader.readExcel(file.getInputStream());
        return ResponseEntity.ok(personData);
    }
}
