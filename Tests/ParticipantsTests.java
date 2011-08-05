import org.w3c.dom.Document;

import model.Artist;
import model.Distributor;
import model.Genre;
import model.Product;
import model.ProductSerializer;
import model.Track;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xmlSerialization.TagCollection;

import junit.framework.TestCase;
import main.Xml;

public class ParticipantsTests extends TestCase{
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
		String filePath = "c:\\Stuff\\XMLCreator\\xml\\"+ _upc + ".xml";
		filePath = "/home/will/Documents/Java/7digitalXMLCreator/xml/"+ _upc + ".xml";
		
		_xml.addParticipant("Didgery Doo", "Rolf Harris");
		_xml.printXml();
		_doc = DOMElements.parse(filePath);
	}
	
	public void test_participants_node_contains_participant(){
		NodeList participantsNodes = getElementFromProduct("paricipants");
		NodeList participentNodes = ((Element)participantsNodes.item(0)).getElementsByTagName("participant");
		
		assertTrue(participentNodes.getLength() ==  1);
	}
	
	public void test_participant_contains_participant_role_node()
	{
		NodeList participantsNodes = getElementFromProduct("paricipants");
		NodeList participentNodes = ((Element)participantsNodes.item(0)).getElementsByTagName("participant");
		NodeList firstParticipentRoleNodes =((Element)participentNodes.item(0)).getElementsByTagName("participant_role");
		
		assertTrue(firstParticipentRoleNodes.getLength() == 1);
	}
	
	public void test_participant_role_node_contains_correct_value()
	{
		NodeList participantsNodes = getElementFromProduct("paricipants");
		NodeList participentNodes = ((Element)participantsNodes.item(0)).getElementsByTagName("participant");
		NodeList firstParticipentRoleNodes =((Element)participentNodes.item(0)).getElementsByTagName("participant_role");
		
		assertEquals(firstParticipentRoleNodes.item(0).getTextContent(),"Didgery Doo");
	}
	
	public void test_participant_contains_participant_name_node()
	{
		NodeList participantsNodes = getElementFromProduct("paricipants");
		NodeList participentNodes = ((Element)participantsNodes.item(0)).getElementsByTagName("participant");
		NodeList firstParticipentRoleNodes =((Element)participentNodes.item(0)).getElementsByTagName("participant_name");
		
		assertTrue(firstParticipentRoleNodes.getLength() == 1);
	}
	
	public void test_participant_name_node_contains_correct_value()
	{
		NodeList participantsNodes = getElementFromProduct("paricipants");
		NodeList participentNodes = ((Element)participantsNodes.item(0)).getElementsByTagName("participant");
		NodeList firstParticipentNameNodes =((Element)participentNodes.item(0)).getElementsByTagName("participant_name");
		
		assertEquals(firstParticipentNameNodes.item(0).getTextContent(),"Rolf Harris");
	}
	
	public void test_a(){
		Product a = new Product("43243242", "fgrg", "zzzz", "ybfvdf", "fegfev", "8787nj", "lom", "fdgf", new Artist("bob dylan",true), true);
		a.Tracks.add(new Track("", true, "", new Artist("pink floyd", true), false, "", 1, "", "", "", "", "", "", new Genre("prog rock")));
		ProductSerializer productSerializer = new ProductSerializer(a);
		TagCollection b = productSerializer.Serialize();
		System.out.print(b.prettyFormat(b.printXml()));
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


