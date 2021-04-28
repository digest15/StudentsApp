package com.example.application.views.components;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;

public class ItemEditorActionsBar extends HorizontalLayout {
    private Button saveBtn;
    private Button cancelBtn;

    public ItemEditorActionsBar() {
        saveBtn = new Button("Save");
        cancelBtn = new Button("Cancel");

        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);

        saveBtn.addClickShortcut(Key.ENTER);
        cancelBtn.addClickShortcut(Key.ESCAPE);

        saveBtn.addClickListener(event -> fireEvent(new SaveItemEvent(this)));
        cancelBtn.addClickListener(event -> fireEvent(new CancelEvent(this)));

        add(saveBtn, cancelBtn);
    }

    public static abstract class ItemEditorActionsEvent extends ComponentEvent<ItemEditorActionsBar> {
        protected ItemEditorActionsEvent(ItemEditorActionsBar source) {
            super(source, false);
        }
    }

    public static class SaveItemEvent extends ItemEditorActionsEvent{
        public SaveItemEvent(ItemEditorActionsBar source) {
            super(source);
        }
    }

    public static class CancelEvent extends ItemEditorActionsEvent{
        public CancelEvent(ItemEditorActionsBar source) {
            super(source);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
