import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIIngest {

    public static List<Question> fetchQuestionDetails(URL apiUrl) throws IOException {

        URLConnection connection = apiUrl.openConnection();

        ObjectMapper mapper = new ObjectMapper();
        List<Question> questions = new ArrayList<>();

        try (InputStream questionData = connection.getInputStream()) {
            JsonNode root = mapper.readTree(questionData); // determines the lowest level node in the schema
            JsonNode entries = root.path("QuestionsList").path("Question");

            int internalID = 0;

            for (JsonNode node : entries) {
                internalID++;
                Question q = new Question();
                q.setDocumentId(node.path("DocumentId").asText());
                questions.add(q);

                System.out.println("#" + internalID + " | " + q.documentId + " | ");
            }
        }
        return questions;
    }
}