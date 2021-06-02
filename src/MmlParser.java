import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class MmlParser {

    public JFrame buildFrame(String fileName) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(fileName));
            Element root = doc.getDocumentElement();
            if (!root.getNodeName().equals("JFrame")) {
                throw new IllegalStateException("root should be a JFrame");
            }
            JFrame frame = Components.makeJFrame(root);
            Components.attachLayout(root,frame);
            NodeList children = root.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE)
                    Components.parseElement((Element) child, frame);
            }
            return frame;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
