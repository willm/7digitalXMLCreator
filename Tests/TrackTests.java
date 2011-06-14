import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import junit.framework.TestCase;


public class TrackTests extends TestCase {

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
			"false");
	
	private Document _doc;
	
	public static final String _isrc = "FR354354";
	public static final String _hidden = "false";
	public static final String _trackIdentifier = "track.wav";
	public static final String _trackArtist = "Michael Jackson";
	public static final String _isExplicit = "false";
	public static final String _volume = "1";
	public static final String _trackNumber = "1";
	public static final String _trackType = "audio";
	public static final String _trackTitle = "Wanna Be Startin' Somethin'";
	public static final String _trackVTitle = "Original Version";
	public static final String _trackLength = "00:06:02";
	public static final String _trackLabel = "epic";
	public static final String _trackPLine = "published by goats";
	public static final String _trackGenre = "pop";

	
	public void setUp(){
		_xml.addTrack(_isrc, "false", _trackIdentifier, _trackArtist, 
				_isExplicit, _volume, _trackNumber, _trackType, _trackTitle, 
				_trackVTitle, _trackLength, _trackLabel, _trackPLine, _trackGenre);
		readOutputXmlFile();
	}
	
	public void readOutputXmlFile(){
		_xml.printXml();
		String filePath = "c:\\Stuff\\XMLCreator\\xml\\"+ _upc + ".xml";
		filePath = "/home/will/Documents/Java/7digitalXMLCreator/xml/"+ _upc + ".xml";
		_doc = DOMElements.parse(filePath);
	}
	
	public void test_adding_a_track_adds_a_track_node_to_track(){
		_xml.addTrack("", "", "", "", "", "", "", "", "", "", "", "", "", "");
		readOutputXmlFile();
		
		NodeList tracks = getTracksNode();
		
		assertEquals(((Element)tracks.item(0)).getElementsByTagName("track").getLength(), 2);
		
		_xml.addTrack("", "", "", "", "", "", "", "", "", "", "", "", "", "");
		readOutputXmlFile();
		
		tracks = getTracksNode();
		
		assertEquals(((Element)tracks.item(0)).getElementsByTagName("track").getLength(), 3);
	}
	
	public void test_track_contains_isrc_attribute(){
		Node track = getFirstTrack();
		Attr isrc = ((Element)track).getAttributeNode("isrc");
		
		assertTrue(isrc != null);
	}
	
	public void test_isrc_contains_correct_value(){
		Node track = getFirstTrack();
		Attr isrc = ((Element)track).getAttributeNode("isrc");
		
		assertEquals(isrc.getNodeValue(),_isrc);
	}
	
	public void test_track_contains_hidden_attribute(){
		Node track = getFirstTrack();
		Attr hidden = ((Element)track).getAttributeNode("hidden");
		
		assertTrue(hidden != null);
	}
	
	public void test_hidden_contains_correct_value(){
		Node track = getFirstTrack();
		Attr hidden = ((Element)track).getAttributeNode("hidden");
		
		assertEquals(hidden.getNodeValue(),_hidden);
	}
	
	public void test_track_contains_track_identifier_node(){
		Node track = getFirstTrack();
		NodeList trackIdentifierNode = ((Element)track).getElementsByTagName("track_identifier");
		
		assertTrue(trackIdentifierNode.getLength() == 1);
	}
	
	public void test_track_identifier_node_contains_correct_value(){
		Node track = getFirstTrack();
		NodeList trackIdentifierNode = ((Element)track).getElementsByTagName("track_identifier");
		
		assertEquals(trackIdentifierNode.item(0).getTextContent(),_trackIdentifier);
	}
	
	
	
	


	public void test_track_contains_explicit_content_node(){
		Node track = getFirstTrack();
		NodeList trackExplicitNode = ((Element)track).getElementsByTagName("explicit_content");
		
		assertTrue(trackExplicitNode.getLength() == 1);
	}
	
	public void test_track_contains_explicit_content_node_contains_correct_value(){
		Node track = getFirstTrack();
		NodeList trackExplicitNode = ((Element)track).getElementsByTagName("explicit_content");
		
		assertEquals(trackExplicitNode.item(0).getTextContent(),_isExplicit);
	}
	
	public void test_track_contains_track_volume_node(){
		Node track = getFirstTrack();
		NodeList trackVolumeNode = ((Element)track).getElementsByTagName("track_volume");
		
		assertTrue(trackVolumeNode.getLength() == 1);
	}
	
	public void test_trackvolume_node_contains_correct_value(){
		Node track = getFirstTrack();
		NodeList trackVolumeNode = ((Element)track).getElementsByTagName("track_volume");
		
		assertEquals(trackVolumeNode.item(0).getTextContent(),_volume);
	}
	
	public void test_track_contains_track_number_node(){
		Node track = getFirstTrack();
		NodeList trackNumberNode = ((Element)track).getElementsByTagName("track_number");
		
		assertTrue(trackNumberNode.getLength() == 1);
	}
	
	public void test_track_number_node_contains_correct_value(){
		Node track = getFirstTrack();
		NodeList trackNumberNode = ((Element)track).getElementsByTagName("track_number");
		
		assertEquals(trackNumberNode.item(0).getTextContent(),_trackNumber);
	}
	
	public void test_track_contains_track_type_node(){
		Node track = getFirstTrack();
		NodeList trackTypeNode = ((Element)track).getElementsByTagName("track_type");
		
		assertTrue(trackTypeNode.getLength() == 1);
	}
	
	public void test_track_type_node_contains_correct_value(){
		Node track = getFirstTrack();
		NodeList trackTypeNode = ((Element)track).getElementsByTagName("track_type");
		
		assertEquals(trackTypeNode.item(0).getTextContent(),_trackType);
	}
	
	public void test_track_contains_track_title_node(){
		Node track = getFirstTrack();
		NodeList trackTitleNode = ((Element)track).getElementsByTagName("track_type");
		
		assertTrue(trackTitleNode.getLength() == 1);
	}
	
	public void test_track_title_node_contains_correct_value(){
		Node track = getFirstTrack();
		NodeList trackTitleNode = ((Element)track).getElementsByTagName("track_type");
		
		assertEquals(trackTitleNode.item(0).getTextContent(),_trackType);
	}
	
	public void test_track_contains_track_title_v_node(){
		Node track = getFirstTrack();
		NodeList trackVTitleNode = ((Element)track).getElementsByTagName("track_version_title");
		
		assertTrue(trackVTitleNode.getLength() == 1);
	}
	
	public void test_track_v_title_node_contains_correct_value(){
		Node track = getFirstTrack();
		NodeList trackVTitleNode = ((Element)track).getElementsByTagName("track_version_title");
		
		assertEquals(trackVTitleNode.item(0).getTextContent(),_trackVTitle);
	}
	
	public void test_track_contains_track_length_node(){
		Node track = getFirstTrack();
		NodeList trackLengthNode = ((Element)track).getElementsByTagName("track_length");
		
		assertTrue(trackLengthNode.getLength() == 1);
	}
	
	public void test_track_length_node_contains_correct_value(){
		Node track = getFirstTrack();
		NodeList trackLengthNode = ((Element)track).getElementsByTagName("track_length");
		
		assertEquals(trackLengthNode.item(0).getTextContent(),_trackLength);
	}
	
	public void test_track_contains_track_label_node(){
		Node track = getFirstTrack();
		NodeList trackLabelNode = ((Element)track).getElementsByTagName("track_label");
		
		assertTrue(trackLabelNode.getLength() == 1);
	}
	
	public void test_track_label_node_contains_correct_value(){
		Node track = getFirstTrack();
		NodeList trackLabelNode = ((Element)track).getElementsByTagName("track_label");
		
		assertEquals(trackLabelNode.item(0).getTextContent(), _trackLabel);
	}
	
	public void test_track_contains_track_p_line_node(){
		Node track = getFirstTrack();
		NodeList trackPLineNode = ((Element)track).getElementsByTagName("track_p_line");
		
		assertTrue(trackPLineNode.getLength() == 1);
	}
	
	public void test_track_p_lime_node_contains_correct_value(){
		Node track = getFirstTrack();
		NodeList trackPLineNode = ((Element)track).getElementsByTagName("track_p_line");
			
		assertEquals(trackPLineNode.item(0).getTextContent(), _trackPLine);
	}
	
	//-------------------------------------------------------------------------
	
	public Node getFirstTrack() {
		
		NodeList tracks = getTracksNode();
		return ((Element)tracks.item(0)).getElementsByTagName("track").item(0);
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
	
	public NodeList getTracksNode(){
		return getElementFromProduct("tracks");
	}
	
	public NodeList getTracks(String name){
		NodeList productArtistsNode = getElementFromProduct("product_artists");
		return ((Element)productArtistsNode.item(0)).getElementsByTagName(name);
	}

}
