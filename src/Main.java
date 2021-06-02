import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        JFrame frame = parseMML("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public static JFrame parseMML(String fileName) throws ParserConfigurationException, SAXException, IOException {
        MmlParser parser = new MmlParser();
        JFrame frame = new JFrame();
        frame = parser.buildFrame(fileName);
        return frame;
    }
}
