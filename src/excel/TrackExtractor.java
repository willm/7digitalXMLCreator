package excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import main.Xml;

public class TrackExtractor extends DataExtractor{

	private String[] track = new String[14];
	private HSSFCell cell = null;
	private int trackStratingColumn = 13;
	private int trackEndingColumn = 27;
	
    public void addTrack(Xml theXml, HSSFRow currentRow){
        
    	HSSFRow row = currentRow;
    	
        
		for(int i=trackStratingColumn; i<trackEndingColumn; i++){
                            cell =row.getCell(i);
                            track[i-trackStratingColumn] = antiNullString(cell);
                        }
        
        
        theXml.addTrack(
			track[0],
			new Boolean(track[1]),
			track[2],
			track[3],
			new Boolean(track[4]),
			track[5],
			new Integer(track[6]),
			track[7],
			track[8],
			track[9],
			track[10],
			track[11],
			track[12],
			track[trackStratingColumn]);
    }	
}
