package excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class DistributorExtractor extends DataExtractor{
	
	private final int distributorRow = 1;
	private final int distributorColumn = 1;
	
	public String getDistributor(HSSFSheet sheet) throws Exception {
		HSSFRow row = sheet.getRow(distributorRow);
		HSSFCell cell = row.getCell(distributorColumn);
		if(antiNullString(cell).equals("")){
            throw new Exception("No distributor name supplied");
        }
		return antiNullString(cell);
	}
}
