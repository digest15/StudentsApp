package com.example.application.views.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

import java.util.function.Consumer;

public abstract class AbstractItemEditor<T> extends VerticalLayout {
    protected T item;
    protected Binder<T> binder;

    protected Consumer<T> onSaveHandler;

    protected Consumer<T> onCancelHandler;

    public AbstractItemEditor() {
    }

    protected abstract Component creteFields();

    public void cancelItemEdit() {
        if (binder.hasChanges()) {
            ConfirmationDialog dialog = new ConfirmationDialog("You have unsaved changes. Are you want save the changes?",
                    () -> saveItem(),
                    ()->onAction(onCancelHandler),
                    "Yes",
                    "No");
            dialog.open();
        }else {
            onAction(onCancelHandler);
        }

        onClose();
    }

    public void saveItem(){
        try {
            binder.writeBean(item);
        } catch (ValidationException e) {

        }
        onAction(onSaveHandler);
    }

    public void setItem(T item) {
        this.item = item;
        initBinder();
        binder.readBean(item);
    }

    protected void init() {
        add(
                creteFields(),
                createEditorActions()
        );
    }

    protected Component createEditorActions(){
        ItemEditorActionsBar actionsBar = new ItemEditorActionsBar();
        actionsBar.addListener(ItemEditorActionsBar.SaveItemEvent.class, this::saveEventHandler);
        actionsBar.addListener(ItemEditorActionsBar.CancelEvent.class, this::cancelEventHandler);

        return actionsBar;
    }

    protected void initBinder(){
        if (binder == null && item != null) {
            binder = new Binder(item.getClass());
            binder.bindInstanceFields(this);
        }
    }

    protected void onClose() {
        setItem(null);
    }

    private void cancelEventHandler(ItemEditorActionsBar.CancelEvent event) {
        cancelItemEdit();
    }

    private void saveEventHandler(ItemEditorActionsBar.SaveItemEvent event) {
        saveItem();
    }

    private void onAction(Consumer<T> handler) {
        if (handler != null) {
            handler.accept(item);
        }
    }

    public void setOnSaveHandler(Consumer<T> onSaveHandler) {
        this.onSaveHandler = onSaveHandler;
    }

    public void setOnCancelHandler(Consumer<T> onCancelHandler) {
        this.onCancelHandler = onCancelHandler;
    }
}
