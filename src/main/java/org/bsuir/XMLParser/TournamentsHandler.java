package org.bsuir.XMLParser;

import org.bsuir.model.DateManager;
import org.bsuir.model.Tournament;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TournamentsHandler extends DefaultHandler {

    private List<Tournament> tournaments;
    private Tournament tournament;
    private String currentElement;
    private Boolean isTournament;

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attributes) {
        if (qName.equals("tournaments")) {
            tournaments = new ArrayList<>();
        }

        if (qName.equals("tournament")) {
            tournament = new Tournament();
            isTournament = true;
        }

        currentElement = qName;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
        if (qName.equals("tournament")) {
            tournaments.add(tournament);
            isTournament = false;
        }
        currentElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        try {
            if (currentElement.equals("tournamentName") && isTournament) {
                tournament.setTournamentName(text(ch, start, length));
            }

            if (currentElement.equals("sportName") && isTournament) {
                tournament.setSportName(text(ch, start, length));
            }

            if (currentElement.equals("fullName") && isTournament) {
                tournament.setFullName(new String(text(ch, start, length)));
            }

            if (currentElement.equals("tournamentDate") && isTournament) {
                tournament.setTournamentDate(new DateManager(text(ch, start, length)));
            }

            if (currentElement.equals("prize") && isTournament) {
                tournament.setPrize(Double.parseDouble(text(ch, start, length)));
            }

            if (currentElement.equals("income") && isTournament) {
                tournament.setIncome(Double.parseDouble(text(ch, start, length)));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String text(char[] ch, int start, int length) {
        return new String(ch, start, length);
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }
}
