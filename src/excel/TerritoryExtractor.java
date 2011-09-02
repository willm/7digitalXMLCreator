package excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import main.Xml;

public class TerritoryExtractor {
	
	private boolean successful = true;
	private int territoryCodesColumn = 29;
	private int territoryDatesColumn = 30;
	private int territoryPriceCodeColumn = 31;
	

	public void addTerritory(Xml theXml, HSSFRow currentRow) throws Exception{
		HSSFRow row = currentRow;
		
		HSSFCell cell =row.getCell(territoryCodesColumn);
        String rawTers = antiNullString(cell);
        String[] ters = rawTers.split("-");
        
        
        for(int i=0; i<ters.length; i++){
            ters[i] = ters[i].trim();
        }
        
        
		cell =row.getCell(territoryDatesColumn);
        String rawDates = antiNullString(cell);
        String[] terDates = rawDates.split("-");
        for(int i=0; i<terDates.length; i++){
            terDates[i] = terDates[i].trim();
        }
        
        
		cell =row.getCell(territoryPriceCodeColumn);
        String rawCodes = antiNullString(cell);
        String[] terCodes = rawCodes.split("-");
        for(int i=0; i<terCodes.length; i++){
            terCodes[i] = terCodes[i].trim();
        }
        
        if(ters.length == terDates.length && ters.length == terCodes.length){
            for(int l=0; l<ters.length; l++){
                theXml.addTerritory(true,ters[l],terDates[l],terCodes[l],"","");
            }
        }
        
        else if(ters.length > terDates.length && ters.length == terCodes.length){
            for(int l=0; l<ters.length; l++){
                if(l<terDates.length){theXml.addTerritory(true,ters[l],terDates[l],terCodes[l],"","");}
                else{theXml.addTerritory(true,ters[l],terDates[terDates.length-1],terCodes[l],"","");}
            }
        }
        
        else if(ters.length == terDates.length && ters.length > terCodes.length){
            for(int l=0; l<ters.length; l++){
                if(l<terCodes.length){theXml.addTerritory(true,ters[l],terDates[l],terCodes[l],"","");}
                else{theXml.addTerritory(true,ters[l],terDates[l],terCodes[terCodes.length-1],"","");}
            }
        }
        
        else if(ters.length > terDates.length && ters.length > terCodes.length){
            for(int i=0; i<ters.length; i++){
                if(i<terCodes.length && i<terDates.length){
                    theXml.addTerritory(true,ters[i],terDates[i],terCodes[i],"","");
                }
                else if(i<terCodes.length && i>=terDates.length){
                    theXml.addTerritory(true,ters[i],terDates[terDates.length-1],terCodes[i],"","");
                }
                else if(i>=terCodes.length && i<terDates.length){
                    theXml.addTerritory(true,ters[i],terDates[i],terCodes[terCodes.length-1],"","");
                }
                else if(i>=terCodes.length && i>=terDates.length){
                    theXml.addTerritory(true,ters[i],terDates[terDates.length-1],terCodes[terCodes.length-1],"","");
                }
            }
        }
        
        if(ters.length < terDates.length | ters.length < terCodes.length){
        	successful=false;
        	};
        
        	
        cell =row.getCell(32);
        String rawExters = antiNullString(cell);
        String[] exters = rawExters.split("-");
        if(!rawExters.equals("")){
            System.out.println("THE EXTER = " + rawExters);
            for(int m=0; m<exters.length; m++){
                theXml.addTerritory(false,exters[m],"","","","");
            }
        }
        if(!successful){
        	throw new Exception("Territory error UPC : " + theXml.getProduct().Upc);
        }
        
    }
	
    public String antiNullString(HSSFCell theCell){
        if(theCell != null){
            return(theCell.getRichStringCellValue().getString());
        }
        else{
            return("");
        }
    }
    
    public void sendTerError(){
        System.out.println("TERRITORY ERROR");
    }
}
