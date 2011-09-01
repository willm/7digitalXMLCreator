package excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import main.Xml;

public class TrackExtractor {

	private String[] initTrack = new String[14];
	private HSSFCell cell = null;
	
    public void addTrack(Xml theXml, HSSFRow currentRow){
        
    	HSSFRow row = currentRow;
    	
        for(int k=13; k<27; k++){
                            cell =row.getCell(k);
                            initTrack[k-13] = antiNullString(cell);
                        }
        
        
        theXml.addTrack(
			initTrack[0],
			new Boolean(initTrack[1]),
			initTrack[2],
			initTrack[3],
			new Boolean(initTrack[4]),initTrack[5],
			new Integer(initTrack[6]),initTrack[7],
			initTrack[8],
			initTrack[9],
			initTrack[10],
			initTrack[11],
			initTrack[12],
			initTrack[13]);
    }
    
    public String antiNullString(HSSFCell theCell){
        if(theCell != null){
            return(theCell.getRichStringCellValue().getString());
        }
        else{
            return("");
        }
    }
	
}
