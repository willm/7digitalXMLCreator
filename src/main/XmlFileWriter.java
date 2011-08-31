package main;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import model.Distributor;
import model.Product;
import model.ProductSerializer;
import xmlSerialization.*;


public class XmlFileWriter {
	
	public void writeToXmlFile(Product product, Distributor distributor, String fileName) throws FileNotFoundException {
		PrintStream printStream = new PrintStream(new FileOutputStream("xml/"+fileName+".xml"));
		TagCollection products = new TagCollection("products");
		products.addTag(distributor.Serialize());
		ProductSerializer productSerializer = new ProductSerializer(product);
		products.addTag(productSerializer.Serialize());
		printStream.print(TagCollection.prettyFormat(products.printXml()));
		System.out.print(TagCollection.prettyFormat(products.printXml()));
		printStream.close();
	}
}
