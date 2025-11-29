package file.upload.genericexcelreader.easyexcel;

import file.upload.genericexcelreader.PersonRepository;
import file.upload.genericexcelreader.entity.Person;
import file.upload.genericexcelreader.model.PersonExcelData;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Service
public class DAOPrinter {
    @Autowired
    private  PersonRepository personRepository;


    public void print(List<PersonExcelData> persons){
        List<Person> personData = new ArrayList<>();
        persons.forEach(personExcelData -> {
            Person person = new Person();
            person.setName(personExcelData.getName());
            person.setCity(personExcelData.getCity());
            personData.add(person);
        });
        personRepository.saveAll(personData);
    }
}
