import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

        try(InputStream questionData = connection.getInputStream()){
            JsonNode root = mapper.readTree(questionData); //determines the lowest level node in the schema
            JsonNode entries = root.path("QuestionsList").path("Question");
        
            for (JsonNode node : entries) {
                Question q = new Question();
                q.setDocumentId(node.path("DocumentId").asText());
                questions.add(q);

                System.out.println(" | " +q.documentId +" | ");
            } System.out.println("OK?*******************");
        }

        // BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
        // String line = null;
        // while ((line = br.readLine()) != null) {
            // class builder goes here
            // Question q = new Question();
            // Pattern p = Pattern.compile("\"[^\"]*\":\"(.*?)\"(?=,\"|$)");
            // Matcher m = p.matcher(line);
            // List<String> values = new ArrayList<>();
            // while (m.find()) {
            //     values.add(m.group(1));
            // }

            // q.setDocumentId(values.get(0));

            //             // call printer
            // System.out.println(" | " +q.documentId +" | "+ q.documentType +" | "+ q.reference +" | "+ q.tablerName
            //         +" | "+ q.tablerTitle +" | "+ q.tablerAffiliationId +" | "+ q.tablerPersonId
            //         +" | "+ q.tabledDate +" | "+ q.questionText +" | "+ q.answerByDate +" | "+ q.priorityRequest
            //         +" | "+ q.ministerTitle +" | "+ q.ministerAffiliationId +" | "+ q.ministerPersonId
            //         +" | "+ q.department +" | "+ q.departmentID +" | "+ q.documentId +" | "+ q.documentType
            //         +" | "+ q.reference);
        // }
        
        // br.close();

    }}
