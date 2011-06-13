import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
/**
 * Write a description of class Tag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tag
{

    protected Boolean attributePresent = false;
    protected Boolean isCollection = false;
    protected ArrayList attributes = new ArrayList();
    //protected String[] attribute = new String[2];
    //this array will contain the name of the atribute at position 0 and it's value at position 1.
    protected String opener;
    protected String name;
    protected String closer;
    protected String value;
    //protected 
    

    public Tag(String theName, String theValue)
    {
        name=theName;
        
        opener="<"+name+">";
        value=theValue;
        closer="</"+name+">";
    }

    public void addAttribute(String theAttribute, String theValue)
    {
        String[] attribute = new String[2];
        attribute[0] = theAttribute;
        attribute[1] = theValue;
        attributes.add(attribute);
        attributePresent = true;
    }
    
    public void editAttribute(String name, String newVal){
        if(attributePresent==true){
            for(int i = 0; i<attributes.size(); i++){
                String[] temp = (String[])attributes.get(i);
                if(temp[0] == name){
                    temp[1] = newVal;
                }
            }
        }
    }
    
    public String returnAtrbtVal(String theName){
        String theVal = null;
        for(int i=0; i<attributes.size(); i++){
            String[] temp = new String[2];
            temp = (String[])attributes.get(i);
            if(temp[0]==theName){
                theVal=temp[1];
            }
        }
        return theVal;
    }
                
    
    public void editValue(String newVal){
        value = newVal;
    }
    
    public String returnVal(){
        return(value);
    }
    
    public String returnName(){
        return(name);
    }
    
   public void print()
    {
        if(attributePresent == false){
            System.out.println(opener+value+closer);
        }
        
        else {
            for(int i=0; i<attributes.size(); i++){
                String[] curAtrbt = new String[2];
                curAtrbt = (String[]) attributes.get(i);
                opener = opener + curAtrbt[0] + "=\"" + curAtrbt[1]+"\"";
            }
            opener = opener + ">";
            System.out.println(opener + value + closer);
        }

    }
    
    public String printXml()
    {
        String printXml;
        if(attributePresent == false){
            printXml = new String(opener+value+closer);
        }
        
        else{
            opener =  "<"+name+" ";
            
            for(int i=0; i<attributes.size(); i++){
                String[] curAtrbt = new String[2];
                curAtrbt = (String[]) attributes.get(i);
                opener = opener + curAtrbt[0] + "=\"" + curAtrbt[1] +"\"";
            }
            opener = opener + ">";
            printXml = new String(opener+value+closer+"\r\n");
        }
        
        return printXml;
    }
}
