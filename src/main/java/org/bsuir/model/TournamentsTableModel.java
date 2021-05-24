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

    public int deleteByFullNameOrSportType(String fullName, String sportName) {

        int counter = 0;

        for (int i = 0; i < getRowCount(); ++i) {

            if (getValueAt(i, 0).equals(fullName)
                    || getValueAt(i, 3).equals(sportName)) {

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

    public TournamentsTableModel createSubModelByFullNameOrSportType(String fullName, String sportName) throws ArrayIndexOutOfBoundsException {
        TournamentsTableModel model = new TournamentsTableModel();

        for (int i = 0; i < getRowCount(); ++i) {

            if (getValueAt(i, 0).equals(fullName)
                    || getValueAt(i, 3).equals(sportName)) {

                model.addRow(parseRowToObjects(i));
            }
        }
        return model;
    }

    public TournamentsTableModel createSubModelByPrizeOrIncome(String prizeUpper, String incomeUpper, String prizeLower, String incomeLower) throws ArrayIndexOutOfBoundsException {
        TournamentsTableModel model = new TournamentsTableModel();

        double prizeUpperNumber = Double.parseDouble(prizeUpper);
        double incomeUpperNumber = Double.parseDouble(incomeUpper);
        Double prizeLowerNumber = Double.parseDouble(prizeLower);
        Double incomeLowerNumber = Double.parseDouble(incomeLower);

        for (int i = 0; i < getRowCount(); ++i) {
            Double currentPrize = (Double) getValueAt(i, 4);
            Double currentIncome = (Double) getValueAt(i, 5);

            if(currentPrize > prizeLowerNumber && currentPrize < prizeUpperNumber
                    || currentIncome > incomeLowerNumber && currentIncome<incomeUpperNumber){

                model.addRow(parseRowToObjects(i));
            }

        }
        return model;

    }

    public TournamentsTableModel createSubModelByTournamentDateOrName(String tournamentName, DateManager tournamentDate) throws ArrayIndexOutOfBoundsException {

        TournamentsTableModel model = new TournamentsTableModel();
        String date = tournamentDate.toString();

        for (int i = 0; i < getRowCount(); ++i) {

            if (getValueAt(i, 1).equals(date)
                    || getValueAt(i, 0).equals(tournamentName)) {

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

        double value =(double) objects[4];
        int intValue = (int) value;
        DateManager dateManager = new DateManager((String) objects[1]);
         return new Tournament((String) objects[0],(String) objects[3],(String) objects[2],dateManager,intValue);
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

    private void clearModel() {
        for (int i = 0; i < getRowCount(); ++i) {
            removeRow(i--);
        }
    }

    public int deleteByPrizeOrIncome(String prizeUpper, String incomeUpper, String prizeLower, String incomeLower) {
        Double prizeUpperNumber = Double.parseDouble(prizeUpper);
        Double incomeUpperNumber = Double.parseDouble(incomeUpper);
        Double prizeLowerNumber = Double.parseDouble(prizeLower);
        Double incomeLowerNumber = Double.parseDouble(incomeLower);

        int counter = 0;

        for (int i = 0; i < getRowCount(); ++i) {
            Double currentPrize = (Double) getValueAt(i, 4);
            Double currentIncome = (Double) getValueAt(i, 5);

            if(currentPrize > prizeLowerNumber && currentPrize < prizeUpperNumber
                    || currentIncome > incomeLowerNumber && currentIncome<incomeUpperNumber){

                ++counter;
                removeRow(i--);
            }

        }
        return counter;
    }
}
