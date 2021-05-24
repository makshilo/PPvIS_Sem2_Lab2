package org.bsuir.view;

import java.awt.*;

public class MenuBarBuilder {

    public static final int AMOUNT_OF_MENU_BAR_ITEMS = 5;

    private MenuBar menuBar;
    private final MenuItem[] menuBarItems;

    public MenuBarBuilder() {
        menuBarItems = new MenuItem[AMOUNT_OF_MENU_BAR_ITEMS];
        makeMenuBar();
    }

    private void makeMenuBar() {
        MenuItem openFileItem = new MenuItem("Open file");
        MenuItem saveAsItem = new MenuItem("Save as");
        MenuItem addTournament = new MenuItem("Add");
        MenuItem deleteTournament = new MenuItem("Delete");
        MenuItem searchTournament = new MenuItem("Search");

        addMenuBarItems(openFileItem, saveAsItem, addTournament, deleteTournament, searchTournament);

        Menu fileMenu = new Menu("File");
        Menu tournamentMenu = new Menu("Tournament");

        fileMenu.add(openFileItem);
        fileMenu.add(saveAsItem);

        tournamentMenu.add(addTournament);
        tournamentMenu.add(deleteTournament);
        tournamentMenu.add(searchTournament);

        menuBar = new MenuBar();
        menuBar.add(fileMenu);
        menuBar.add(tournamentMenu);

    }

    private void addMenuBarItems(MenuItem openFileItem, MenuItem saveAsItem, MenuItem addTournament, MenuItem deleteTournament, MenuItem searchTournament) {
        menuBarItems[0] = openFileItem;
        menuBarItems[1] = saveAsItem;
        menuBarItems[2] = addTournament;
        menuBarItems[3] = deleteTournament;
        menuBarItems[4] = searchTournament;
    }

    public MenuItem[] getMenuBarItems() {
        return menuBarItems;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
