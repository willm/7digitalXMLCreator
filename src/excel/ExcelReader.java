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
private ArrayList<Xml> productsInSheet;           
private HSSFWorkbook workBook;
private HSSFSheet sheet = null;
private HSSFRow row = null;
private HSSFCell cell = null;
private String[] initTrack = new String[14];
private String[] initXml = new String[10];
private String[] ters, terDates, terCodes, exters, genres, partNames, partRoles;
private boolean successful = true;
private String distributor = null;

int firstRow=4;


    public ExcelReader(){
        xlsPath = "";
        productsInSheet= new ArrayList<Xml>();

    }
    
    @SuppressWarnings ("unchecked")
    
    public void reset(){
        productsInSheet= new ArrayList();
    }


    public String TestString (String xlsPath){
        InputStream inputStream = null;
        
        try{
            inputStream = new FileInputStream (xlsPath);
            }
        catch (FileNotFoundException e){
            System.out.println ("File not found in the specified path.");
            e.printStackTrace ();
            }
        
        POIFSFileSystem fileSystem = null;
        
        try{
            fileSystem = new POIFSFileSystem (inputStream);
            
            HSSFWorkbook      workBook = new HSSFWorkbook (fileSystem);
            HSSFSheet         sheet    = workBook.getSheetAt (0);
            HSSFRow row = sheet.getRow(10);
            HSSFCell cell = row.getCell(0);
            return antiNullString(cell);
        }
        catch (IOException e){
            e.printStackTrace ();
        }
        return("nothing");
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
    
    public void setPath(String thePath){
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
        Xml anXml = new Xml(distributor,initXml[0],initXml[1],
                                            initXml[2],initXml[3],initXml[4],initXml[5],
                                            initXml[6],initXml[7],initXml[8],new Boolean(initXml[9]));
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
    
    public void addTer(Xml theXml){
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
    
    public void addParticipent(boolean isProduct, Xml theXml){
        int parCellStart;
        if(isProduct){parCellStart = 11;}
        else{parCellStart = 27;}
            cell = row.getCell(parCellStart);
            String rawNames = antiNullString(cell);
            partNames = rawNames.split("-");
            if(!rawNames.equals("")){
                for(int i=0; i<partNames.length; i++){
                    partNames[i] = partNames[i].trim();
                }
            }
            cell = row.getCell(parCellStart+1);
            String rawRoles = antiNullString(cell);
            partRoles = rawRoles.split("-");
            
            if(partNames.length != partRoles.length){
                successful = false;
            }
            //this will return a genre error (maybe a little simplistic)
            if(!rawRoles.equals("")){
                for(int i=0; i<partRoles.length; i++){
                    partRoles[i] = partRoles[i].trim();
                    if(isProduct){theXml.addParticipant(partRoles[i], partNames[i]);}
                    else{theXml.addTrackParticipent(theXml.numberOfTracks()-1, partRoles[i], partNames[i]);}
                }
            }
        }
        
        public void celltype(){
            for(int i=0; i<=sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                for(int j=0; j<32; j++){
                    cell = row.getCell(j);
                    cell.setCellType(1);
                    System.out.println("The Cell "+i +":"+j+ "type: " + cell.getCellType());
                }
            }
        }

            
    public String exceltoxml(String thePath){
        
        int fileAmount =0;
        
        try{
            inputStream = new FileInputStream (thePath);
        }
        catch (FileNotFoundException e){
            return ("File not found in the specified path.");
            //e.printStackTrace ();
        }
               
        try{
            fileSystem = new POIFSFileSystem (inputStream);
            workBook = new HSSFWorkbook (fileSystem);
            workBook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);
            sheet = workBook.getSheetAt (0);
            
            celltype();

            row = sheet.getRow(1);
            cell = row.getCell(1);
            distributor = antiNullString(cell);
            if(distributor.equals("")){
                return("No distributor name supplied");
            }
            row = sheet.getRow(firstRow);
            cell = row.getCell(0);
            initXml[0] = antiNullString(cell);
            if(productsInSheet.size() == 0){
                for(int j=0; j<10; j++){
                        cell =row.getCell(j);
                        initXml[j] = antiNullString(cell);
                    }
                    
                Xml prodXml = newXml();
                addProductGenre(prodXml);
                addParticipent(true, prodXml);
                if(!successful){
                    return("Participents error UPC : " + initXml[0]);
                }
                addTrack(prodXml);
                addParticipent(false, prodXml);
                if(!successful){
                    return("Track participents error UPC : " + initXml[0]);
                }
                addTer(prodXml);
                
                if(!successful){
                    return("territory error UPC : " + initXml[0]);
                }
                
                productsInSheet.add(prodXml);
            }
            //creates product for first row of document
            
            for(int i=firstRow+1; i<=sheet.getLastRowNum(); i++){
                //counts down to the bottom of excel doc
                row = sheet.getRow(i);
                System.out.println("row:"+i +"last row:" + sheet.getLastRowNum());
                cell =row.getCell(0);  
                Xml current = (Xml) productsInSheet.get(productsInSheet.size()-1);
                if(current.getTagVal(1).equals(("\""+antiNullString(cell))+"\"")==true){
                    //checks if next row belongs to same product as the last (getTagVal(1) gets upc out of previous xml)
                    
                    for(int k=10; k<24; k++){
                            cell =row.getCell(k);
                            initTrack[k-10] = antiNullString(cell);
                        }
                    addTrack(current);
                    addParticipent(false, current);
                    if(!successful){
                    return("Track participents error UPC : " + initXml[0]);
                    }
                 }
                 
                 else if(!antiNullString(cell).equals("")) {
                     for(int t=0; t<10; t++){
                         cell =row.getCell(t);
                         System.out.println(t + " " + antiNullString(cell));
                         initXml[t] = antiNullString(cell);
                        }
                Xml prodXml = newXml();
                addProductGenre(prodXml);
                addParticipent(true, prodXml);
                if(!successful){
                    return("Participents error UPC : " + initXml[0]);
                }
                addTrack(prodXml);
                addParticipent(false, prodXml);
                if(!successful){
                    return("Track participents error UPC : " + initXml[0]);
                }
                addTer(prodXml);
                
                if(!successful){
                    return("Territory error UPC : " + initXml[0]);
                }
                                                
                productsInSheet.add(prodXml);
                    
                }
            }
            for(int i=0; i<productsInSheet.size(); i++){
                Xml toprint = (Xml) productsInSheet.get(i);
                toprint.printXml();
            }
            fileAmount = productsInSheet.size();
            productsInSheet= new ArrayList();
            
        }
        catch (IOException e){
            e.printStackTrace ();
        }
        return (fileAmount +" : xmls were created or modified");
    }        
}