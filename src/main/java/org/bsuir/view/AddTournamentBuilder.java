package org.bsuir.view;

import org.bsuir.model.DateLabelFormatter;
import org.bsuir.model.Parameters;
import org.jdatepicker.impl.*;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;


public class AddTournamentBuilder {
    private final JDialog dialog;

    private final static int AMOUNT_OF_LABELS = 5;
    private final static int AMOUNT_OF_TEXT_FIELDS = 4;
    private final static int AMOUNT_OF_DATE_PANELS = 1;

    private final JLabel[] labelItems;
    private final JTextField[] textFields;
    private final JDatePanelImpl[] datePanels;
    private final JDatePickerImpl[] datePickers;
    private final JButton enterButton;
    private final JComboBox<String> comboBox;


    public AddTournamentBuilder() {
        labelItems = new JLabel[AMOUNT_OF_LABELS];
        textFields = new JTextField[AMOUNT_OF_TEXT_FIELDS];
        datePanels = new JDatePanelImpl[AMOUNT_OF_DATE_PANELS];
        datePickers = new JDatePickerImpl[AMOUNT_OF_DATE_PANELS];
        enterButton = new JButton("Enter");
        comboBox = new JComboBox<>(Parameters.SPORT_TYPES);

        addLabels();
        addTextFields();
        addDateComponents();

        dialog = new JDialog();
        setDialog();
    }

    private void setDialog() {
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocation(550,250);
        dialog.setPreferredSize(new Dimension(450, 300));

        GroupLayout layout = new GroupLayout(dialog.getContentPane());
        dialog.getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        setHorizontalLayout(layout);
        setVerticalLayout(layout);

        dialog.pack();
        dialog.setVisible(true);
    }

    private void addDateComponents() {
        UtilDateModel tournamentDate = new UtilDateModel();
        JDatePanelImpl tournamentDatePanel = new JDatePanelImpl(tournamentDate, new Properties());
        JDatePickerImpl tournamentDatePicker = new JDatePickerImpl(tournamentDatePanel, new DateLabelFormatter());

        datePanels[0] = tournamentDatePanel;

        datePickers[0] = tournamentDatePicker;
    }

    private void setVerticalLayout(GroupLayout layout) {
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelItems[0])
                        .addComponent(labelItems[4]))
                .addGroup(layout.createParallelGroup()
                        .addComponent(textFields[0])

                        .addComponent(datePickers[0]))
                .addGroup(layout.createParallelGroup()

                        .addComponent(labelItems[1]))
                .addComponent(comboBox)
                .addComponent(labelItems[2])
                .addComponent(textFields[2])
                .addComponent(labelItems[3])
                .addComponent(textFields[1])
                .addComponent(enterButton)
        );
    }

    private void setHorizontalLayout(GroupLayout layout) {
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelItems[0])
                        .addComponent(textFields[0])
                        .addComponent(labelItems[1])
                        .addComponent(comboBox)
                        .addComponent(labelItems[2])
                        .addComponent(textFields[2])
                        .addComponent(labelItems[3])
                        .addComponent(textFields[1])
                        .addComponent(enterButton))
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelItems[4])
                        .addComponent(datePickers[0])
                )
        );
    }

    private void addTextFields() {
        JTextField tournamentNameTextField = new JTextField();
        JTextField fullNameTextField = new JTextField();
        JTextField awardTextField = new JTextField();

        textFields[0] = tournamentNameTextField;
        textFields[1] = fullNameTextField;
        textFields[2] = awardTextField;
    }

    private void addLabels() {
        JLabel tournamentNameLabel = new JLabel("Tournament name");
        JLabel sportTypeLabel = new JLabel("Sports type");
        JLabel fullNameLabel = new JLabel("Full name");
        JLabel awardLabel = new JLabel("Award");
        JLabel dateOfTournamentLabel = new JLabel("Date of tournament");

        labelItems[0] = tournamentNameLabel;
        labelItems[1] = sportTypeLabel;
        labelItems[2] = fullNameLabel;
        labelItems[3] = awardLabel;
        labelItems[4] = dateOfTournamentLabel;
    }

    public JDatePanelImpl[] getDatePanels() {
        return datePanels;
    }

    public JDatePickerImpl[] getDatePickers() {
        return datePickers;
    }

    public JButton getEnterButton() {
        return enterButton;
    }

    public JLabel[] getLabelItems() {
        return labelItems;
    }

    public JTextField[] getTextFields() {
        return textFields;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
