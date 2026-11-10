import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIIngest {

    public static Map<Integer, Question> fetchQuestionDetails(Map<Integer, Question> questionMap, URL apiUrl)
            throws IOException {

        URLConnection connection = apiUrl.openConnection();

        ObjectMapper mapper = new ObjectMapper();

        try (InputStream questionData = connection.getInputStream()) {
            JsonNode root = mapper.readTree(questionData); // determines the highest level node in the schema
            JsonNode questions = root.path("QuestionsList").path("Question");

            int internalID = 0;

            for (JsonNode node : questions) {
                //Defines key for hasmap from documentId as int
                int key = node.path("DocumentId").asInt();
                if (!questionMap.containsKey(key)) {
                    Question q = new Question();
                    applyFields(q, node);
                    questionMap.put(key, q);

                    internalID++;
                    System.out.println("#" + internalID + " | " + q.documentId + " | ");
                } else {
                    //finds extant instance of Question at given key
                    Question extant = questionMap.get(key);
                    applyFields(extant, node);

                    internalID++;
                    System.out.println("%" + internalID + " | " + extant.documentId + " | ");
                }
            }
        }
        return questionMap;
    }

    // Maps JSON fields to the setters.
   
    private static void applyFields(Question q, JsonNode node) {
        q.setDocumentId(node.path("DocumentId").asText());
        q.setDocumentType(node.path("DocumentType").asText());
        q.setReference(node.path("Reference").asText());
        q.setTablerName(node.path("TablerName").asText());
        q.setTablerTitle(node.path("TablerTitle").asText());
        q.setTablerAffiliationId(node.path("TablerAffiliationId").asText());
        q.setTablerPersonId(node.path("TablerPersonId").asText());
        q.setTabledDate(node.path("TabledDate").asText());
        q.setAnsweredOnDate(node.path("AnsweredOnDate").asText());
        q.setQuestionText(node.path("QuestionText").asText());
        q.setAnswerByDate(node.path("AnswerByDate").asText());
        q.setPriorityRequest(node.path("PriorityRequest").asText());
        q.setMinisterTitle(node.path("MinisterTitle").asText());
        q.setMinisterAffiliationId(node.path("MinisterAffiliationId").asText());
        q.setMinisterPersonId(node.path("MinisterPersonId").asText());
        q.setDepartment(node.path("Department").asText());
        q.setDepartmentID(node.path("DepartmentID").asText());
        q.setAnswerPlainText(node.path("AnswerPlainText").asText());
        q.setAnswerOpenXml(node.path("AnswerOpenXml").asText());
        q.setAnswerHtml(node.path("AnswerHtml").asText());
    }
}
