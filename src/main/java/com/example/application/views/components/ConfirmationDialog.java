package com.example.application.views.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ConfirmationDialog extends Dialog {
    protected Button confirmBtn;
    protected ActionHandler confirmHandler;

    protected Button cancelBtn;
    protected ActionHandler cancelHandler = ()->{};

    @FunctionalInterface
    public interface ActionHandler {
        void onHand();
    }

    public ConfirmationDialog(String message, ActionHandler confirmHandler) {
        this.confirmHandler = confirmHandler;

        Text text = new Text(message);

        confirmBtn = new Button("OK");
        cancelBtn = new Button("Cancel");

        confirmBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);

        confirmBtn.addClickShortcut(Key.ENTER);
        cancelBtn.addClickShortcut(Key.ESCAPE);

        confirmBtn.addClickListener(this::confirmation);
        cancelBtn.addClickListener(this::canceled);

        HorizontalLayout buttonlayout = new HorizontalLayout(confirmBtn, cancelBtn);
        buttonlayout.setVerticalComponentAlignment(FlexComponent.Alignment.END, cancelBtn);

        add(new VerticalLayout(text, buttonlayout));
    }

    public ConfirmationDialog(String message,
                              ActionHandler confirmHandler,
                              ActionHandler cancelHandler) {

        this(message, confirmHandler);
        this.cancelHandler = cancelHandler;
    }

    public ConfirmationDialog(String message,
                              ActionHandler confirmHandler,
                              ActionHandler cancelHandler,
                              String confirmCaption,
                              String cancelCaption) {

        this(message, confirmHandler, cancelHandler);
        confirmBtn.setText(confirmCaption);
        cancelBtn.setText(cancelCaption);
    }

    private void canceled(ClickEvent<Button> buttonClickEvent) {
        close();
        cancelHandler.onHand();
    }

    private void confirmation(ClickEvent<Button> buttonClickEvent) {
        close();
        confirmHandler.onHand();
    }
}
