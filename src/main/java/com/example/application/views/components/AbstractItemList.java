package com.example.application.views.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class AbstractItemList extends VerticalLayout {
    protected StandartItemListFilter filter;
    protected GridActionsBar gridActionsBar;

    protected final String SELECT_ITEM_NOTIFICATION_TEXT = "You need select item in grid";

    public AbstractItemList() {

    }

    protected abstract Component createGrid();

    protected abstract void updateList();

    protected abstract void addItem();

    protected abstract void editItem();

    protected abstract void deleteItem();

    protected void init() {
        add(
                createFilter(),
                createEditBar(),
                createGrid()
        );

        updateList();
    }

    protected Component createFilter(){
        filter = new StandartItemListFilter(e -> updateList());
        return filter;
    };

    protected Component createEditBar(){
        gridActionsBar = new GridActionsBar();
        gridActionsBar.addListener(GridActionsBar.NewItemEvent.class, this::addItemHandler);
        gridActionsBar.addListener(GridActionsBar.EditItemEvent.class, this::editItemHandler);
        gridActionsBar.addListener(GridActionsBar.DeleteItemEvent.class, this::deleteItemHandler);

        return gridActionsBar;
    }

    private void deleteItemHandler(GridActionsBar.DeleteItemEvent event) {
        ConfirmationDialog dialog = new ConfirmationDialog("Are you sure what you want delete item?",
                () -> deleteItem());
        dialog.open();
    }

    private void editItemHandler(GridActionsBar.EditBarEvent event) {
        editItem();
    }

    private void addItemHandler(GridActionsBar.NewItemEvent event) {
        addItem();
    }

}
