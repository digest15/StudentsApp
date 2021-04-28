package com.example.application.views.student;

import com.example.application.backend.entity.Group;
import com.example.application.backend.entity.Student;
import com.example.application.backend.services.group.GroupService;
import com.example.application.views.components.AbstractItemEditor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.LocalDateToDateConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.time.ZoneId;
import java.util.ArrayList;

@SpringComponent
@UIScope
public class StudentEdit extends AbstractItemEditor<Student> {
    private TextField firstName;
    private TextField lastName;
    private TextField patronomic;
    private DatePicker birthday;
    private ComboBox<Group> group;

    private GroupService groupService;

    public StudentEdit(GroupService groupService) {
        this.groupService = groupService;

        init();
    }

    @Override
    public void setItem(Student item) {
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
        group.setItemLabelGenerator(Group::getSpecialtyName);

        return new VerticalLayout(
                firstName,
                lastName,
                patronomic,
                birthday,
                group
        );
    }
}
