package org.bsuir.controller;

import org.bsuir.XMLParser.TournamentsXMLReader;
import org.bsuir.model.TournamentsTableModel;
import org.bsuir.view.Alert;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoadFromFileController {
    private final JFileChooser fileChooser;
    private final TournamentsTableModel tournamentsTableModel;

    public LoadFromFileController(JFileChooser fileChooser, TournamentsTableModel model) {
        this.fileChooser = fileChooser;
        this.tournamentsTableModel = model;
        addActionListener();
        fileChooser.showOpenDialog(null);
    }

    private void addActionListener() {
        fileChooser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int actionTypeNumber = fileChooser.getApproveButtonMnemonic();
                    if (actionTypeNumber == JFileChooser.APPROVE_OPTION) {

                        TournamentsXMLReader xmlReader = new TournamentsXMLReader(fileChooser.getSelectedFile());

                        tournamentsTableModel.resetModel(xmlReader.readAll());
                    }
                } catch (IllegalArgumentException| SAXException| IOException  | ParserConfigurationException exception) {
                    Alert.incorrectFormatAlert();
                }
            }
        });
    }

}
