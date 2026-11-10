
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        LocalDate endDate = LocalDate.now(); // returns today as the most recent day
        LocalDate startDate = LocalDate.now().minusDays(1); // minus one day (for testing)

        Map<Integer, Question> questionMap = new HashMap<>();

        String TabledApi = ("https://data.niassembly.gov.uk/questions.asmx/GetQuestionsForWrittenAnswer_TabledInRange_JSON?startDate="
                + startDate + "&endDate=" + endDate);
        URL TabledUrl = new URL(TabledApi);
        APIIngest.fetchQuestionDetails(questionMap, TabledUrl);
        System.out.println(TabledApi);

        String AnsweredApi = ("https://data.niassembly.gov.uk/questions.asmx/GetQuestionsForWrittenAnswer_AnsweredInRange_JSON?startDate="
                + startDate + "&endDate=" + endDate);
        URL AnsweredUrl = new URL(AnsweredApi);
        APIIngest.fetchQuestionDetails(questionMap, AnsweredUrl);
        System.out.println(AnsweredApi);

        System.out.println("OK?*******************");
    }

}
