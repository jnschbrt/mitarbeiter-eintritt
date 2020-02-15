package de.fhms.winfo;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ProcessStartedListener implements TaskListener {

    private final static Logger LOGGER = Logger.getLogger(ProcessStartedListener.class.getName());

    @Autowired
    private EmailService emailService;

    public void notify(DelegateTask delegateTask) {

        String assignee = delegateTask.getAssignee();

        if (assignee != null) {
            IdentityService identityService = Context.getProcessEngineConfiguration().getIdentityService();
            User user = identityService.createUserQuery().userId(assignee).singleResult();

            if (user != null) {
                String recipient = user.getEmail();
                LOGGER.info("Assignee: " + assignee + " .E-Mail: " + recipient);

                if (recipient != null && !recipient.isEmpty()) {
                    String subject = "Neuer Mitarbeiter-Eintritt Prozess gestartet";
                    String message = "Bitte vervollständige die Aufgaben in Camunda!";
                    emailService.sendEmail(recipient, subject, message);
                } else {
                    LOGGER.warning("Es konnte keine E-Mail an: " + assignee + " gesendet werden. " +
                            "Benutzer hat keine Email hinterlegt");
                }
            } else {
                LOGGER.warning("Es konnte keine E-Mail an: " + assignee + " gesendet werden. " +
                        "Benutzer ist nicht am Identitäts-Service registriert.");
            }
        }
    }

}
