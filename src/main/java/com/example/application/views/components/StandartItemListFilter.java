package com.example.application.views.components;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import java.util.function.Consumer;

public class StandartItemListFilter extends HorizontalLayout {
    protected TextField filter;
    protected Consumer<String> handler;

    public StandartItemListFilter(Consumer<String> filterHandler) {
        this.handler = filterHandler;

        configureFilter();

        add(filter);
    }

    protected void configureFilter() {
        filter = new TextField("", "Type to filter");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> handler.accept(getPattern()));
    }

    public String getPattern() {
        return filter.getValue();
    }

    public void setPlaceholder(String text) {
        filter.setPlaceholder(text);
    }
}
