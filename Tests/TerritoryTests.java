import org.w3c.dom.Attr;
import main.Xml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import junit.framework.TestCase;

public class TerritoryTests extends TestCase {
	
	private static final String _productArtist = "Michael Jackson";
	private static final String _copyrightLine = "copyright starRecords";
	private static final String _publisherLine = "Published By star publishing";
	private static final String _releaseDate = "2011-01-01";
	private static final String _album = "Thriller";
	private static final String _image = "dgf.jpg";
	private static final String _label = "Star Records";
	private static final String _productType = "Audio";
	private static final String _upc = "3654654654";
	private static final String _distributor = "Believe";
	
	private Xml _xml = new Xml(_distributor,
			_upc,
			_productType,
			_label,
			_image, 
			_album, 
			_releaseDate, 
			_publisherLine, 
			_copyrightLine, 
			_productArtist, 
			false);
	
	private static final Boolean _restrictedTo = true;
	private static final String _territoryCode = "FR";
	private static final String _territoryReleaseDate = "2011-01-20";
	private static final String _priceCode = "PRICE CODE";
	private static final String _price = "6.50";
	private static final String _currencyCode = "EUR";
	
	private Document _doc;
	
	//String filePath = "c:\\Stuff\\XMLCreator\\xml\\"+ _upc + ".xml";
	String filePath = "/home/will/Documents/Java/7digitalXMLCreator/xml/"+ _upc + ".xml";
	
	public void setUp(){
		
		//String filePath = "c:\\Stuff\\XMLCreator\\xml\\"+ _upc + ".xml";
		//filePath = "/home/will/Documents/Java/7digitalXMLCreator/xml/"+ _upc + ".xml";
		_xml.addTerritory(_restrictedTo, _territoryCode, _territoryReleaseDate, _priceCode, _price, _currencyCode);
		_xml.printXml();
		_doc = DOMElements.parse(filePath);
	}
	
	public void test_territory_restrictions_node_contains_territory_node(){
		NodeList territoryRestrictions = getElementFromProduct("territory_restrictions");
		NodeList territories = ((Element)territoryRestrictions.item(0)).getElementsByTagName("territory");
		
		assertTrue(territories.getLength() ==  1);
	}
	
	public void test_territory_node_contains_restricted_to_attribute(){
		NodeList territoryRestrictions = getElementFromProduct("territory_restrictions");
		NodeList territories = ((Element)territoryRestrictions.item(0)).getElementsByTagName("territory");
		
		Attr restrictedTo = ((Element)territories.item(0)).getAttributeNode("restricted_to");
		
		assertTrue(restrictedTo !=  null);
	}
	
	public void test_restricted_to_attribute_contains_correct_value(){
		NodeList territoryRestrictions = getElementFromProduct("territory_restrictions");
		NodeList territories = ((Element)territoryRestrictions.item(0)).getElementsByTagName("territory");
		
		Attr restrictedTo = ((Element)territories.item(0)).getAttributeNode("restricted_to");
		
		assertEquals(restrictedTo.getValue(), _restrictedTo.toString());
	}

	public void test_territory_node_contains_territory_code_node(){
		Element territory = getFirstTerritory();
		NodeList code = territory.getElementsByTagName("territory_code");
		
		assertTrue(code.getLength() ==  1);
	}
	
	public void test_territory_code_node_contains_correct_value(){
		Element territory = getFirstTerritory();
		NodeList code = territory.getElementsByTagName("territory_code");
		
		assertEquals(code.item(0).getTextContent(),_territoryCode);
	}
	
	public void test_territory_node_contains_territory_release_date_node(){
		Element territory = getFirstTerritory();
		NodeList releaseDate = territory.getElementsByTagName("territory_release_date");
		
		assertTrue(releaseDate.getLength() ==  1);
	}
	
	public void test_territory_release_date_node_contains_correct_value(){
		Element territory = getFirstTerritory();
		NodeList releaseDate = territory.getElementsByTagName("territory_release_date");
		
		assertEquals(releaseDate.item(0).getTextContent(),_territoryReleaseDate);
	}
	
	public void test_territory_node_contains_price_code_node(){
		Element territory = getFirstTerritory();
		NodeList priceCode = territory.getElementsByTagName("price_code");
		
		assertTrue(priceCode.getLength() ==  1);
	}
	
	public void test_price_code_node_contains_correct_value(){
		Element territory = getFirstTerritory();
		NodeList priceCode = territory.getElementsByTagName("price_code");
		
		assertEquals(priceCode.item(0).getTextContent(),_priceCode);
	}

	public void test_territory_node_contains_wholesale_price_node(){
		Element territory = getFirstTerritory();
		NodeList wholeSalePrice = territory.getElementsByTagName("wholesale_price");
		
		assertTrue(wholeSalePrice.getLength() ==  1);
	}
	
	public void test_wholesale_price_node_contains_price_node(){
		Element territory = getFirstTerritory();
		NodeList wholeSalePrice = territory.getElementsByTagName("wholesale_price");
		NodeList price  =((Element)wholeSalePrice.item(0)).getElementsByTagName("price");
		
		assertTrue(price.getLength() ==  1);
	}
	
	public void test_price_node_contains_correctValue(){
		Element territory = getFirstTerritory();
		NodeList wholeSalePrice = territory.getElementsByTagName("wholesale_price");
		NodeList price  =((Element)wholeSalePrice.item(0)).getElementsByTagName("price");
		
		assertEquals(price.item(0).getTextContent(), _price);
	}
	
	public void test_wholesale_price_node_contains_currency_code_node(){
		Element territory = getFirstTerritory();
		NodeList wholeSalePrice = territory.getElementsByTagName("wholesale_price");
		NodeList currencyCode  =((Element)wholeSalePrice.item(0)).getElementsByTagName("currency_code");
		
		assertTrue(currencyCode.getLength() ==  1);
	}
	
	public void test_currency_code_node_contains_correctValue(){
		Element territory = getFirstTerritory();
		NodeList wholeSalePrice = territory.getElementsByTagName("wholesale_price");
		NodeList currencyCode  =((Element)wholeSalePrice.item(0)).getElementsByTagName("currency_code");
		
		assertEquals(currencyCode.item(0).getTextContent(), _currencyCode);
	}
	
	public void test_add_territory_adds_territory(){
		_xml.addTerritory(_restrictedTo, _territoryCode, _territoryReleaseDate, _priceCode, _price, _currencyCode);
		_xml.printXml();
		_doc = DOMElements.parse(filePath);
		NodeList territoryRestrictions = getElementFromProduct("territory_restrictions");
		NodeList territories = ((Element)territoryRestrictions.item(0)).getElementsByTagName("territory");
		
		assertTrue(territories.getLength() ==  2);
	}
	
//-------------------------------------------------------------------------

	public Element getFirstTerritory() {
		NodeList territoryRestrictions = getElementFromProduct("territory_restrictions");
		NodeList territories = ((Element)territoryRestrictions.item(0)).getElementsByTagName("territory");
		
		return ((Element)territories.item(0));
	}
	
public NodeList getProductElement() {
	NodeList products =getTagsByName(_doc, "products");
	return ((Element)products.item(0)).getElementsByTagName("product");
}

public NodeList getTagsByName(Document doc, String name){
// Get a list of all elements in the document
   NodeList list = doc.getElementsByTagName(name);
   return list;
}

public NodeList getElementFromProduct(String name) {
	return ((Element)getProductElement().item(0)).getElementsByTagName(name);
}

}
