
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        //Start of the 7th assembly
        LocalDate assemblyStart = LocalDate.of(2022, 5, 1);

        LocalDate TabledEndDate = LocalDate.now(); // returns today as the most recent day
        LocalDate TabledStartDate = LocalDate.now().minusDays(2); // minus n days (for testing)

        LocalDate AnsweredEndDate = LocalDate.now(); // returns today as the most recent day
        LocalDate AnsweredStartDate = LocalDate.now().minusDays(200); // minus n days (for testing)

        Map<Integer, Question> questionMap = new HashMap<>();

        String TabledApi = ("https://data.niassembly.gov.uk/questions.asmx/GetQuestionsForWrittenAnswer_TabledInRange_JSON?startDate="
                + TabledStartDate + "&endDate=" + TabledEndDate);
        URL TabledUrl = new URL(TabledApi);
        APIIngest.fetchQuestionDetails(questionMap, TabledUrl);
        System.out.println(TabledApi);

        String AnsweredApi = ("https://data.niassembly.gov.uk/questions.asmx/GetQuestionsForWrittenAnswer_AnsweredInRange_JSON?startDate="
                + AnsweredStartDate + "&endDate=" + AnsweredEndDate);
        URL AnsweredUrl = new URL(AnsweredApi);
        APIIngest.fetchQuestionDetails(questionMap, AnsweredUrl);
        System.out.println(AnsweredApi);

        Print.printToJSON(questionMap);

        System.out.println("OK?*******************");
    }

}
