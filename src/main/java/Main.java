
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// import java.util.regex.Matcher;
// import java.util.regex.Pattern;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class Main {

    public static void main(String[] args) throws IOException {

        LocalDate endDate = LocalDate.now(); // returns today as the most recent day
        LocalDate startDate = LocalDate.now().minusDays(7); // minus one week (for testing)

        String API_URL = ("https://data.niassembly.gov.uk/questions.asmx/GetQuestionsForWrittenAnswer_TabledInRange_JSON?startDate="
                + startDate + "&endDate=" + endDate);

        URL url = new URL(API_URL);
        URLConnection connection = url.openConnection();

        ObjectMapper mapper = new ObjectMapper();
        List<Question> questions = new ArrayList<>();

        try (InputStream questionData = connection.getInputStream()) {
            JsonNode root = mapper.readTree(questionData); // determines the lowest level node in the schema
            JsonNode entries = root.path("QuestionsList").path("Question");

            for (JsonNode node : entries) {
                Question q = new Question();
                q.setDocumentId(node.path("DocumentId").asText());
                questions.add(q);

                System.out.println(" | " + q.documentId + " | ");
            }
            System.out.println("OK?*******************");
        }

    }
}
