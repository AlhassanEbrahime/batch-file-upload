package fawry.project.exceltodb.service;

import fawry.project.exceltodb.domain.Customer;
import fawry.project.exceltodb.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;


    public void saveCustomer(MultipartFile file) throws Exception{
        if(ExcelUploadService.isValidExcelFile(file)){
            List<Customer> customers = ExcelUploadService.getCustomersDataFromExcelFile(file.getInputStream());
            this.customerRepository.saveAll(customers);
        }
        else{
            throw new Exception("Invalid file extension");
        }
    }


}
