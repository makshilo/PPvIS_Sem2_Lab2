package org.bsuir.controller;

import org.bsuir.model.DateManager;
import org.bsuir.model.TournamentsTableModel;
import org.bsuir.model.Tournament;
import org.bsuir.view.Alert;
import org.bsuir.view.DeleteTournamentBuilder;
import org.jdatepicker.impl.JDatePanelImpl;
import org.bsuir.exception.EmptyFieldException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Objects;

public class AddTournamentController {
    private final TournamentsTableModel tournamentsTableModel;
    private Tournament tournament;
    /**
     * @see DeleteTournamentBuilder#getTextFields()
     */
    private final JTextField[] textFields;
    /**
     * @see DeleteTournamentBuilder#getDatePanels()
     */
    private final JDatePanelImpl[] datePanels;
    private final JButton enterButton;
    private final JComboBox<String> comboBox;

    public AddTournamentController(TournamentsTableModel model, JTextField[] textFields,
                                   JDatePanelImpl[] datePanels, JButton enterButton, JComboBox<String> comboBox) {
        this.tournamentsTableModel = model;
        this.textFields = textFields;
        this.datePanels = datePanels;
        this.enterButton = enterButton;
        this.comboBox = comboBox;
        setEnterButtonAction();
    }

    private void setEnterButtonAction() {
        enterButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getInformation();
                    if (informationCorrect()) {
                        addTournamentToModel();
                        SwingUtilities.getWindowAncestor(enterButton).dispose();
                        Alert.successfulAddingAlert();
                    }
                } catch (EmptyFieldException exception) {
                    Alert.unsuccessfulAddingAlert("Award is an int value");
                }
            }
        });
    }

    private void addTournamentToModel() {
        tournamentsTableModel.addTournament(tournament);
    }


    private boolean informationCorrect() {
        for (JTextField textField : textFields) {
            if (textField.getText().equals("")) {
                Alert.unsuccessfulAddingAlert("Fill all text information");
                return false;
            }
        }
        for (JDatePanelImpl datePanel : datePanels) {
            if (datePanel.getModel().getValue() == null) {
                Alert.unsuccessfulAddingAlert("Fill date information");
                return false;
            }
        }
        return true;
    }

    private void getInformation() throws EmptyFieldException {
        String tournamentName = textFields[0].getText();
        String sportsName = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
        DateManager tournamentDate = new DateManager((Date) datePanels[0].getModel().getValue());

        String fullName = textFields[2].getText();
        try {
            Double prize = Double.valueOf(textFields[1].getText());
            tournament = new Tournament(tournamentName, sportsName, fullName, tournamentDate, prize);
        } catch (NumberFormatException exception) {
            throw new EmptyFieldException("Int should be numeric value");
        }
    }
}
