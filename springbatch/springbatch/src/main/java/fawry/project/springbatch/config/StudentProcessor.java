package fawry.project.springbatch.config;

import fawry.project.springbatch.entity.Student;
import org.springframework.batch.item.ItemProcessor;

public class StudentProcessor implements ItemProcessor<Student, Student> {


    @Override
    public Student process(Student item) throws Exception {
        return null;
    }
}
