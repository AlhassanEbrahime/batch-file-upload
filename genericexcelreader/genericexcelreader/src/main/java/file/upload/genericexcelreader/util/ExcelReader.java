package file.upload.genericexcelreader.util;

import file.upload.genericexcelreader.entity.Person;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
public class ExcelReader {

    public List<Person> readExcel(InputStream file) throws IOException {
        List<Person> personData = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(file);
        int sheetsCount = workbook.getNumberOfSheets();
        for(int i = 0 ; i < sheetsCount; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            sheet.forEach(row -> {
                Person person = new Person();
                if (row.getRowNum() > 0) {
                    person.setName(row.getCell(0).getStringCellValue());
                    person.setCity(row.getCell(1).getStringCellValue());
                    personData.add(person);
                }
            });
        }
        return personData;
    }
}