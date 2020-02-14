package de.fhms.winfo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.util.logging.Logger;

public class PersoServiceDelegate implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("create_hr_user");

    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Lege " + execution.getVariable("first_name") + " " +  execution.getVariable("last_name") +
                " mit dem Benutzernamen: " + execution.getVariable("username") + " im Personalsystem an");

        //TODO: WebSerivce-Call

    }
}
