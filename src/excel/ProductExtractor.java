package excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import main.Xml;

public class ProductExtractor extends DataExtractor {
	
	public Xml extractProduct(String distributor, HSSFRow row){
		String[] xmlSetupValues = new String[10];
			for(int i=0; i<10; i++){
			     HSSFCell cell =row.getCell(i);
			     System.out.println(i + " " + antiNullString(cell));
			     
				xmlSetupValues[i] = antiNullString(cell);
			    }
	            Xml prodXml = newXml(distributor, xmlSetupValues);
			return prodXml;
		
	}

	public Xml newXml(String distributor , String[] xmlSetupValues){
        Xml anXml = new Xml(distributor,xmlSetupValues[0],xmlSetupValues[1],
                xmlSetupValues[2],xmlSetupValues[3],xmlSetupValues[4],xmlSetupValues[5],
                xmlSetupValues[6],xmlSetupValues[7],xmlSetupValues[8],new Boolean(xmlSetupValues[9]));
		return anXml;
	}
}
