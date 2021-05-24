package org.bsuir.view;

import org.bsuir.controller.AddTournamentController;
import org.bsuir.model.TournamentsTableModel;

public class AddTournamentView {

    public AddTournamentView(TournamentsTableModel model){
        AddTournamentBuilder addTournamentBuilder = new AddTournamentBuilder();
        new AddTournamentController(model, addTournamentBuilder.getTextFields(), addTournamentBuilder.getDatePanels(),
                addTournamentBuilder.getEnterButton());
    }
}
