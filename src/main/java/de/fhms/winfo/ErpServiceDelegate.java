package de.fhms.winfo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.json.*;
import org.springframework.stereotype.Service;
import org.apache.http.impl.client.HttpClients;
import java.util.Map;
import java.util.logging.Logger;

@Service("erpRequestProcessor")
public class ErpServiceDelegate implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("create_erp_user");

    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Lege " + execution.getVariable("first_name") + " " +  execution.getVariable("last_name") +
                " mit dem Benutzernamen: " + execution.getVariable("username") + " im ERP-System an");

        Map<String, Object> variables = execution.getVariables();
        String firstName = (String) variables.get("first_name");
        String lastName = (String) variables.get("last_name");
        String username = (String) variables.get("username");
        String department = (String) variables.get("department");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost");

        String json = new JSONObject()
                .put("firstName", firstName)
                .put("lastName", lastName)
                .put("username", username)
                .put("department", department)
                .toString();

        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

//        CloseableHttpResponse response = httpClient.execute(httpPost);
//
//        try {
//            HttpEntity responseEntity = response.getEntity();
//            EntityUtils.consume(responseEntity);
//        } finally {
//            response.close();
//        }

    }
}
