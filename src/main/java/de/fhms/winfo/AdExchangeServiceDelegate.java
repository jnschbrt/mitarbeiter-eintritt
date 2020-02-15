package de.fhms.winfo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

public class AdExchangeServiceDelegate implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("create_ad_exchange_user");

    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Lege " + execution.getVariable("first_name") + " "
                +  execution.getVariable("last_name") + " im Active Directory und Exchange an");

        // Neue Prozessvariablen "username" und "email" aus Vor- und Nachname erstellen
        String firstName = execution.getVariable("first_name").toString();
        String lastName = execution.getVariable("last_name").toString();
        String username = firstName.substring(0,1) + lastName;
        execution.setVariable("username", username.toLowerCase());
        execution.setVariable("email", firstName.toLowerCase()+"."+lastName.toLowerCase()+"@unternehmen.de");

        LOGGER.info(execution.getVariable("first_name") + " " +  execution.getVariable("last_name")
                + " angelegt. Erstellter Benutzername: " + execution.getVariable("username")
                + ", Email: " + execution.getVariable("email"));

    }
}
