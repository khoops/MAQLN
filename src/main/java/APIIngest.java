import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
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

                    q.setDocumentId(node.path("DocumentId").asText());
                    // ...
                    questionMap.put(key, q);

                    internalID++;
                    System.out.println("#" + internalID + " | " + q.documentId + " | ");
                } else {
                    //finds extant instance of Question at given key and updates it
                    Question extant = questionMap.get(key);

                    extant.setDocumentId(node.path("DocumentId").asText());
                    // ...

                    internalID++;
                    System.out.println("%" + internalID + " | " + extant.documentId + " | ");
                }
            }
        }
        return questionMap;
    }
}