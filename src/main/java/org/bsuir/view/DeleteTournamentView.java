package org.bsuir.view;

import org.bsuir.controller.DeleteTournamentController;
import org.bsuir.model.TournamentsTableModel;

public class DeleteTournamentView {
    public DeleteTournamentView(TournamentsTableModel model) {
        DeleteTournamentBuilder deleteTournamentBuilder = new DeleteTournamentBuilder();
        new DeleteTournamentController(model, deleteTournamentBuilder.getDeleteButton(),
                deleteTournamentBuilder.getTextFields(), deleteTournamentBuilder.getLabelItems(),
                deleteTournamentBuilder.getDatePanels(), deleteTournamentBuilder.getComboBoxes(), deleteTournamentBuilder.getDeleteByTypeComboBox(),
                deleteTournamentBuilder.getCards());
    }
}
