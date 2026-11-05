package com.dataaccess.finalactivityAFP2627;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads the passengers of an XML file using DOM.
 */
public class XmlPassengerReader {

    public static List<Passenger> read(File file) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(file);

        List<Passenger> passengers = new ArrayList<>();
        NodeList nodes = document.getElementsByTagName("passenger");

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            Passenger passenger = new Passenger(
                    getText(element, "passportno"),
                    getText(element, "firstname"),
                    getText(element, "lastname"),
                    getText(element, "address"),
                    getText(element, "phone"));
            passengers.add(passenger);
        }
        return passengers;
    }

    /**
     * Returns the text of a child tag, or null if it does not exist or is empty.
     */
    private static String getText(Element parent, String tag) {
        NodeList list = parent.getElementsByTagName(tag);
        if (list.getLength() == 0) {
            return null;
        }
        String text = list.item(0).getTextContent().trim();
        if (text.isEmpty()) {
            return null;
        } else {
            return text;
        }
    }
}
