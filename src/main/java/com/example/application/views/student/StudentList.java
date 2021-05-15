package com.example.application.views.student;

import com.example.application.backend.dto.student.StudentDto;
import com.example.application.backend.dto.student.StudentDtoForGrid;
import com.example.application.backend.dao.student.StudentDao;
import com.example.application.views.components.AbstractItemList;
import com.example.application.views.components.StandartItemListFilter;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Route(value = "students", layout = MainView.class)
@PageTitle("Student list")
public class StudentList extends AbstractItemList {
    private StudentDao service;

    private Grid<StudentDtoForGrid> grid;
    private StandartItemListFilter numberFilter;

    private StudentEdit editor;
    private Dialog dialog = new Dialog();

    @Autowired
    public StudentList(StudentDao service, StudentEdit editor) {
        this.service = service;
        this.editor = editor;

        init();
    }

    @Override
    protected Component createGrid() {
        grid = new Grid<>(StudentDtoForGrid.class);
        grid.removeAllColumns();
        grid.addColumn("firstName").setHeader("First name");
        grid.addColumn("lastName").setHeader("Last name");
        grid.addColumn("patronomic").setHeader("Patronomic");
        grid.addColumn(i -> i.getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),"birthday")
                .setHeader("Birthday");
        grid.addColumn("groupNumber").setHeader("Group");
        grid.asSingleSelect();
        grid.addItemDoubleClickListener(e -> handDoubleClickOnGrid(e.getItem()));

        return grid;
    }

    private void handDoubleClickOnGrid(StudentDtoForGrid itemDto) {
        openEditor(service.findById(itemDto.getId()));
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
        openEditor(new StudentDto());
    }

    @Override
    protected void editItem() {
        Optional<StudentDtoForGrid> studentOptional = grid.getSelectionModel().getFirstSelectedItem();
        if (studentOptional.isPresent()) {
            openEditor(service.findById(studentOptional.get().getId()));
        }else {
            Notification.show(SELECT_ITEM_NOTIFICATION_TEXT);
        }
    }

    @Override
    protected void deleteItem() {
        Optional<StudentDtoForGrid> studentOptional = grid.getSelectionModel().getFirstSelectedItem();
        if (studentOptional.isPresent()) {
            service.delete(service.findById(studentOptional.get().getId()));
            updateList();
        }else {
            Notification.show(SELECT_ITEM_NOTIFICATION_TEXT);
        }
    }

    private void openEditor(StudentDto student) {
        if (student != null) {
            editor.setItem(student);
            editor.setOnSaveHandler(e -> {
                service.save((StudentDto) e);
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
