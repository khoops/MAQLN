import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIIngest {

    public static Map<Integer, Question> fetchQuestionDetails(Map<Integer, Question> questionMap, URL apiUrl)
            throws IOException {

        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setConnectTimeout(10_000); 
        connection.setReadTimeout(30_000); 
        connection.setRequestProperty("User-Agent",
                "MAQLN project (+mailto:dmcclune04@qub.ac.uk; initial data import and limited updates)");
        connection.setRequestProperty("From", "dmcclune04@qub.ac.uk");
        connection.setRequestProperty("Accept", "application/json");

        int status = connection.getResponseCode();
        if (status != 200) {
            throw new IOException("HTTP " + status + " for " + apiUrl);
        }

        ObjectMapper mapper = new ObjectMapper();

        try (InputStream questionData = connection.getInputStream()) {
            JsonNode root = mapper.readTree(questionData); // determines the highest level node in the schema
            if (root.has("Error")) {
                throw new IOException("API error for " + apiUrl + ": " + root.path("Error").asText());
            } // to flag errors as they occur

            JsonNode questions = root.path("QuestionsList").path("Question");

            // Internal ID for internal use only
            int internalID = 0;

            for (JsonNode node : questions) {
                // Defines key for hasmap from documentId as int
                int key = node.path("DocumentId").asInt();
                if (!questionMap.containsKey(key)) {
                    Question q = new Question();
                    applyQuestionFields(q, node);
                    questionMap.put(key, q);

                    // internalID++;

                    // // Debugging printout
                    // System.out.print("#" + internalID + " | " + q.documentId + " | ");
                    // for (Minister m : q.getMinisters()) {
                    // System.out.println(m.ministerPersonId);
                    // }

                } else {
                    // finds extant instance of Question at given key
                    Question extant = questionMap.get(key);
                    applyQuestionFields(extant, node);

                    // internalID++;

                    // Debugging printout
                    // System.out.print(
                    // "!!" + internalID + " | " + extant.documentId + " | ");
                    // for (Minister m : extant.getMinisters()) {
                    // System.out.print(m.ministerPersonId + " ");
                    // }
                    // if (extant.answerPlainText != null) {
                    // System.out.print(
                    // extant.answerPlainText.substring(0, Math.min(54,
                    // extant.answerPlainText.length())));
                    // } else {
                    // System.out.println(" - (present on, but no answer from, AnswerByAPI)");
                }
            }
        }

        return questionMap;

    }

    // creates a Minister
    private static Minister applyMinisterFields(Question q, JsonNode node) {
        Minister m = new Minister();
        m.setMinisterTitle(node.path("MinisterTitle").asText());
        m.setMinisterAffiliationId(node.path("MinisterAffiliationId").asText());
        m.setMinisterPersonId(node.path("MinisterPersonId").asText());
        return m;
    }

    // Maps JSON fields to the setters.

    private static void applyQuestionFields(Question q, JsonNode node) {

        q.setDocumentId(node.path("DocumentId").asText());

        if (SetterHelperMethods.isMissing(q.getDocumentType())) {
            q.setDocumentType(node.path("DocumentType").asText());
        }
        if (SetterHelperMethods.isMissing(q.getReference())) {
            q.setReference(node.path("Reference").asText());
        }
        if (SetterHelperMethods.isMissing(q.getTablerName())) {
            q.setTablerName(node.path("TablerName").asText());
        }
        if (SetterHelperMethods.isMissing(q.getTablerTitle())) {
            q.setTablerTitle(node.path("TablerTitle").asText());
        }
        if ((q.getTablerAffiliationId() == 0)) {
            q.setTablerAffiliationId(node.path("TablerAffiliationId").asText());
        }
        if ((q.getTablerPersonId() == 0)) {
            q.setTablerPersonId(node.path("TablerPersonId").asText());
        }
        if ((q.getTabledDate() == null)) {
            q.setTabledDate(node.path("TabledDate").asText());
        }
        if ((q.getAnsweredOnDate() == null)) {
            q.setAnsweredOnDate(node.path("AnsweredOnDate").asText());
        }
        if (SetterHelperMethods.isMissing(q.getQuestionText())) {
            q.setQuestionText(node.path("QuestionText").asText());
        }
        if ((q.getAnswerByDate() == null)) {
            q.setAnswerByDate(node.path("AnswerByDate").asText());
        }

        // Boolean, false is the default
        q.setPriorityRequest(node.path("PriorityRequest").asText());

        if (SetterHelperMethods.isMissing(q.getDepartment())) {
            q.setDepartment(node.path("Department").asText());
        }
        if ((q.getDepartmentID() == 0)) {
            q.setDepartmentID(node.path("DepartmentID").asText());
        }
        if (SetterHelperMethods.isMissing(q.getAnswerPlainText())) {
            q.setAnswerPlainText(node.path("AnswerPlainText").asText());
        }
        if (SetterHelperMethods.isMissing(q.getAnswerOpenXml())) {
            q.setAnswerOpenXml(node.path("AnswerOpenXml").asText());
        }
        if (SetterHelperMethods.isMissing(q.getAnswerHtml())) {
            q.setAnswerHtml(node.path("AnswerHtml").asText());
        }

        // Any number of ministers can be added, so no need to limit insert
        q.addMinister(applyMinisterFields(q, node));
    }
}
