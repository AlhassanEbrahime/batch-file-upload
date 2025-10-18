package file.upload.excelfileupload.employee;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final Repo repo;

    public void savFileData(InputStream file) throws IOException {
        List<Employee> employeeList = new LinkedList<>();
        Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            sheet.forEach(row -> {
                Employee employee = new Employee();
                if (row.getRowNum() != 0) {
                    employee.setEmpName(row.getCell(0).getStringCellValue());
                    employee.setEmpSalary(row.getCell(1).getNumericCellValue());
                    employeeList.add(employee);
                }
            });

        repo.saveAll(employeeList);
    }


    public List<Employee> findAll() {
        return repo.findAll();
    }

}
