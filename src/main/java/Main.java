
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        LocalDate assemblyStartDate = LocalDate.of(2022, 5, 1);         //Start of the 7th assembly
        LocalDate dateMinusDays = LocalDate.now().minusDays(2); // minus n days (for testing)

        LocalDate tabledEndDate = LocalDate.now(); // returns today as the most recent day
        LocalDate tabledStartDate = assemblyStartDate;

        LocalDate answeredEndDate = LocalDate.now(); // returns today as the most recent day
        LocalDate answeredStartDate = assemblyStartDate;

        Map<Integer, Question> questionMap = new HashMap<>();

        String tabledApi = ("https://data.niassembly.gov.uk/questions.asmx/GetQuestionsForWrittenAnswer_TabledInRange_JSON?startDate="
                + tabledStartDate + "&endDate=" + tabledEndDate);
        URL tabledUrl = new URL(tabledApi);
        APIIngest.fetchQuestionDetails(questionMap, tabledUrl);
        System.out.println(tabledApi);

        String answeredApi = ("https://data.niassembly.gov.uk/questions.asmx/GetQuestionsForWrittenAnswer_AnsweredInRange_JSON?startDate="
                + answeredStartDate + "&endDate=" + answeredEndDate);
        URL answeredUrl = new URL(answeredApi);
        APIIngest.fetchQuestionDetails(questionMap, answeredUrl);
        System.out.println(answeredApi);

        Print.printToJSON(questionMap);

        System.out.println("OK?*******************");
    }

}
