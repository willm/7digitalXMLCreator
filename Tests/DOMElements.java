import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
//import org.xml.sax.*;

public class DOMElements{
   static public Document parse(String xmlFile){
	   try {
		   File file = new File(xmlFile);
		   if(file.exists()){
			   // Create a factory
			   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			   // Use the factory to create a builder
			   DocumentBuilder builder = factory.newDocumentBuilder();
			   Document doc = builder.parse(xmlFile);
			   return doc;
		   }
		   else{
			   return null;
		   }
	   }
	   
	   catch (Exception e) {
		   System.exit(1);
	   }
	return null;
   }
}