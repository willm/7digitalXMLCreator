import java.util.ArrayList;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Write a description of class BCollection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class TagCollection extends Tag
{
    // instance variables - replace the example below with your own
    private ArrayList<Tag> inside = new ArrayList<Tag>();

    public TagCollection(String theName)
    {
        super(theName, null);
        super.value=null;
        isCollection = true;
    }

   public void addTag(Tag theTag){
       inside.add(theTag);
    }
    
    public void replaceTag(int pos, Tag theTag){
        inside.set(pos, theTag);
    }
    
      
    public void print()
    {
        
        if(attributes.size() >0){
            opener =  "<"+name+" ";
            for(String key : attributes.keySet()){
                opener = opener + key + "=\"" + attributes.get(key)+ "\" ";
            }
            opener = opener + ">";
        }
        
        System.out.println(opener);
        
        for(int i=0; i<inside.size(); i++){
            Tag currentTag = (Tag)inside.get(i);
                currentTag.print();
            }
            
        System.out.println(closer);
        
    }
    public String printXml()
    {
        if(attributes.size()>0){
            opener =  "<"+name+" ";
            for(String key : attributes.keySet()){
                opener = opener + key + "=\"" + attributes.get(key) + "\" ";
            }
            opener = opener + ">";
        }
        
        String printThis = (opener + "\r\n");
        for(int i=0; i<inside.size(); i++){
            Tag currentTag = (Tag)inside.get(i);
            if(currentTag.isCollection == true){
                printThis = (printThis+currentTag.printXml()+"\r\n");
            }
            else{
                printThis = (printThis+currentTag.printXml()+"\r\n");
            }
        }
        printThis = printThis + closer + "\r\n";
        return printThis;
    }
    
    public Tag accessInside(int pos){
        return (Tag)inside.get(pos);
    }
    
    public void removeTag(int pos){
        inside.remove(pos);
    }
    
    public int insideSize(){
        return inside.size();
    }
    
    public void replace(String theName, String theNewVal){
        for(int i=0; i<inside.size(); i++){
            Tag currentTag = (Tag)inside.get(i);
                if (currentTag.name == theName){
                    currentTag.editValue(theNewVal);
                }
            }
        }
        
    public String returnVal(String theName){
        for(int i=0; i<inside.size(); i++){
            Tag currentTag = (Tag)inside.get(i);
                if (currentTag.name == theName){
                    return(currentTag.value);
                }
        }
        return(null);
    }
    
    
}
