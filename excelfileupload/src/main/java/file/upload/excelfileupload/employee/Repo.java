package file.upload.excelfileupload.employee;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface Repo extends JpaRepository<Employee, Integer> {
}
