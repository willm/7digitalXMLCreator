import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MySAXApp {

  public static void main(String args[]) {

    if (args.length != 1) {
      System.err.println("Usage: java NameLister xmlfile.xml");
      System.exit(-1);
    }

    try {

      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();

      DefaultHandler handler = new DefaultHandler() {
        boolean name = false;

        public void startElement(String uri, String localName,
            String qName, Attributes attributes)
            throws SAXException {
          if (qName.equalsIgnoreCase("NAME")) {
            name = true;
          }
        }

        public void characters(char ch[], int start, int length)
            throws SAXException {
          if (name) {
            System.out.println("Name: "
                + new String(ch, start, length));
            name = false;
          }
        }
      };

      saxParser.parse(args[0], handler);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}