package org.bsuir.view;

import org.bsuir.controller.SearchTournamentController;
import org.bsuir.model.TournamentsTableModel;

public class SearchTournamentView {

    public SearchTournamentView(TournamentsTableModel model) {
        SearchTournamentBuilder searchTournamentBuilder = new SearchTournamentBuilder(model);
        new SearchTournamentController(model, searchTournamentBuilder.getDeleteButton(),
                searchTournamentBuilder.getCardsTextFields(), searchTournamentBuilder.getCardsLabelItems(),
                searchTournamentBuilder.getCardsDatePanels(), searchTournamentBuilder.getSearchByTypeComboBox(),
                searchTournamentBuilder.getTable(), searchTournamentBuilder.getPageButtonItems(),
                searchTournamentBuilder.getPageSpinner(), searchTournamentBuilder.getPageLabelItems(),
                searchTournamentBuilder.getCards());
    }

}
