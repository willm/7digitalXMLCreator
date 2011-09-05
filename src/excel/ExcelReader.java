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
private String[] xmlSetupValues = new String[10];
private String distributor = null;
private TrackExtractor trackExtractor = new TrackExtractor();
private ParticipantExtractor participantExtractor= new ParticipantExtractor();
private TerritoryExtractor territoryExtractor = new TerritoryExtractor();
private GenreExtractor genreExtractor = new GenreExtractor();
private DistributorExtractor distributorExtractor = new DistributorExtractor();

private final int firstRow=4;
private ProductExtractor productExtractor = new ProductExtractor();


    public ExcelReader(){
        xlsPath = "";
        productsRead= new ArrayList<Xml>();

    }
    
    public void reset(){
        productsRead= new ArrayList<Xml>();
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
    
    
    public void setPath(String thePath) throws Exception{
        xlsPath = thePath;
        System.out.println("From read: " + xlsPath);
        exceltoxml(xlsPath);
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

            
    public int exceltoxml(String thePath) throws Exception{

        setupFileStream(thePath);
        setupReadingExcelFile();
        
        distributor = distributorExtractor.getDistributor(sheet);

        
        for(int i=firstRow; i<=sheet.getLastRowNum(); i++){
            //counts down to the bottom of excel doc
            row = sheet.getRow(i);
            System.out.println("row:"+i +"last row:" + sheet.getLastRowNum());
            cell =row.getCell(0);

			if(i>firstRow){
			    Xml currentReleaseXml = productsRead.get(productsRead.size()-1);
			    String currentUPC = currentReleaseXml.getProduct().Upc;
				if(currentUPC.equals(antiNullString(cell)) && i>firstRow){
					trackExtractor.addTrack(currentReleaseXml, row);
			        participantExtractor.addTrackParticipants(currentReleaseXml, row);
			     }

			     else if(!antiNullString(cell).equals("") || i == firstRow) {
			    	 productsRead.add(readNewProductRow());
			    }
			}
            else if(i==firstRow){
            	productsRead.add(readNewProductRow());
            }
        }
        
        printFiles();
        productsRead= new ArrayList<Xml>();
        System.out.println(productsRead.size() +" : xmls were created or modified");
        return productsRead.size();
    }
    
    public void printFiles(){
	    for(int i=0; i<productsRead.size(); i++){
	        Xml product = productsRead.get(i);
	        product.printXml();
	    }
	}
	public Xml readNewProductRow() throws Exception {
		Xml prodXml = productExtractor.extractProduct(distributor, row);
		genreExtractor.addProductGenre(prodXml, row);
		participantExtractor.addProductParticipants(prodXml, row);
		trackExtractor.addTrack(prodXml, row);
		participantExtractor.addTrackParticipants(prodXml, row);
		territoryExtractor.addTerritory(prodXml, row);
		return prodXml;
	}

	public void setupReadingExcelFile() throws IOException {
		fileSystem = new POIFSFileSystem (inputStream);
		workBook = new HSSFWorkbook (fileSystem);
		workBook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);
		sheet = workBook.getSheetAt (0);

		setupCellTypes();
	}


}