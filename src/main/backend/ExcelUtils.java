package main.backend;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {
    private void exportExcel(String path, String sheetname, List<String> headtable, List<List<String>> value) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetname);
        HSSFRow hssfRow = sheet.createRow(0);

        for (int i = 0; i < headtable.size(); i++) {
            String val = headtable.get(i);
            Cell cell1 = hssfRow.createCell(i);
            cell1.setCellValue(val);
        }

        for (int i = 0; i < value.size(); i++) {
            hssfRow = sheet.createRow((int) i + 1);
            List<String> row = value.get(i);
            for(int j = 0; j < row.size(); j++){
                HSSFCell cell = hssfRow.createCell(j);
                cell.setCellValue(row.get(j));
            }
        }

        try {
            OutputStream outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<List<String>> importExcel(String path) {
        List<List<String>> list=null;
        try
        {
            FileInputStream is =  new FileInputStream(path);
            HSSFWorkbook excel=new HSSFWorkbook(is);
            //获取第一个sheet
            HSSFSheet sheet0=excel.getSheetAt(0);
            for (Iterator rowIterator=sheet0.iterator();rowIterator.hasNext();)
            {
                HSSFRow row=(HSSFRow) rowIterator.next();
                List<String> ro = null;
                for (Iterator iterator=row.cellIterator();iterator.hasNext();)
                {
                    HSSFCell cell=(HSSFCell) iterator.next();
                    ro.add(cell.getStringCellValue());
                }
                list.add(ro);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
}
