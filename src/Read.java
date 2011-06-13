//~--- non-JDK imports --------------------------------------------------------

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
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

public class Read
{

private String xlsPath;
private ArrayList productsInSheet;

    public Read(){
        xlsPath = "";
        productsInSheet= new ArrayList();
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
        Read reader = new Read ();
        //String         xlsPath    = "C:\\Documents and Settings\\Administrator\\Desktop\\7Digital\\test.xls";
        
        //reader.displayFromExcel (xlsPath);
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
        
    public void exceltoxml(String thePath){
        InputStream inputStream = null;
        
        try{
            inputStream = new FileInputStream (thePath);
            }
        catch (FileNotFoundException e){
            System.out.println ("File not found in the specified path.");
            e.printStackTrace ();
            }
        
        POIFSFileSystem fileSystem = null;
        
        try{
            fileSystem = new POIFSFileSystem (inputStream);
            
            HSSFWorkbook      workBook = new HSSFWorkbook (fileSystem);
            workBook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);

            HSSFSheet         sheet    = workBook.getSheetAt (0);
            HSSFRow row = sheet.getRow(1);
            HSSFCell cell = row.getCell(0);
            String[] initTrack = new String[14];
            String[] initXml = new String[10];
            String[] ters, terDates, terCodes, exters;
            initXml[0] = antiNullString(cell);
                        
            if(productsInSheet.size() == 0){
                for(int j=0; j<10; j++){
                        cell =row.getCell(j);
                        initXml[j] = antiNullString(cell);
                    }
                Xml prodXml = new Xml("testdistributor",initXml[0],initXml[1],initXml[2],initXml[3],initXml[4],initXml[5],initXml[6],initXml[7],initXml[8],initXml[9]);
                for(int k=10; k<24; k++){
                            cell =row.getCell(k);
                            initTrack[k-10] = antiNullString(cell);
                        }
                prodXml.addTrack(initTrack[0],initTrack[1],initTrack[2],initTrack[3],
                    initTrack[4],initTrack[5],initTrack[6],initTrack[7],initTrack[8],initTrack[9],
                    initTrack[10],initTrack[11],initTrack[12],initTrack[13]);
                            
                cell =row.getCell(24);
                String rawTers = antiNullString(cell);
                ters = rawTers.split("-");
                cell =row.getCell(25);
                String rawDates = antiNullString(cell);
                terDates = rawDates.split("-");
                cell =row.getCell(26);
                String rawCodes = antiNullString(cell);
                terCodes = rawCodes.split("-");
                
                if(ters.length == terDates.length && ters.length == terCodes.length){
                    for(int l=0; l<ters.length; l++){
                        prodXml.addTerritory("true",ters[l],terDates[l],terCodes[l],"","");
                    }
                }
                if(ters.length > terDates.length && ters.length == terCodes.length){
                    for(int l=0; l<ters.length; l++){
                        if(l<terDates.length){prodXml.addTerritory("true",ters[l],terDates[l],terCodes[l],"","");}
                        else{prodXml.addTerritory("true",ters[l],terDates[terDates.length-1],terCodes[l],"","");}
                    }
                }
                if(ters.length == terDates.length && ters.length > terCodes.length){
                    for(int l=0; l<ters.length; l++){
                        if(l<terCodes.length){prodXml.addTerritory("true",ters[l],terDates[l],terCodes[l],"","");}
                        else{prodXml.addTerritory("true",ters[l],terDates[l],terCodes[terCodes.length-1],"","");}
                    }
                }
                if(ters.length > terDates.length && ters.length > terCodes.length){
                    for(int l=0; l<ters.length; l++){
                        if(l<terCodes.length && l<terDates.length){
                            prodXml.addTerritory("true",ters[l],terDates[l],terCodes[l],"","");
                        }
                        if(l<terCodes.length && l>=terDates.length){
                            prodXml.addTerritory("true",ters[l],terDates[l],terCodes[terCodes.length-1],"","");
                        }
                        if(l>=terCodes.length && l<terDates.length){
                            prodXml.addTerritory("true",ters[l],terDates[terDates.length-1],terCodes[l],"","");
                        }
                        if(l>=terCodes.length && l>=terDates.length){
                            prodXml.addTerritory("true",ters[l],terDates[terDates.length-1],terCodes[terCodes.length-1],"","");
                        }
                    }
                }
                if(ters.length < terDates.length | ters.length < terCodes.length){sendTerError();};
                
                
                cell =row.getCell(27);
                String rawExters = antiNullString(cell);
                exters = rawExters.split("-");
                for(int m=0; m<exters.length; m++){
                    prodXml.addTerritory("false",exters[m],"","","","");
                }
                productsInSheet.add(prodXml);
            }
            //creates product for first row of document
            
            for(int i=2; i<=sheet.getLastRowNum(); i++){
                //counts down to the bottom of excell doc
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
                current.addTrack(initTrack[0],initTrack[1],initTrack[2],initTrack[3],
                    initTrack[4],initTrack[5],initTrack[6],initTrack[7],initTrack[8],initTrack[9],
                    initTrack[10],initTrack[11],initTrack[12],initTrack[13]);
                 }
                 else{
                     for(int t=0; t<10; t++){
                        cell =row.getCell(t);
                         System.out.println(t + " " + antiNullString(cell));
                        initXml[t] = antiNullString(cell);
                       
                    }
                    Xml prodXml = new Xml("testdistributor",initXml[0],initXml[1],
                                            initXml[2],initXml[3],initXml[4],initXml[5],
                                            initXml[6],initXml[7],initXml[8],initXml[9]);
                                            
                    for(int k=10; k<24; k++){
                                cell =row.getCell(k);
                                initTrack[k-10] = antiNullString(cell);
                            }
                            prodXml.addTrack(initTrack[0],initTrack[1],initTrack[2],initTrack[3],
                                initTrack[4],initTrack[5],initTrack[6],initTrack[7],initTrack[8],initTrack[9],
                                initTrack[10],initTrack[11],initTrack[12],initTrack[13]);
                                
                                
                cell =row.getCell(24);
                String rawTers = antiNullString(cell);
                ters = rawTers.split("-");
                cell =row.getCell(25);
                String rawDates = antiNullString(cell);
                terDates = rawDates.split("-");
                cell =row.getCell(26);
                String rawCodes = antiNullString(cell);
                terCodes = rawCodes.split("-");
                
                if(ters.length == terDates.length && ters.length == terCodes.length){
                    for(int l=0; l<ters.length; l++){
                        prodXml.addTerritory("true",ters[l],terDates[l],terCodes[l],"","");
                    }
                }
                if(ters.length > terDates.length && ters.length == terCodes.length){
                    for(int l=0; l<ters.length; l++){
                        if(l<terDates.length){prodXml.addTerritory("true",ters[l],terDates[l],terCodes[l],"","");}
                        else{prodXml.addTerritory("true",ters[l],terDates[terDates.length-1],terCodes[l],"","");}
                    }
                }
                if(ters.length == terDates.length && ters.length > terCodes.length){
                    for(int l=0; l<ters.length; l++){
                        if(l<terCodes.length){prodXml.addTerritory("true",ters[l],terDates[l],terCodes[l],"","");}
                        else{prodXml.addTerritory("true",ters[l],terDates[l],terCodes[terCodes.length-1],"","");}
                    }
                }
                if(ters.length > terDates.length && ters.length > terCodes.length){
                    for(int l=0; l<ters.length; l++){
                        if(l<terCodes.length && l<terDates.length){
                            prodXml.addTerritory("true",ters[l],terDates[l],terCodes[l],"","");
                        }
                        if(l<terCodes.length && l>=terDates.length){
                            prodXml.addTerritory("true",ters[l],terDates[terDates.length-1],terCodes[l],"","");
                        }
                        if(l>=terCodes.length && l<terDates.length){
                            prodXml.addTerritory("true",ters[l],terDates[l],terCodes[terCodes.length-1],"","");
                        }
                        if(l>=terCodes.length && l>=terDates.length){
                            prodXml.addTerritory("true",ters[l],terDates[terDates.length-1],terCodes[terCodes.length-1],"","");
                        }
                    }
                }
                if(ters.length < terDates.length | ters.length < terCodes.length){sendTerError();};
                
                
                cell =row.getCell(27);
                String rawExters = antiNullString(cell);
                exters = rawExters.split("-");
                for(int m=0; m<exters.length; m++){
                    prodXml.addTerritory("false",exters[m],"","","","");
                }
                                
                                
                    productsInSheet.add(prodXml);
                    
                    
                }
            }
            for(int i=0; i<productsInSheet.size(); i++){
                Xml toprint = (Xml) productsInSheet.get(i);
                toprint.printXml();
            }
        }
        catch (IOException e){
            e.printStackTrace ();
        }
    }
        
}