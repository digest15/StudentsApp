package com.example.application.views.group;

import com.example.application.backend.dto.group.GroupDto;
import com.example.application.backend.dto.student.StudentDto;
import com.example.application.views.components.AbstractItemEditor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class GroupEdit extends AbstractItemEditor<GroupDto> {
    private TextField number;
    private TextField specialtyName;
    private Grid<StudentDto> groupGrid;

    @Autowired
    public GroupEdit() {
        init();
    }

    @Override
    public void setItem(GroupDto item) {
        super.setItem(item);
        groupGrid.setItems(item.getStudents());
    }

    @Override
    protected Component creteFields() {
        number = new TextField("Number");
        specialtyName = new TextField("Specialty name");
        groupGrid = createGroupGrid();

        return new VerticalLayout(number, specialtyName, groupGrid);
    }

    private Grid<StudentDto> createGroupGrid() {
        Grid<StudentDto> grid = new Grid<>(StudentDto.class);
        grid.setWidthFull();
        grid.removeAllColumns();
        grid.addColumn("fullName")
                .setHeader("Student")
                .setAutoWidth(true);

        return grid;
    }

}
