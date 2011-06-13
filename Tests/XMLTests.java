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
	
	public void testXmlIsOutputAsExpected()
	{
		xml.printXml();
		XMLReader.read("c:\\Stuff\\XMLCreator\\xml\\"+ _upc + ".xml");
	}
}
