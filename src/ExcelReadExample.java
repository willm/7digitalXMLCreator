 

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ExcelReadExample {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        //
        // An excel file name. You can create a file name with a full path
        // information.
        //
        String filename = "..\\data.xls";

        //
        // Create an ArrayList to store the data read from excel sheet.
        //
        List sheetData = new ArrayList();

        FileInputStream fis = null;
        try {
            //
            // Create a FileInputStream that will be use to read the excel file.
            //
            fis = new FileInputStream(filename);

            //
            // Create an excel workbook from the file system.
            //
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            //
            // Get the first sheet on the workbook.
            //
            HSSFSheet sheet = workbook.getSheetAt(0);

            //
            // When we have a sheet object in hand we can iterator on each
            // sheet's rows and on each row's cells. We store the data read
            // on an ArrayList so that we can printed the content of the excel
            // to the console.
            //
            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();

                List data = new ArrayList();
                while (cells.hasNext()) {
                    HSSFCell cell = (HSSFCell) cells.next();
                    data.add(cell);
                }

                sheetData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }

        showExelData(sheetData);
    }

    private static void showExelData(List sheetData) {
        //
        // Iterates the data and print it out to the console.
        //
        for (int i = 0; i < sheetData.size(); i++) {
            List list = (List) sheetData.get(i);
            for (int j = 0; j < list.size(); j++) {
                HSSFCell cell = (HSSFCell) list.get(j);
                System.out.print(cell.getRichStringCellValue().getString());
                if (j < list.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("");
        }
    }
}
