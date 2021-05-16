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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Consumer;

@Route(value = "students", layout = MainView.class)
@PageTitle("Student list")
public class StudentList extends AbstractItemList {
    private StudentDao dao;

    private Grid<StudentDtoForGrid> grid;
    private StandartItemListFilter numberFilter;

    private StudentEdit editor;
    private Dialog dialog = new Dialog();

    @Autowired
    public StudentList(StudentDao dao, StudentEdit editor) {
        this.dao = dao;
        this.editor = editor;

        init();
    }

    @Override
    protected Component createFilter() {
        filter = new StudentFilter(e -> updateList());
        return filter;
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
        openEditor(dao.findById(itemDto.getId()));
    }

    @Override
    protected void updateList() {
        String patternByGroupNumber = filter.getPattern();
        String patternByFullName = ((StudentFilter) filter).getPatternForFullName();
        if (patternByGroupNumber.isEmpty()) {
            grid.setItems(dao.getItemsForGrid());
        }else if (patternByFullName.isEmpty()){
            grid.setItems(dao.getItemsForGrid(patternByGroupNumber));
        }else {
           grid.setItems(dao.getItemsForGrid(patternByFullName, patternByGroupNumber));
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
            openEditor(dao.findById(studentOptional.get().getId()));
        }else {
            Notification.show(SELECT_ITEM_NOTIFICATION_TEXT);
        }
    }

    @Override
    protected void deleteItem() {
        Optional<StudentDtoForGrid> studentOptional = grid.getSelectionModel().getFirstSelectedItem();
        if (studentOptional.isPresent()) {
            dao.delete(dao.findById(studentOptional.get().getId()));
            updateList();
        }else {
            Notification.show(SELECT_ITEM_NOTIFICATION_TEXT);
        }
    }

    private void openEditor(StudentDto student) {
        if (student != null) {
            editor.setItem(student);
            editor.setOnSaveHandler(e -> {
                dao.save((StudentDto) e);
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

    private class StudentFilter extends StandartItemListFilter {
        private TextField filterByFullName;

        public StudentFilter(Consumer<String> filterHandler) {
            super(filterHandler);
            add(filterByFullName);
        }

        public String getPatternForFullName() {
            return filterByFullName.getValue();
        }

        @Override
        protected void configureFilter() {
            filter = new TextField("", "Filter by Group Number");
            filter.setClearButtonVisible(true);
            filter.setValueChangeMode(ValueChangeMode.LAZY);
            filter.addValueChangeListener(e -> {
                filterByFullName.setEnabled(!getPattern().isEmpty());
                handler.accept(getPattern());
            });

            filterByFullName = new TextField("", "Filter by Full Name");
            filterByFullName.setClearButtonVisible(true);
            filterByFullName.setValueChangeMode(ValueChangeMode.LAZY);
            filterByFullName.addValueChangeListener(e -> handler.accept(getPatternForFullName()));
            filterByFullName.setEnabled(false);
        }
    }
}
