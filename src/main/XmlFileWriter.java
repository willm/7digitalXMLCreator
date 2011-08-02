package main;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import xmlSerialization.*;


public class XmlFileWriter {
	public void writeToXmlFile(Tag[] pInitInfo, Tag pProductArtist, TagCollection pParticipants, Tag pExplicitContent, ArrayList<Tag> pGenres, TagCollection pTerritories, TagCollection pTracks, String pFileName) throws FileNotFoundException {
		PrintStream printStream = new PrintStream(new FileOutputStream("xml/"+pFileName+".xml"));
		
		printStream.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		printStream.println("<products xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"7dig.xsd\">");
		printStream.println("<!--CREATED USING THE 7digital XML CREATOR-->");
		
		for(int i =0; i<9; i++){
		    printStream.println(pInitInfo[i].printXml());
		}
		//prints the initinfo array
		
		printStream.println("<product_artists>");
		
		    printStream.println(pProductArtist.printXml());
		    //prints product artists
		
		printStream.println("</product_artists>");
		           
		printStream.print(pParticipants.printXml());
		//participants list 
		
		printStream.println(pExplicitContent.printXml());
		//prints whether explicit content is present
		
		printStream.println("<genres>");
		//prints genres list
		
		    for(int i=0; i<pGenres.size(); i++){
		        Tag temp = pGenres.get(i);
		        printStream.println(temp.printXml());
		    }
		
		printStream.println("</genres>");
		
		printStream.print(pTerritories.printXml());
		//territory_restrictions list
		
		printStream.print(pTracks.printXml());
		//tracks list
		
		printStream.println("</product>");
		printStream.println("</products>");
		
		printStream.close();
	}
}
