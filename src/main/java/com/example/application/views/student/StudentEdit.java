package com.example.application.views.student;

import com.example.application.backend.dto.group.GroupDto;
import com.example.application.backend.dto.student.StudentDto;
import com.example.application.backend.entity.Student;
import com.example.application.backend.dao.group.GroupDao;
import com.example.application.views.components.AbstractItemEditor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class StudentEdit extends AbstractItemEditor<StudentDto> {
    private TextField firstName;
    private TextField lastName;
    private TextField patronomic;
    private DatePicker birthday;
    private ComboBox<GroupDto> group;

    private GroupDao groupService;

    public StudentEdit(GroupDao groupService) {
        this.groupService = groupService;

        init();
    }

    @Override
    public void setItem(StudentDto item) {
        group.setItems(groupService.getItems());
        super.setItem(item);
    }

    @Override
    protected Component creteFields() {
        firstName = new TextField("First name");
        lastName = new TextField("Last name");
        patronomic = new TextField("Patronomic");
        birthday = new DatePicker("Birthday");

        group = new ComboBox<>("Group");
        group.setItemLabelGenerator(GroupDto::getSpecialtyName);

        return new VerticalLayout(
                firstName,
                lastName,
                patronomic,
                birthday,
                group
        );
    }
}
