package org.bsuir.controller;

import org.bsuir.exception.EmptyFieldException;
import org.bsuir.model.DateManager;
import org.bsuir.model.TournamentsTableModel;
import org.bsuir.model.Parameters;
import org.bsuir.view.Alert;
import org.bsuir.view.DeleteTournamentBuilder;
import org.jdatepicker.impl.JDatePanelImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.Objects;

public class DeleteTournamentController {

    private final TournamentsTableModel tournamentsTableModel;
    private final JButton deleteButton;
    /**
     * @see DeleteTournamentBuilder#getTextFields()
     */
    private final JTextField[] textFields;
    /**
     * @see DeleteTournamentBuilder#getLabelItems()
     */
    private final JLabel[] labelItems;
    /**
     * @see DeleteTournamentBuilder#getDatePanels()
     */
    private final JComboBox<String>[] comboBoxes;
    private final JDatePanelImpl[] datePanels;
    private final JComboBox<String> deleteByTypeComboBox;
    private final JPanel cards;

    public DeleteTournamentController(TournamentsTableModel model, JButton deleteButton
            , JTextField[] textFields, JLabel[] labelItems
            , JDatePanelImpl[] datePanels, JComboBox<String>[] comboBoxes, JComboBox<String> deleteByTypeComboBox, JPanel cards) {

        this.tournamentsTableModel = model;
        this.deleteButton = deleteButton;
        this.textFields = textFields;
        this.labelItems = labelItems;
        this.datePanels = datePanels;
        this.comboBoxes = comboBoxes;
        this.deleteByTypeComboBox = deleteByTypeComboBox;
        this.cards = cards;

        setComboBoxController();
        setRemoveButtonAction();
    }

    private void setComboBoxController() {
        deleteByTypeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                CardLayout layout = (CardLayout) (cards.getLayout());
                layout.show(cards, (String) e.getItem());
            }
        });
    }

    private void setRemoveButtonAction() {
        deleteButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int amountOfDeletedTournaments = 0;

                    String deleteType = (String) deleteByTypeComboBox.getSelectedItem();

                    if (deleteType == null) {
                        throw new EmptyFieldException("Unknown type");
                    }

                    if (deleteType.equals(Parameters.SEARCH_TYPES[0])) {
                        String sportName = getSportType();
                        String fullName = getFullName();

                        amountOfDeletedTournaments = tournamentsTableModel.deleteByFullNameOrSportType(fullName, sportName);
                    } else if (deleteType.equals(Parameters.SEARCH_TYPES[1])) {
                        String prizeUpper = getPrizeUpper();
                        if(prizeUpper.isEmpty()){
                            prizeUpper = "0";
                        }
                        String incomeUpper = getIncomeUpper();
                        if(incomeUpper.isEmpty()){
                            incomeUpper = "0";
                        }
                        String prizeLower = getPrizeLower();
                        if(prizeLower.isEmpty()) {
                            prizeLower = "0";
                        }
                        String incomeLower = getIncomeLower();
                        if(incomeLower.isEmpty()){
                            incomeLower = "0";
                        }
                        amountOfDeletedTournaments = tournamentsTableModel.deleteByPrizeOrIncome(prizeUpper, incomeUpper, prizeLower, incomeLower);
                    } else if (deleteType.equals(Parameters.SEARCH_TYPES[2])) {
                        String tournamentName = getTournamentName();
                        DateManager dateOfTournament = new DateManager(getDateOfTournament());

                        amountOfDeletedTournaments = tournamentsTableModel.tournamentNameOrDate(tournamentName, dateOfTournament);
                    }

                    SwingUtilities.getWindowAncestor(deleteButton).dispose();
                    Alert.deletionAlert(amountOfDeletedTournaments);
                } catch (EmptyFieldException exception) {
                    Alert.unknownTypeAlert();
                }
            }
        });
    }

    private Date getDateOfTournament() { return (Date) datePanels[0].getModel().getValue(); }

    private String getTournamentName() { return textFields[0].getText(); }

    private String getPrizeUpper() { return textFields[1].getText(); }

    private String getPrizeLower() { return textFields[3].getText(); }

    private String getIncomeUpper() { return textFields[2].getText(); }

    private String getIncomeLower() { return textFields[4].getText(); }

    private String getFullName() { return textFields[5].getText(); }

    private String getSportType() { return Objects.requireNonNull(comboBoxes[0].getSelectedItem()).toString(); }
}
