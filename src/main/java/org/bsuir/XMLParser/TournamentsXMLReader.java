package org.bsuir.XMLParser;

import org.bsuir.model.Tournament;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TournamentsXMLReader extends DefaultHandler {
    private final TournamentsHandler handler;
    private final SAXParser parser;
    private final File file;

    public TournamentsXMLReader(File file) throws ParserConfigurationException, SAXException {
        this.file = file;
        SAXParserFactory factory = SAXParserFactory.newInstance();

        parser = factory.newSAXParser();

        handler = new TournamentsHandler();
    }

    public List<Tournament> readAll() throws IOException, SAXException,IllegalArgumentException {

        parser.parse(file, handler);

        return handler.getTournaments();
    }
}