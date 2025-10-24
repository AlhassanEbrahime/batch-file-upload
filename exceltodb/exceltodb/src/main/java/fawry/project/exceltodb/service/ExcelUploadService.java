package fawry.project.exceltodb.service;


import fawry.project.exceltodb.domain.Customer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {

    public static boolean isValidExcelFile(MultipartFile file) {
        String contentType = file.getContentType();
        return Objects.equals(contentType,"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Customer> getCustomersDataFromExcelFile(InputStream inputStream) throws IOException {
        List<Customer> customers = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rowIdx = 0;
        for (Row row : sheet) {
            if(rowIdx == 0){
                rowIdx++;
                continue;
            }
            Iterator<Cell> cellIterator = row.iterator();
            int cellIdx = 0;
            Customer customer = new Customer();
            while(cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                switch (cellIdx) {
                    case 0 -> customer.setCustomerId((int) cell.getNumericCellValue());
                    case 1 -> customer.setFirstName(cell.getStringCellValue());
                    case 2 -> customer.setLastName(cell.getStringCellValue());
                    case 3 -> customer.setCountry(cell.getStringCellValue());
                    case 4 -> customer.setTelephone(cell.getStringCellValue());
                    default -> {}
                }
                cellIdx++;
            }
            customers.add(customer);

        }
        return customers;
    }


}
