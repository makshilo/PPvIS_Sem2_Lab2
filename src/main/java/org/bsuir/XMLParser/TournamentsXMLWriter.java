package org.bsuir.XMLParser;

import org.bsuir.model.Tournament;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TournamentsXMLWriter {
    private final File file;
    private static final String DEFAULT_STRUCTURE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
            "<tournaments>\n</tournaments>";

    public TournamentsXMLWriter(File file) throws IOException {

        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(DEFAULT_STRUCTURE);
        fileWriter.close();
        this.file = file;

    }

    public void writeAll(List<Tournament> tournaments) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        document.getDocumentElement().normalize();
        Node patientTag = document.getElementsByTagName("tournaments").item(0);
        removeAll(document, Node.ELEMENT_NODE, "tournament");


        for (Tournament tournament : tournaments) {
            Element tournamentElement = document.createElement("tournament");
            patientTag.appendChild(tournamentElement);

            Element tournamentName = document.createElement("tournamentName");
            tournamentName.appendChild(document.createTextNode(tournament.getTournamentName()));
            tournamentElement.appendChild(tournamentName);

            Element sportName = document.createElement("sportName");
            sportName.appendChild(document.createTextNode(tournament.getSportName()));
            tournamentElement.appendChild(sportName);

            Element fullName = document.createElement("fullName");
            fullName.appendChild(document.createTextNode(tournament.getFullName()));
            tournamentElement.appendChild(fullName);

            Element tournamentDate = document.createElement("tournamentDate");
            tournamentDate.appendChild(document.createTextNode(tournament.getTournamentDate().toString()));
            tournamentElement.appendChild(tournamentDate);


            Element prize = document.createElement("prize");
            prize.appendChild(document.createTextNode(String.valueOf(tournament.getPrize())));
            tournamentElement.appendChild(prize);

            Element income = document.createElement("income");
            income.appendChild(document.createTextNode(String.valueOf(tournament.getIncome())));
            tournamentElement.appendChild(income);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(file);
        transformer.transform(domSource, streamResult);
    }


    private void removeAll(Node node, short nodeType, String name) {
        if (node.getNodeType() == nodeType && (name == null || node.getNodeName().equals(name))) {
            node.getParentNode().removeChild(node);
        } else {
            NodeList list = node.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                removeAll(list.item(i), nodeType, name);
            }
        }
    }


}
