import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import junit.framework.TestCase;

public class XMLTests extends TestCase {

	private static final String _isExplicit = "false";
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
	
	private Xml xml = new Xml(_distributor,
			_upc,
			_productType,
			_label,
			_image, 
			_album, 
			_releaseDate, 
			_publisherLine, 
			_copyrightLine, 
			_productArtist, 
			_isExplicit);
	
	Document doc;
	
	public void setUp(){
		xml.printXml();
		String filePath = "c:\\Stuff\\XMLCreator\\xml\\"+ _upc + ".xml";
		filePath = "/home/will/Documents/Java/7digitalXMLCreator/xml/"+ _upc + ".xml";
		doc = DOMElements.parse(filePath);
	}
	
	public void test_xml_contains_products_node()
	{		
		NodeList products = getTagsByName(doc,"products");
		
		assertTrue(products.getLength() == 1);
	}
	
	public void test_xml_contains_distributor_node(){
		NodeList distributorNode = getTagsByName(doc,"distributor");
		
		assertTrue(distributorNode.getLength() == 1);
	}
	
	public void test_distributor_contains_correct_distributor_value(){
		NodeList distributor = getTagsByName(doc,"distributor");
		Element value = (Element)distributor.item(0);
		
		assertTrue(value.getTextContent().equals(_distributor));
	}
	
	public void test_xml_contains_product_node(){
		NodeList product = getTagsByName(doc,"product");
		
		assertTrue(product.getLength() == 1);
	}
	
	public void test_productNode_contains_upc_attribute(){
		NodeList productNode = getTagsByName(doc,"product");
		Attr upcAtribute = ((Element)productNode.item(0)).getAttributeNode("upc");
		
		assertTrue(upcAtribute != null);
	}
	
	public void test_upc_attribute_contains_correct_value(){
		NodeList productNode = getTagsByName(doc,"product");
		Attr upcAtribute = ((Element)productNode.item(0)).getAttributeNode("upc");
		
		assertTrue(upcAtribute.getNodeValue().equals(_upc));
	}
	
	public void test_xml_contains_product_type_node(){
		NodeList product_type = getTagsByName(doc,"product_type");
		
		assertTrue(product_type.getLength() == 1);
	}
	
	public void test_product_type_contains_correct_value(){
		NodeList product_type = getTagsByName(doc,"product_type");
		Element value = (Element)product_type.item(0);
		
		assertEquals(value.getTextContent(),"Audio");
	}
	
	public void test_xml_contains_product_label_node(){
		NodeList product_label = getTagsByName(doc,"product_label");
		
		assertTrue(product_label.getLength() == 1);
	}
	
	public void test_product_label_contains_correct_value(){
		NodeList product_label = getTagsByName(doc,"product_label");
		Element value = (Element)product_label.item(0);
		
		assertEquals(value.getTextContent(),_label);
	}
	
	public void test_xml_contains_product_image_node(){
		NodeList product_image = getTagsByName(doc,"product_image");
		
		assertTrue(product_image.getLength() == 1);
	}
	
	public void test_product_image_contains_correct_value(){
		NodeList product_image = getTagsByName(doc,"product_image");
		Element value = (Element)product_image.item(0);
		
		assertEquals(value.getTextContent(),_image);
	}
	
	public void test_xml_contains_product_title_node(){
		NodeList product_title = getTagsByName(doc,"product_title");
		
		assertTrue(product_title.getLength() == 1);
	}
	
	public void test_product_title_contains_correct_value(){
		NodeList product_title = getTagsByName(doc,"product_title");
		Element value = (Element)product_title.item(0);
		
		assertEquals(value.getTextContent(),_album);
	}
	
	public void test_xml_contains_product_release_date_node(){
		NodeList product_release_date = getTagsByName(doc,"product_release_date");
		
		assertTrue(product_release_date.getLength() == 1);
	}
	
	public void test_product_release_date_contains_correct_value(){
		NodeList product_release_date = getTagsByName(doc,"product_release_date");
		Element value = (Element)product_release_date.item(0);
		
		assertEquals(value.getTextContent(),_releaseDate);
	}
	
	public void test_xml_contains_product_p_line_node(){
		NodeList productPublisherLine = getTagsByName(doc,"product_p_line");
		
		assertTrue(productPublisherLine.getLength() == 1);
	}
	
	public void test_product_p_line_contains_correct_value(){
		NodeList productPublisherLine = getTagsByName(doc,"product_p_line");
		Element value = (Element)productPublisherLine.item(0);
		
		assertEquals(value.getTextContent(),_publisherLine);
	}
	
	
	public NodeList getTagsByName(Document doc, String name){
	// Get a list of all elements in the document
	   NodeList list = doc.getElementsByTagName(name);
	   return list;
	   /*System.out.println("XML Elements: ");
	   for (int i=0; i<list.getLength(); i++) {
		   // Get element
		   Element element = (Element)list.item(i);
		   System.out.println(element.getNodeName() +" : " + element.getNodeType());
	   }*/
	}
}
