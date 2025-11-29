package file.upload.genericexcelreader.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import file.upload.genericexcelreader.model.PersonExcelData;

import java.util.List;

public class UploadDataListener implements ReadListener<PersonExcelData> {

    private static final int BATCH_COUNT=10000;

    private List<PersonExcelData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);


    private DAOPrinter dao;

    public UploadDataListener(){
        dao = new DAOPrinter();
    }


    public UploadDataListener(DAOPrinter daoPrinter){
        this.dao = daoPrinter;
    }

    @Override
    public void invoke(PersonExcelData person, AnalysisContext analysisContext) {

        cachedDataList.add(person);
        if(cachedDataList.size()>=BATCH_COUNT){
            printData();

            cachedDataList=ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        printData();
    }

    private void printData(){
        dao.print(cachedDataList);
    }
}
