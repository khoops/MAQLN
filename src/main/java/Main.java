
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        LocalDate TabledEndDate = LocalDate.now(); // returns today as the most recent day
        LocalDate TabledStartDate = LocalDate.now().minusDays(20); // minus n days (for testing)

        LocalDate AnsweredEndDate = LocalDate.now(); // returns today as the most recent day
        LocalDate AnsweredStartDate = LocalDate.now().minusDays(20); // minus n days (for testing)

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

        System.out.println("OK?*******************");
    }

}
