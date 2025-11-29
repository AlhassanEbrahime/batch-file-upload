package file.upload.genericexcelreader.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.sun.net.httpserver.Authenticator;
import file.upload.genericexcelreader.model.PersonExcelData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RequiredArgsConstructor
@RestController
@RequestMapping("upload")
public class UploadController {

    private final DAOPrinter daoPrinter;


    @PostMapping(consumes ="multipart/form-data")
    public String upload(@RequestParam ("file")MultipartFile file)throws IOException {
        EasyExcel.read(file.getInputStream(), PersonExcelData.class, new UploadDataListener(daoPrinter)).sheet().doRead();
        return "success";
    }
}
