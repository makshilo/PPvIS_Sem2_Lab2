package org.bsuir.controller;

import org.bsuir.XMLParser.TournamentsXMLWriter;
import org.bsuir.model.TournamentsTableModel;
import org.bsuir.view.Alert;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.ParseException;

public class SaveToFileController {
    private final JFileChooser fileChooser;
    private final TournamentsTableModel tournamentsTableModel;

    public SaveToFileController(JFileChooser fileChooser, TournamentsTableModel model) {
        this.fileChooser = fileChooser;
        this.tournamentsTableModel = model;
        addActionListener();
        fileChooser.showOpenDialog(null);
    }

    private void addActionListener() {
        fileChooser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int actionTypeNumber = fileChooser.getApproveButtonMnemonic();
                if (actionTypeNumber == JFileChooser.OPEN_DIALOG) {
                    try {
                        TournamentsXMLWriter xmlWriter = new TournamentsXMLWriter(fileChooser.getSelectedFile());
                        xmlWriter.writeAll(tournamentsTableModel.getAllTournaments());
                    } catch (IOException | ParseException | ParserConfigurationException | SAXException | TransformerException exception) {
                        Alert.unsuccessfulWriteToFileAlert();
                    }
                }
            }
        });
    }
}

