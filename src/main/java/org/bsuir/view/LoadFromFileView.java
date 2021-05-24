package org.bsuir.view;

import org.bsuir.controller.LoadFromFileController;
import org.bsuir.model.TournamentsTableModel;

import javax.swing.*;

public class LoadFromFileView {

    public LoadFromFileView(TournamentsTableModel model) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load from");
        new LoadFromFileController(fileChooser,model);
    }
}
