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
        setScrollPane();
    }

    private void setScrollPane() {
        //tableScrollPane.setPreferredSize(new Dimension(700, 400));
    }

    private void setTableInfo(TournamentsTableModel model) {
        table.setModel(model);
        table.getTableHeader().setFont(new Font("Segou UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(32, 136, 203, 0));
        table.getTableHeader().setForeground(new Color(255, 255, 255, 0));
        table.setGridColor(new Color(100, 100, 100, 0));
       // table.setPreferredSize(new Dimension(700, 200));
    }

    public JTable getTable() {
        return table;
    }

    public JScrollPane getTableScrollPane() {
        return tableScrollPane;
    }
}
