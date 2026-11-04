import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {

    public static void Main(String[] args) throws IOException {
        LocalDate endDate = LocalDate.now(); // returns today as the most recent day
        LocalDate startDate = LocalDate.now().minusDays(7); // minus one week (for testing)

        String API_URL = ("https://data.niassembly.gov.uk/questions.asmx/GetQuestionsForWrittenAnswer_TabledInRange_JSON?startDate="
                + startDate + "&endDate=" + endDate);

        URL url = new URL(API_URL);
        URLConnection urlc = url.openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
        String line = null;
        while ((line = br.readLine()) != null) {
            // class builder goes here
            Pattern p = Pattern.compile("\"[^\"]*\":\"(.*?)\"(?=,\"|$)");
            Matcher m = p.matcher(line);
            List<String> values = new ArrayList<>();
            while (m.find()) {
                values.add(m.group(1));
            }

            Question.setDocumentId(values.indexOf(0));

            // call printer
            System.out.println(Question.documentId + Question.documentType + Question.reference + Question.tablerName
                    + Question.tablerTitle + Question.tablerAffiliationId + Question.tablerPersonId
                    + Question.tabledDate + Question.questionText + Question.answerByDate + Question.priorityRequest
                    + Question.ministerTitle + Question.ministerAffiliationId + Question.ministerPersonId
                    + Question.department + Question.departmentID + Question.documentId + Question.documentType
                    + Question.reference);
        }
        br.close();

    }

}
