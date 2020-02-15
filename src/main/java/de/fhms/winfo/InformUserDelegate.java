package de.fhms.winfo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("informUserDelegate")
public class InformUserDelegate implements JavaDelegate {

    @Autowired
    private EmailService emailService;

    public void execute(DelegateExecution execution) throws Exception {
        String to = (String) execution.getVariable("notification_email");
        String subject = (String) execution.getVariable("username") + " vollständig erfasst";
        String message = "Hallo, \n"
                + "Folgender Benutzer wurde durch die Process-Engine in allen notwendigen Systemen erfasst \n\n"
                + "Vorname: " + execution.getVariable("first_name") + "\n"
                + "Nachname: " + execution.getVariable("last_name") + "\n"
                + "Benutzername (autom. generiert): " + execution.getVariable ("username") + "\n"
                + "E-Mail (autom. generiert): " + execution.getVariable("email") + "\n"
                + "Abteilung: " + execution.getVariable("department") + "\n"
                + "Position/Job-Bezeichnung: " + execution.getVariable("position") + "\n"
                + "Art der Anstellung: " + execution.getVariable("type_of_enployment") + "\n"
                + "Befristeter Arbeitsvertrag: " + execution.getVariable("limited") + "\n"
                + "Schulungen geplant: " + execution.getVariable("training");

        emailService.sendEmail(to, subject, message);
    }
}
