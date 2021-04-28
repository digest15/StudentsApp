package com.example.application.views.components;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class GridActionsBar extends HorizontalLayout {
    Button newBtn;
    Button editBtn;
    Button deleteBtn;

    public GridActionsBar() {
        newBtn = new Button("New");
        editBtn = new Button("Edit");
        deleteBtn = new Button("Delete");

        newBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        editBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        deleteBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);

        newBtn.addClickListener(buttonClickEvent -> fireEvent(new NewItemEvent(this)));
        editBtn.addClickListener(buttonClickEvent -> fireEvent(new EditItemEvent(this)));
        deleteBtn.addClickListener(buttonClickEvent -> fireEvent(new DeleteItemEvent(this)));

        add(
                newBtn,
                editBtn,
                deleteBtn
        );
    }

    public static abstract class EditBarEvent extends ComponentEvent<GridActionsBar> {
        protected EditBarEvent(GridActionsBar source) {
            super(source, false);
        }
    }

    public static class NewItemEvent extends EditBarEvent{
        public NewItemEvent(GridActionsBar source) {
            super(source);
        }
    }

    public static class EditItemEvent extends EditBarEvent {
        public EditItemEvent(GridActionsBar source) {
            super(source);
        }
    }

    public static class DeleteItemEvent extends EditBarEvent{
        public DeleteItemEvent(GridActionsBar source) {
            super(source);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
