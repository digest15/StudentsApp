package com.example.application.views.group;

import com.example.application.backend.entity.Group;
import com.example.application.views.components.AbstractItemEditor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class GroupEdit extends AbstractItemEditor<Group> {
    private TextField number;
    private TextField specialtyName;

    @Autowired
    public GroupEdit() {
        init();
    }

    @Override
    protected Component creteFields() {
        number = new TextField("Number");
        specialtyName = new TextField("Specialty name");
        return new VerticalLayout(number, specialtyName);
    }

}
