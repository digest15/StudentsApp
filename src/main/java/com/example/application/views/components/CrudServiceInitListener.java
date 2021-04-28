package com.example.application.views.components;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;


@SpringComponent
public class CrudServiceInitListener implements VaadinServiceInitListener {
    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {
        serviceInitEvent.getSource().addSessionInitListener(e -> {
            e.getSession().setErrorHandler(errorEvent -> {
                Throwable t = errorEvent.getThrowable();
                handExceptions(t);
            });
        });
    }

    private void handExceptions(Throwable t) {
        if (t instanceof DataIntegrityViolationException) {
            Notification.show("Item has references!");
            return;
        }

        if (t instanceof OptimisticLockingFailureException) {
            Notification.show("Item was modified in another session. Reopen Item.");
        }
    }
}
