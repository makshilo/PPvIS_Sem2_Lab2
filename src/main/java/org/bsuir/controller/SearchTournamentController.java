package org.bsuir.controller;

import org.bsuir.exception.EmptyFieldException;
import org.bsuir.model.DateManager;
import org.bsuir.model.TournamentsTableModel;
import org.bsuir.model.Parameters;
import org.bsuir.view.Alert;
import org.bsuir.view.CardsBuilder;
import org.bsuir.view.PageComponentsBuilder;
import org.jdatepicker.impl.JDatePanelImpl;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.Objects;

public class SearchTournamentController {
    /**
     * @see CardsBuilder#getLabelItems()
     */
    private final JTextField[] cardsTextFields;
    /**
     * @see CardsBuilder#getLabelItems()
     */
    private final JLabel[] cardsLabelItems;
    /**
     * @see CardsBuilder#getLabelItems()
     */
    private final JDatePanelImpl[] cardsDatePanels;

    /**
     * @see PageComponentsBuilder#getButtonItems()
     */
    private final JButton[] pageButtonItems;
    /**
     * @see PageComponentsBuilder#getLabelItems()
     */
    private final JLabel[] pageLabelItems;
    private final JSpinner pageSpinner;

    private final TournamentsTableModel fullModel;
    private TournamentsTableModel foundModel;
    private final JButton SearchButton;
    private final JComboBox<String> searchByTypeComboBox;
    private final JTable table;
    private final JPanel cards;
    private final JComboBox<String>[] comboBoxes;

    public SearchTournamentController(TournamentsTableModel fullModel, JButton SearchButton, JTextField[] cardsTextFields,
                                      JLabel[] cardsLabelItems, JDatePanelImpl[] datePanel,
                                      JComboBox<String> searchByTypeComboBox, JTable table, JButton[] pageButtonItems,
                                      JSpinner pageSpinner, JLabel[] pageLabelItems, JPanel cards, JComboBox<String>[] comboBoxes) {

        this.table = table;
        this.fullModel = fullModel;
        this.SearchButton = SearchButton;
        this.searchByTypeComboBox = searchByTypeComboBox;
        this.comboBoxes = comboBoxes;

        this.cards = cards;
        this.cardsDatePanels = datePanel;
        this.cardsLabelItems = cardsLabelItems;
        this.cardsTextFields = cardsTextFields;

        this.pageButtonItems = pageButtonItems;
        this.pageLabelItems = pageLabelItems;
        this.pageSpinner = pageSpinner;

        table.setModel(fullModel);
        setSearchButtonAction();
        setPageButtonItemsController();
        addComboBoxItemListener();
        addPageSpinnerChangeListener();
        addModelDataChangedListener();
    }

    private void addComboBoxItemListener() {
        searchByTypeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                CardLayout layout = (CardLayout) (cards.getLayout());
                layout.show(cards, (String) e.getItem());
            }
        });
    }

    private void addModelDataChangedListener() {
        fullModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int newPageNumber = Integer.parseInt(pageLabelItems[3].getText());
                updateComponents(newPageNumber);
            }
        });
    }

    private void setPageButtonItemsController() {
        pageButtonItems[0].addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateComponents(1);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    Alert.wrongPageAlert();
                }
            }

        });

        pageButtonItems[1].addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int newPageNumber = Integer.parseInt(pageLabelItems[3].getText()) - 1;
                    updateComponents(newPageNumber);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    Alert.wrongPageAlert();
                }
            }
        });

        pageButtonItems[2].addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int newPageNumber = Integer.parseInt(pageLabelItems[3].getText()) + 1;
                    updateComponents(newPageNumber);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    Alert.wrongPageAlert();
                }
            }
        });


        pageButtonItems[3].addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int amountOfNotesOnTheTable = getAmountOfNotesOnTheTable();

                    int newPageNumber = (foundModel.getRowCount() - 1) / amountOfNotesOnTheTable + 1;
                    updateComponents(newPageNumber);

                } catch (ArrayIndexOutOfBoundsException exception) {
                    Alert.wrongPageAlert();
                }
            }
        });
    }

    private void updateComponents(int newPageNumber) throws ArrayIndexOutOfBoundsException {
        int amountOfNotesOnTheTable = getAmountOfNotesOnTheTable();
        int amountOfPages = (foundModel.getRowCount() - 1) / amountOfNotesOnTheTable + 1;

        table.setModel(foundModel.createPagedSubModel(newPageNumber, amountOfNotesOnTheTable));

        pageLabelItems[1].setText("Page count: " + amountOfPages);
        pageLabelItems[2].setText("Total record counter: " + fullModel.getRowCount());
        pageLabelItems[3].setText(String.valueOf(newPageNumber));
    }

    private void setSearchButtonAction() {
        SearchButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String currentSearchType = (String) searchByTypeComboBox.getSelectedItem();

                    if (currentSearchType == null) {
                        throw new EmptyFieldException("Unknown type");
                    }

                    if (currentSearchType.equals(Parameters.SEARCH_TYPES[0])) {
                        String fullName = getFullName();
                        String sportType = getSportType();

                        foundModel = fullModel.createSubModelByFullNameOrSportType(fullName, sportType);
                        table.setModel(foundModel);

                    } else if (currentSearchType.equals(Parameters.SEARCH_TYPES[1])) {
                        String prizeLower = getPrizeLower();
                        String prizeUpper = getPrizeUpper();
                        String incomeLower = getIncomeLower();
                        String incomeUpper = getIncomeUpper();


                        foundModel = fullModel.createSubModelByPrizeOrIncome(prizeUpper, incomeUpper, prizeLower, incomeLower);
                        table.setModel(foundModel);


                    } else if (currentSearchType.equals(Parameters.SEARCH_TYPES[2])) {
                        String name = getTournamentName();
                        DateManager date = new DateManager(getDateOfTournament());

                        foundModel = fullModel.createSubModelByTournamentDateOrName(name, date);
                        table.setModel(foundModel);
                    }
                } catch (EmptyFieldException exception) {
                    Alert.unknownTypeAlert();
                }
            }
        });
    }

    private void addPageSpinnerChangeListener() {
        pageSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    int newPageNumber = Integer.parseInt(pageLabelItems[3].getText());
                    updateComponents(newPageNumber);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    Alert.wrongPageAlert();
                }
            }
        });
    }

    private String getPrizeUpper() { return cardsTextFields[1].getText(); }

    private String getPrizeLower() { return cardsTextFields[3].getText(); }

    private String getIncomeUpper() { return cardsTextFields[2].getText(); }

    private String getIncomeLower() { return cardsTextFields[4].getText(); }

    private int getAmountOfNotesOnTheTable() {
        return (int) pageSpinner.getValue();
    }

    private Date getDateOfTournament() { return (Date) cardsDatePanels[0].getModel().getValue(); }

    private String getTournamentName() { return cardsTextFields[0].getText(); }



    private Date getBirthday() {
        return (Date) cardsDatePanels[0].getModel().getValue();
    }


    private String getFullName() {
        return cardsTextFields[5].getText();
    }

    private String getSportType() { return Objects.requireNonNull(comboBoxes[0].getSelectedItem()).toString(); }

}
