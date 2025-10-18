package file.upload.excelfileupload.employee;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class Controller {

    private final EmployeeService employeeService;

    @PostMapping(value = "/upload", consumes="multipart/form-data")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        employeeService.savFileData(file.getInputStream());
        return ResponseEntity.ok("success");
    }
}
