package excel;

import org.apache.poi.hssf.usermodel.HSSFCell;

public class DataExtractor {

	public String antiNullString(HSSFCell theCell){
        if(theCell != null){
            return(theCell.getRichStringCellValue().getString());
        }
        else{
            return("");
        }
    }
}
