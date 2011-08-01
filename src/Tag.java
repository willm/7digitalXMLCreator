import java.util.Hashtable;

public class Tag
{
    protected Boolean isCollection = false;
    protected Hashtable<String, String> attributes = new Hashtable<String, String>();
    protected String opener;
    protected String name;
    protected String closer;
    protected String value;
    

    public Tag(String theName, String theValue)
    {
        name=theName;
        
        opener="<"+name+">";
        value=theValue;
        closer="</"+name+">";
    }

    public void addAttribute(String theAttribute, String theValue)
    {
        
        attributes.put(theAttribute, theValue);
    }
    
    public void editAttribute(String name, String newVal){
        if(attributes.size()>0){
        	attributes.put(name, newVal);
        }
    }
    
    public String returnAtrbtVal(String theName){
        String theVal = null;
        theVal = attributes.get(theName);
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
        if(attributes.size() == 0){
            System.out.println(opener+value+closer);
        }
        
        else {
            for(String key: attributes.keySet()){
                opener = opener + key + "=\"" + attributes.get(key)+"\"";
            }
            opener = opener + ">";
            System.out.println(opener + value + closer);
        }

    }
    
    public String printXml()
    {
        String printXml;
        if(attributes.size() == 0){
            printXml = new String(opener+value+closer);
        }
        
        else{
            opener =  "<"+name+" ";
            
            for(String key: attributes.keySet()){
                opener = opener + key + "=\"" + attributes.get(key) +"\"";
            }
            opener = opener + ">";
            printXml = new String(opener+value+closer+"\r\n");
        }
        
        return printXml;
    }
}
