package com.example.application.views.group;

import com.example.application.backend.dto.group.GroupDto;
import com.example.application.backend.dto.group.GroupDtoForGrid;
import com.example.application.backend.dao.group.GroupDao;
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
    private GroupDao dao;

    private Grid<GroupDtoForGrid> grid;

    private GroupEdit editor;
    private Dialog dialog = new Dialog();

    @Autowired
    public GroupList(GroupDao dao, GroupEdit editor) {
        this.dao = dao;
        this.editor = editor;

        init();
    }

    @Override
    protected void init() {
        super.init();

        filter.setPlaceholder("Type to filter by Spetiality name or number");
    }

    @Override
    protected Component createGrid() {
        grid = new Grid<>(GroupDtoForGrid.class);
        grid.removeColumnByKey("id");
        grid.asSingleSelect();
        grid.addItemDoubleClickListener(e -> handDoubleClickOnGrid(e.getItem()));

        return grid;
    }

    @Override
    protected void updateList() {
        String pattern = filter.getPattern();
        if (pattern.isEmpty()) {
            grid.setItems(dao.getItemsForGrid());
        }else {
            grid.setItems(dao.getItemsForGrid(pattern));
        }
    }

    @Override
    protected void addItem() {
        openEditor(new GroupDto());
    }

    @Override
    protected void editItem() {
        Optional<GroupDtoForGrid> groupOptional = grid.getSelectionModel().getFirstSelectedItem();
        if (groupOptional.isPresent()) {
            openEditor(dao.findById(groupOptional.get().getId()));
        }else {
            Notification.show(SELECT_ITEM_NOTIFICATION_TEXT);
        }
    }

    @Override
    protected void deleteItem() {
        Optional<GroupDtoForGrid> groupOptional = grid.getSelectionModel().getFirstSelectedItem();
        if (groupOptional.isPresent()) {
            dao.delete(dao.findById(groupOptional.get().getId()));
            updateList();
        }else {
            Notification.show(SELECT_ITEM_NOTIFICATION_TEXT);
        }
    }

    private void handDoubleClickOnGrid(GroupDtoForGrid itemDto) {
        openEditor(dao.findById(itemDto.getId()));
    }

    private void openEditor(GroupDto group) {
        if (group != null) {
            editor.setItem(group);
            editor.setOnSaveHandler(e -> {
                dao.save((GroupDto) e);
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
