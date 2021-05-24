package org.bsuir.model;

import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.util.*;

public class TournamentsTableModel extends DefaultTableModel {

    public TournamentsTableModel() {

        super(Parameters.defaultData, Parameters.TABLE_HEADER);
    }

    public void addTournament(Tournament tournament) {

        addRow(parseTournament(tournament));
        System.out.println("Added tournament");
    }

    public int deleteByFullNameOrAddress(String fullName, String sportName) {

        int counter = 0;

        for (int i = 0; i < getRowCount(); ++i) {
            if (getValueAt(i, 0).equals(sportName)
                    || getValueAt(i, 1).equals(fullName)) {

                ++counter;
                removeRow(i--);
            }
        }
        System.out.println("Removed " + counter + " tournaments");

        return counter;
    }

    /*public int deleteByBirthday(String prizeUpper, String prizeLower, String incomeUpper, String incomeLower ) {
        int counter = 0;
        Double prizeUpperBorder = Double.parseDouble(prizeUpper);
        Double prizeLowerBorder = Double.parseDouble(prizeLower);
        Double incomeUpperBorder = Double.parseDouble(incomeUpper);
        Double incomeLowerBorder = Double.parseDouble(incomeLower);

        for (int i = 0; i < getRowCount(); ++i) {

            if (getValueAt(i, 2) > prizeLowerBorder) {
                ++counter;
                removeRow(i--);
            }
        }
        System.out.println("Removed " + counter + "tournaments");

        return counter;
    }*/

    public int tournamentNameOrDate(String tournamentName, DateManager tournamentDate) {
        int counter = 0;
        String date = tournamentDate.toString();

        for (int i = 0; i < getRowCount(); ++i) {

            if (getValueAt(i, 1).equals(date)
                    || getValueAt(i, 0).equals(tournamentName)) {

                ++counter;
                removeRow(i--);
            }
        }
        System.out.println("Deleted " + counter + " tournaments");

        return counter;
    }

    private Object[] parseTournament(Tournament tournament) {

        Object[] objects = new Object[6];
        objects[0] = tournament.getTournamentName();
        objects[1] = tournament.getTournamentDate().toString();
        objects[2] = tournament.getFullName();
        objects[3] = tournament.getSportName();
        objects[4] = tournament.getPrize();
        objects[5] = tournament.getIncome();
        return objects;
    }

    public TournamentsTableModel createSubModelByFullNameAndAddress(String fullName, String address) throws ArrayIndexOutOfBoundsException {
        TournamentsTableModel model = new TournamentsTableModel();

        for (int i = 0; i < getRowCount(); ++i) {
            if (getValueAt(i, 1).equals(address)
                    || getValueAt(i, 0).equals(fullName)) {

                model.addRow(parseRowToObjects(i));
            }
        }
        return model;
    }

    public TournamentsTableModel createSubModelByBirthday(DateManager birthday) throws ArrayIndexOutOfBoundsException {
        TournamentsTableModel model = new TournamentsTableModel();

        String parsedBirthday = birthday.toString();

        for (int i = 0; i < getRowCount(); ++i) {

            if (getValueAt(i, 2).equals(parsedBirthday)) {
                model.addRow(parseRowToObjects(i));

            }
        }
        return model;
    }

    public TournamentsTableModel createSubModelByDoctorsFullNameOrDateReceipt(String doctorsFullName,
                                                                              DateManager dateOfReceipt) throws ArrayIndexOutOfBoundsException {

        TournamentsTableModel model = new TournamentsTableModel();
        String date = dateOfReceipt.toString();

        for (int i = 0; i < getRowCount(); ++i) {

            if (getValueAt(i, 3).equals(date)
                    || getValueAt(i, 4).equals(doctorsFullName)) {

                model.addRow(parseRowToObjects(i));

            }
        }
        return model;
    }

    public TournamentsTableModel createPagedSubModel(int page, int amountOfDataOnThePage)
            throws ArrayIndexOutOfBoundsException {

        TournamentsTableModel subModel = new TournamentsTableModel();

        int currentRowNumber = (page - 1) * amountOfDataOnThePage;
        int lastRowNumber = (page) * amountOfDataOnThePage;

        int totalNumberOfLines = getRowCount();

        if (lastRowNumber > totalNumberOfLines)
            lastRowNumber = totalNumberOfLines;

        for (; currentRowNumber < lastRowNumber; ++currentRowNumber) {

            subModel.addRow(parseRowToObjects(currentRowNumber));
        }

        return subModel;
    }

    private Tournament getTournament(int rowNumber) throws ParseException {
        Object[] objects = parseRowToObjects(rowNumber);
        return new Tournament((String) objects[0], (String) objects[1], (String) objects[2],
                new DateManager((String) objects[3]), (int) objects[4]);
    }

    private Object[] parseRowToObjects(int rowNumber) {
        Vector<Vector> tableData = getDataVector();

        Vector data = tableData.elementAt(rowNumber);
        return data.toArray();
    }


    public List<Tournament> getAllTournaments() throws ParseException {
        List<Tournament> allTournaments = new ArrayList<>();
        for (int i = 0; i < getRowCount(); ++i) {
            allTournaments.add(getTournament(i));
        }
        return allTournaments;
    }

    public void resetModel(List<Tournament> tournaments) {
        clearModel();
        for (Tournament tournament : tournaments) {
            addRow(parseTournament(tournament));
        }
    }

    private void clearModel(){
        for(int i=0;i<getRowCount();++i){
            removeRow(i--);
        }
    }
}
