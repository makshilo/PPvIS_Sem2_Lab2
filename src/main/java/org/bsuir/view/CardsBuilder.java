package org.bsuir.view;

import org.bsuir.model.DateLabelFormatter;
import org.bsuir.model.Parameters;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class CardsBuilder {
    private static final int AMOUNT_OF_TEXT_FIELDS = 6;
    private static final int AMOUNT_OF_DATE_PANELS = 1;
    private static final int AMOUNT_OF_LABEL_ITEMS = 6;
    private static final int AMOUNT_OF_COMBO_BOX_ITEMS = 1;

    private final JPanel cards;
    private final JTextField[] textFields;
    private final JDatePanelImpl[] datePanels;
    private final JLabel[] labelItems;
    private final JComboBox<String>[] comboBoxItems;

    public CardsBuilder(){
        textFields = new JTextField[AMOUNT_OF_TEXT_FIELDS];
        datePanels = new JDatePanelImpl[AMOUNT_OF_DATE_PANELS];
        labelItems = new JLabel[AMOUNT_OF_LABEL_ITEMS];
        comboBoxItems = new JComboBox[AMOUNT_OF_COMBO_BOX_ITEMS];

        cards = new JPanel(new CardLayout());

        JPanel sportOrFullNamePanel = createSportOrFullNamePanel();
        JPanel prizeOrIncomePanel = createPrizeOrIncomePanel();
        JPanel TournamentNameOrDatePanel = createTournamentNameOrDatePanel();

        cards.add(sportOrFullNamePanel, Parameters.SEARCH_TYPES[0]);
        cards.add(prizeOrIncomePanel, Parameters.SEARCH_TYPES[1]);
        cards.add(TournamentNameOrDatePanel,Parameters.SEARCH_TYPES[2]);
    }

    private JPanel createTournamentNameOrDatePanel() {

        JPanel tournamentDatePanel = new JPanel();
        JLabel tournamentDateLabel = new JLabel("Tournament date:");

        UtilDateModel dateOfTournamentModel = new UtilDateModel();
        JDatePanelImpl dateOfTournamentPanel = new JDatePanelImpl(dateOfTournamentModel, new Properties());
        JDatePickerImpl dateOfTournamentPicker = new JDatePickerImpl(dateOfTournamentPanel, new DateLabelFormatter());
        dateOfTournamentPanel.setMaximumSize(new Dimension(150,30));
        dateOfTournamentPicker.setMaximumSize(new Dimension(150,30));

        JLabel tournamentNameLabel = new JLabel("Tournament name");
        JTextField tournamentNameTextField = new JTextField();
        tournamentNameTextField.setMaximumSize(new Dimension(150,30));

        GroupLayout layout = new GroupLayout(tournamentDatePanel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        tournamentDatePanel.setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(tournamentNameLabel)
                        .addComponent(tournamentNameTextField))
                .addGroup(layout.createParallelGroup()
                        .addComponent(tournamentDateLabel)
                        .addComponent(dateOfTournamentPicker)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(tournamentNameLabel)
                        .addComponent(tournamentNameTextField))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(tournamentDateLabel)
                        .addComponent(dateOfTournamentPicker)
                )
        );

        labelItems[3] = tournamentDateLabel;
        labelItems[4] = tournamentNameLabel;

        textFields[0] = tournamentNameTextField;

        datePanels[0] = dateOfTournamentPanel;

        return tournamentDatePanel;
    }

    private JPanel createPrizeOrIncomePanel() {
        JPanel prizeOrIncomePanel = new JPanel();

        JLabel prizeLabel = new JLabel("Prize:");
        JLabel incomeLabel = new JLabel("Income:");

        JTextField prizeUpperBorder = new JTextField();
        prizeUpperBorder.setMaximumSize(new Dimension(150,30));

        JTextField prizeLowerBorder = new JTextField();
        prizeLowerBorder.setMaximumSize(new Dimension(150,30));

        JTextField incomeUpperBorder = new JTextField();
        incomeUpperBorder.setMaximumSize(new Dimension(150,30));

        JTextField incomeLowerBorder = new JTextField();
        incomeLowerBorder.setMaximumSize(new Dimension(150,30));

        GroupLayout layout = new GroupLayout(prizeOrIncomePanel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        prizeOrIncomePanel.setLayout(layout);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(prizeLabel)
                        .addComponent(incomeLabel))
                .addGroup(layout.createParallelGroup()
                        .addComponent(prizeUpperBorder)
                        .addComponent(prizeLowerBorder)
                        .addComponent(incomeUpperBorder)
                        .addComponent(incomeLowerBorder))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(prizeLabel)
                        .addComponent(prizeLowerBorder)
                )
                .addComponent(prizeUpperBorder)
                .addGroup(layout.createParallelGroup()
                        .addComponent(incomeLabel)
                        .addComponent(incomeLowerBorder)
                )
                .addComponent(incomeUpperBorder)
        );

        textFields[1] = prizeUpperBorder;
        textFields[2] = incomeUpperBorder;
        textFields[3] = prizeLowerBorder;
        textFields[4] = incomeLowerBorder;

        labelItems[2] = prizeLabel;
        labelItems[5] = incomeLabel;

        return prizeOrIncomePanel;
    }
    private JPanel createSportOrFullNamePanel(){
        JPanel sportOrFullNamePanel = new JPanel();

        JLabel fullNameLabel = new JLabel("Full name");
        JLabel sportTypeLabel = new JLabel("Sport type");

        JTextField fullNameTextField = new JTextField();
        fullNameTextField.setMaximumSize(new Dimension(150,30));

        JComboBox<String> sportType = new JComboBox<>(Parameters.SPORT_TYPES);
        sportType.setMaximumSize(new Dimension(150, 30));

        GroupLayout layout = new GroupLayout(sportOrFullNamePanel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        sportOrFullNamePanel.setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(fullNameLabel)
                        .addComponent(fullNameTextField))
                .addGroup(layout.createParallelGroup()
                        .addComponent(sportTypeLabel)
                        .addComponent(sportType)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(fullNameLabel)
                        .addComponent(fullNameTextField))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(sportTypeLabel)
                        .addComponent(sportType)
                )
        );

        textFields[5] = fullNameTextField;

        comboBoxItems[0] = sportType;

        labelItems[0] = fullNameLabel;

        return sportOrFullNamePanel;
    }

    public JDatePanelImpl[] getDatePanels() {
        return datePanels;
    }

    public JLabel[] getLabelItems() {
        return labelItems;
    }

    public JTextField[] getTextFields() {
        return textFields;
    }

    public JPanel getCards() {
        return cards;
    }

    public JComboBox<String>[] getComboBoxes() { return comboBoxItems; }
}
