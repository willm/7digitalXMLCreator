import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import junit.framework.TestCase;


public class TrackTests extends TestCase {
	
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
		//filePath = "/home/will/Documents/Java/7digitalXMLCreator/xml/"+ _upc + ".xml";
		_doc = DOMElements.parse(filePath);
	}
	
	public void test_adding_a_track_adds_a_track_node_to_track(){
		_xml.addTrack("", "", "", "", "", "", "", "", "", "", "", "", "", "");
		setUp();
		
		NodeList tracks = getTracksNode();
		
		assertEquals(((Element)tracks.item(0)).getElementsByTagName("track").getLength(), 1);
		
		_xml.addTrack("", "", "", "", "", "", "", "", "", "", "", "", "", "");
		setUp();
		
		tracks = getTracksNode();
		
		assertEquals(((Element)tracks.item(0)).getElementsByTagName("track").getLength(), 2);
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
	
	public NodeList getTracksNode(){
		return getElementFromProduct("tracks");
	}
	
	public NodeList getTracks(String name){
		NodeList productArtistsNode = getElementFromProduct("product_artists");
		return ((Element)productArtistsNode.item(0)).getElementsByTagName(name);
	}

}
