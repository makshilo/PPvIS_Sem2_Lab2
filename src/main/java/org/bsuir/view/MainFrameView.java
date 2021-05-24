package org.bsuir.view;

import org.bsuir.controller.MainFrameController;
import org.bsuir.model.TournamentsTableModel;


public class MainFrameView {

    public MainFrameView() {
        TournamentsTableModel tournamentsTableModel = new TournamentsTableModel();
        MainFrameBuilder frameBuilder = new MainFrameBuilder(tournamentsTableModel);

        new MainFrameController(tournamentsTableModel,frameBuilder.getMenuBarItems(),frameBuilder.getButtonItems(),
                frameBuilder.getLabelItems(), frameBuilder.getPageSpinner(), frameBuilder.getTable());

    }

}