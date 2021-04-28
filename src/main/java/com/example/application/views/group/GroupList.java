package com.example.application.views.group;

import com.example.application.backend.entity.Group;
import com.example.application.backend.dto.GroupDTO;
import com.example.application.backend.services.group.GroupService;
import com.example.application.views.components.AbstractItemList;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Route(value = "groups", layout = MainView.class)
@PageTitle("Group list")
public class GroupList extends AbstractItemList {
    private GroupService service;

    private Grid<GroupDTO> grid;

    private GroupEdit editor;
    private Dialog dialog = new Dialog();

    @Autowired
    public GroupList(GroupService service, GroupEdit editor) {
        this.service = service;
        this.editor = editor;

        init();
    }

    @Override
    protected Component createGrid() {
        grid = new Grid<>(GroupDTO.class);
        grid.removeColumnByKey("id");
        grid.asSingleSelect();
        grid.addItemDoubleClickListener(e -> handDoubleClickOnGrid(e.getItem()));

        return grid;
    }

    @Override
    protected void updateList() {
        String pattern = filter.getPattern();
        if (pattern.isEmpty()) {
            grid.setItems(service.getItemsForGrid());
        }else {
            grid.setItems(service.getItemsForGrid(pattern));
        }
    }

    @Override
    protected void addItem() {
        openEditor(new Group());
    }

    @Override
    protected void editItem() {
        Optional<GroupDTO> groupOptional = grid.getSelectionModel().getFirstSelectedItem();
        if (groupOptional.isPresent()) {
            openEditor(service.findById(groupOptional.get().getId()));
        }else {
            Notification.show(SELECT_ITEM_NOTIFICATION_TEXT);
        }
    }

    @Override
    protected void deleteItem() {
        Optional<GroupDTO> groupOptional = grid.getSelectionModel().getFirstSelectedItem();
        if (groupOptional.isPresent()) {
            service.delete(service.findById(groupOptional.get().getId()));
            updateList();
        }else {
            Notification.show(SELECT_ITEM_NOTIFICATION_TEXT);
        }
    }

    private void handDoubleClickOnGrid(GroupDTO itemDto) {
        openEditor(service.findById(itemDto.getId()));
    }

    private void openEditor(Group group) {
        if (group != null) {
            editor.setItem(group);
            editor.setOnSaveHandler(e -> {
                service.save((Group) e);
                closeDialog();
                updateList();
            });
            editor.setOnCancelHandler(e -> closeDialog());

            dialog.setResizable(true);
            dialog.setCloseOnOutsideClick(false);
            dialog.add(editor);
            dialog.open();
        }
    }

    private void closeDialog() {
        dialog.close();
    }

}
