package xmlSerialization;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Hashtable;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Tag
{
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
        System.out.print(printXml());
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
            printXml = new String(opener+value+closer);
        }
        
        return printXml;
    }
    
    public static String prettyFormat(String input, int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer(); 
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            throw new RuntimeException(e); // simple exception handling, please review it
        }
    }

    public static String prettyFormat(String input) {
        return prettyFormat(input, 2);
    }

}
