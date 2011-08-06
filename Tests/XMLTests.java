import org.w3c.dom.Attr;
import main.Xml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import junit.framework.TestCase;

public class XMLTests extends TestCase {

	private static final Boolean _isExplicit = false;
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
			_isExplicit);
	
	private Document _doc;
	
	public void setUp(){
		_xml.printXml();
		String filePath = "c:\\Stuff\\XMLCreator\\xml\\"+ _upc + ".xml";
		filePath = "/home/will/Documents/Java/7digitalXMLCreator/xml/"+ _upc + ".xml";
		_doc = DOMElements.parse(filePath);
	}
	
	public void test_xml_contains_products_node()
	{		
		NodeList products = getTagsByName(_doc,"products");
		
		assertTrue(products.getLength() == 1);
	}
	
	public void test_products_contains_product_node()
	{		
		NodeList product = getProductElement();
		
		assertTrue(product.getLength() == 1);
	}
	
	public void test_products_contain_distributor(){
		NodeList products = getTagsByName(_doc, "products");
		NodeList distributor = ((Element)products.item(0)).getElementsByTagName("distributor");
		
		assertTrue(distributor.getLength() == 1);
	}
	
	public void test_distributor_contains_correct_distributor_value(){
		NodeList distributor = getTagsByName(_doc,"distributor");
		Element value = (Element)distributor.item(0);
		
		assertTrue(value.getTextContent().equals(_distributor));
	}
	
	public void productNode_contains_upc_attribute(){
		NodeList productNode = getProductElement();
		Attr upcAtribute = ((Element)productNode.item(0)).getAttributeNode("upc");
		
		assertTrue(upcAtribute != null);
	}
	
	public void test_upc_attribute_contains_correct_value(){
		NodeList productNode = getProductElement();
		Attr upcAtribute = ((Element)productNode.item(0)).getAttributeNode("upc");
		
		assertTrue(upcAtribute.getNodeValue().equals(_upc));
	}
	
	public void test_xml_contains_product_type_node(){
		NodeList product_type = getElementFromProduct("product_type");
		
		assertTrue(product_type.getLength() == 1);
	}
	
	public void test_product_type_contains_correct_value(){
		NodeList product_type = getElementFromProduct("product_type");
		Element value = (Element)product_type.item(0);
		
		assertEquals(value.getTextContent(),"Audio");
	}
	
	public void test_xml_contains_product_label_node(){
		NodeList product_label = getElementFromProduct("product_label");
		
		assertTrue(product_label.getLength() == 1);
	}
	
	public void test_product_label_contains_correct_value(){
		NodeList product_label = getElementFromProduct("product_label");
		Element value = (Element)product_label.item(0);
		
		assertEquals(value.getTextContent(),_label);
	}
	
	public void test_xml_contains_product_image_node(){
		NodeList product_image = getElementFromProduct("product_image");
		
		assertTrue(product_image.getLength() == 1);
	}
	
	public void test_product_image_contains_correct_value(){
		NodeList product_image = getElementFromProduct("product_image");
		Element value = (Element)product_image.item(0);
		
		assertEquals(value.getTextContent(),_image);
	}
	
	public void test_xml_contains_product_title_node(){
		NodeList product_title = getElementFromProduct("product_title");
		
		assertTrue(product_title.getLength() == 1);
	}
	
	public void test_product_title_contains_correct_value(){
		NodeList product_title = getElementFromProduct("product_title");
		Element value = (Element)product_title.item(0);
		
		assertEquals(value.getTextContent(),_album);
	}
	
	public void test_xml_contains_product_release_date_node(){
		NodeList product_release_date = getElementFromProduct("product_release_date");
		
		assertTrue(product_release_date.getLength() == 1);
	}
	
	public void test_product_release_date_contains_correct_value(){
		NodeList product_release_date = getElementFromProduct("product_release_date");
		Element value = (Element)product_release_date.item(0);
		
		assertEquals(value.getTextContent(),_releaseDate);
	}
	
	public void test_xml_contains_product_p_line_node(){
		NodeList productPublisherLine = getElementFromProduct("product_p_line");
		
		assertTrue(productPublisherLine.getLength() == 1);
	}
	
	public void test_product_p_line_contains_correct_value(){
		NodeList productPublisherLine = getElementFromProduct("product_p_line");
		Element value = (Element)productPublisherLine.item(0);
		
		assertEquals(value.getTextContent(),_publisherLine);
	}
	
	public void test_xml_contains_product_c_line_node(){
		NodeList productCopyrightLine = getElementFromProduct("product_c_line");
		
		assertTrue(productCopyrightLine.getLength() == 1);
	}
	
	public void test_product_c_line_contains_correct_value(){
		NodeList productCopyrightLine = getElementFromProduct("product_c_line");
		Element value = (Element)productCopyrightLine.item(0);
		
		assertEquals(value.getTextContent(),_copyrightLine);
	}
	
	public void test_product_contains_product_artists_node(){
		NodeList productArtistsNode = getElementFromProduct("product_artists");
		
		assertTrue(productArtistsNode.getLength() == 1);
	}
	
	public void test_product_artists_contains_product_artist_name_node(){
		NodeList productArtistNameNode = getElementFromArtistsNode("product_artist_name");
		
		assertTrue(productArtistNameNode.getLength() == 1);
	}
	
	public void test_product_artist_name_contains_main_attribute(){
		NodeList productArtistNameNode = getElementFromArtistsNode("product_artist_name");
		Attr mainAttribute = ((Element)productArtistNameNode.item(0)).getAttributeNode("main");
		
		assertTrue(mainAttribute != null);
	}
	
	public void test_main_attribute_is_set_to_yes(){
		NodeList productArtistNameNode = getElementFromArtistsNode("product_artist_name");
		Attr mainAttribute = ((Element)productArtistNameNode.item(0)).getAttributeNode("main");
		
		assertEquals(mainAttribute.getNodeValue(), "yes");
	}
	
	public void test_product_artist_name_value_is_correct(){
		NodeList productArtistNameNode = getElementFromArtistsNode("product_artist_name");
		
		assertEquals(productArtistNameNode.item(0).getTextContent(), _productArtist);
	}

	public void test_product_contains_participants_node(){
		NodeList participants = getElementFromProduct("participants");
		
		assertTrue(participants.getLength() == 1);
	}
	
	public void test_product_contains_explicit_node(){
		NodeList explicit_content = getElementFromProduct("explicit_content");
		
		assertTrue(explicit_content.getLength() == 1);
	}
	
	public void test_explicit_node_contains_correct_value(){
		NodeList explicit_content = getElementFromProduct("explicit_content");
		
		assertEquals(explicit_content.item(0).getTextContent(), _isExplicit.toString());
	}
	
	public void test_product_contains_genres_node(){
		NodeList genresNodes = getElementFromProduct("genres");
		
		assertTrue(genresNodes.getLength() == 1);
	}
	
	public void test_product_contains_territory_restrictions_node(){
		NodeList territoryRestrictionsNodes = getElementFromProduct("territory_restrictions");
		
		assertTrue(territoryRestrictionsNodes.getLength() == 1);
	}
	
	public void test_product_contains_tracks_node(){
		NodeList tracksNodes = getElementFromProduct("tracks");
		
		assertTrue(tracksNodes.getLength() == 1);
	}
	
	//-------------------------------------------------------------------------
	
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
	
	public NodeList getElementFromArtistsNode(String name){
		NodeList productArtistsNode = getElementFromProduct("product_artists");
		return ((Element)productArtistsNode.item(0)).getElementsByTagName(name);
	}
}
