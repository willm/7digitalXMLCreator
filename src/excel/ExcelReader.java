package excel;
//~--- non-JDK imports --------------------------------------------------------

import main.Xml;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

//~--- JDK imports ------------------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;

public class ExcelReader
{

private POIFSFileSystem fileSystem = null;
private InputStream inputStream = null;
private String xlsPath;
private ArrayList<Xml> productsRead;
private HSSFWorkbook workBook;
private HSSFSheet sheet = null;
private HSSFRow row = null;
private HSSFCell cell = null;
private String[] initTrack = new String[14];
private String[] xmlSetupValues = new String[10];
private String[] ters, terDates, terCodes, exters, genres;
private boolean successful = true;
private String distributor = null;

int firstRow=4;


    public ExcelReader(){
        xlsPath = "";
        productsRead= new ArrayList<Xml>();

    }
    
    @SuppressWarnings ("unchecked")
    
    public void reset(){
        productsRead= new ArrayList();
    }

	public void setupFileStream(String thePath) throws Exception {
		try{
            inputStream = new FileInputStream (thePath);
        }
        catch (FileNotFoundException e){
        	e.printStackTrace ();
            throw new Exception("File not found in the specified path.");
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
    
    public static void main (String[] args){
        ExcelReader reader = new ExcelReader ();
    }
    
    public void setPath(String thePath) throws Exception{
        xlsPath = thePath;
        System.out.println("From read: " + xlsPath);
        exceltoxml(xlsPath);
    }
    
    public boolean stringComp(String a, String b){
        return(a.equals(b));
    }
    
    public void sendTerError(){
        System.out.println("TERRITORY ERROR");
    }
    
    public Xml newXml(){
        Xml anXml = new Xml(distributor,xmlSetupValues[0],xmlSetupValues[1],
                                            xmlSetupValues[2],xmlSetupValues[3],xmlSetupValues[4],xmlSetupValues[5],
                                            xmlSetupValues[6],xmlSetupValues[7],xmlSetupValues[8],new Boolean(xmlSetupValues[9]));
        return anXml;
    }
    
    public void addTrack(Xml theXml){
        
        for(int k=13; k<27; k++){
                            cell =row.getCell(k);
                            initTrack[k-13] = antiNullString(cell);
                        }
        theXml.addTrack(initTrack[0],new Boolean(initTrack[1]),initTrack[2],initTrack[3],
                    new Boolean(initTrack[4]),initTrack[5],new Integer(initTrack[6]),initTrack[7],initTrack[8],initTrack[9],
                    initTrack[10],initTrack[11],initTrack[12],initTrack[13]);
    }
    
    public void addTerritory(Xml theXml) throws Exception{
        cell =row.getCell(29);
        String rawTers = antiNullString(cell);
        ters = rawTers.split("-");
        for(int i=0; i<ters.length; i++){
            ters[i] = ters[i].trim();
        }
        
        cell =row.getCell(30);
        String rawDates = antiNullString(cell);
        terDates = rawDates.split("-");
        for(int i=0; i<terDates.length; i++){
            terDates[i] = terDates[i].trim();
        }
        
        cell =row.getCell(31);
        String rawCodes = antiNullString(cell);
        terCodes = rawCodes.split("-");
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
        
        if(ters.length < terDates.length | ters.length < terCodes.length){sendTerError();successful=false;};
        
        cell =row.getCell(32);
        String rawExters = antiNullString(cell);
        exters = rawExters.split("-");
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

    public void addProductGenre(Xml theXml){
        cell = row.getCell(10);
        String rawGenres = antiNullString(cell);
        genres = rawGenres.split("-");
        if(!rawGenres.equals("")){
            for(int i=0; i<genres.length; i++){
                genres[i] = genres[i].trim();
                theXml.addGenre(genres[i]);
            }
        }
    }
    

    
    public void addTrackParticipants(Xml xml) throws Exception{
    	int participantNameColumn = 27;
    	int participantRoleColumn = 28;
    	String[] names = getParticipantNames(participantNameColumn);
    	String[] roles = getParticipantRoles(participantRoleColumn);
    	if(names.length != roles.length){
		    successful = false;
		}
    	for(int i=0; i<roles.length; i++){
    		xml.addTrackParticipant(xml.numberOfTracks()-1,roles[i], names[i]);
    	} 
    	if(!successful){
        	throw new Exception("Track participants error UPC : " +xml.getProduct().Upc);
        }
    }
    
    public void addProductParticipants(Xml xml) throws Exception{
    	int participantNameColumn = 11;
    	int participantRoleColumn = 12;
    	String[] names = getParticipantNames(participantNameColumn);
    	String[] roles = getParticipantRoles(participantRoleColumn);
    	if(names.length != roles.length){
		    successful = false;
		}
    	for(int i=0; i<roles.length; i++){
    		xml.addParticipant(roles[i], names[i]);
    	}
    	if(!successful){
            throw new Exception("Participants error UPC : " + xml.getProduct().Upc);
        }
    }

	public String[] getParticipantRoles(int participantRoleColumn) {
		cell = row.getCell(participantRoleColumn);
		String rawRoles = antiNullString(cell);
		String[] roles = rawRoles.split("-");
		if(!rawRoles.equals("")){
            for(int i=0; i<roles.length; i++){
            	roles[i] = roles[i].trim();
            }
		}
		return roles;
	}

	public String[] getParticipantNames(int parCellStart) {
		cell = row.getCell(parCellStart);
		String rawNames = antiNullString(cell);
		String[] names = rawNames.split("-");
		if(!rawNames.equals("")){
			
		    for(int i=0; i<names.length; i++){
		    	names[i] = names[i].trim();
		    }
		}
		return names;
	}
        
        public void setupCellTypes(){
            for(int i=0; i<=sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                for(int j=0; j<32; j++){
                    cell = row.getCell(j);
                    cell.setCellType(1);
                    System.out.println("The Cell "+i +":"+j+ "type: " + cell.getCellType());
                }
            }
        }

            
    public void exceltoxml(String thePath) throws Exception{

        setupFileStream(thePath);
               
        try{
            setupReadingExcelFile();

            distributor = getDistributor();
            
            row = sheet.getRow(firstRow);
            cell = row.getCell(0);
            
            xmlSetupValues[0] = antiNullString(cell);
            if(productsRead.size() == 0){
            	productsRead.add(readNewProductRow());
            }
            //creates product for first row of document
            
            for(int i=firstRow+1; i<=sheet.getLastRowNum(); i++){
                //counts down to the bottom of excel doc
                row = sheet.getRow(i);
                System.out.println("row:"+i +"last row:" + sheet.getLastRowNum());
                cell =row.getCell(0);

                Xml currentReleaseXml = productsRead.get(productsRead.size()-1);
                if(currentReleaseXml.getProduct().Upc.equals(antiNullString(cell))){
                    addTrack(currentReleaseXml);
                    addTrackParticipants(currentReleaseXml);
                 }
                 
                 else if(!antiNullString(cell).equals("")) {
                	 productsRead.add(readNewProductRow());
                    
                }
            }
            for(int i=0; i<productsRead.size(); i++){
                Xml toprint = (Xml) productsRead.get(i);
                toprint.printXml();
            }
            
            productsRead= new ArrayList();
            
        }
        catch (IOException e){
            e.printStackTrace ();
        }
        int fileAmount = productsRead.size();
        System.out.println(fileAmount +" : xmls were created or modified");
    }

	public Xml readNewProductRow() throws Exception {
		Xml prodXml = initializeProduct();
		addProductGenre(prodXml);
		addProductParticipants(prodXml);
		addTrack(prodXml);
		addTrackParticipants(prodXml);
		addTerritory(prodXml);                                                
		return prodXml;
	}

	public void setupReadingExcelFile() throws IOException {
		fileSystem = new POIFSFileSystem (inputStream);
		workBook = new HSSFWorkbook (fileSystem);
		workBook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);
		sheet = workBook.getSheetAt (0);

		setupCellTypes();
	}



	public Xml initializeProduct() {
		for(int t=0; t<10; t++){
		     cell =row.getCell(t);
		     System.out.println(t + " " + antiNullString(cell));
		     xmlSetupValues[t] = antiNullString(cell);
		    }
            Xml prodXml = newXml();
		return prodXml;
	}



	public String getDistributor() throws Exception {
		row = sheet.getRow(1);
		cell = row.getCell(1);
		if(antiNullString(cell).equals("")){
            throw new Exception("No distributor name supplied");
        }
		return antiNullString(cell);
	}
}