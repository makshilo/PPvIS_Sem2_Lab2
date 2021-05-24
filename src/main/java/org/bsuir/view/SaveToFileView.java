package org.bsuir.view;

import org.bsuir.controller.SaveToFileController;
import org.bsuir.model.TournamentsTableModel;

import javax.swing.*;

public class SaveToFileView {
    public SaveToFileView(TournamentsTableModel model) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save to");
        new SaveToFileController(fileChooser,model);
    }
}
