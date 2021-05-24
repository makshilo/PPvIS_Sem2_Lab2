package org.bsuir.view;

import org.bsuir.model.TournamentsTableModel;

import javax.swing.*;
import java.awt.*;

public class TableBuilder {
    private final JTable table;
    private final JScrollPane tableScrollPane;

    public TableBuilder(TournamentsTableModel model) {
        this.table = new JTable();
        this.tableScrollPane = new JScrollPane(table);

        setTableInfo(model);
    }

    private void setTableInfo(TournamentsTableModel model) {
        table.setModel(model);
        table.getTableHeader().setFont(new Font("Segou UI", Font.BOLD, 12));
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(new Color(150,190,100, 255));
        table.getTableHeader().setForeground(new Color(255, 255, 255, 255));
        table.setGridColor(new Color(100, 100, 100, 255));
    }

    public JTable getTable() {
        return table;
    }

    public JScrollPane getTableScrollPane() {
        return tableScrollPane;
    }
}
