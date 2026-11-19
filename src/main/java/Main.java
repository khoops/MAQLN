
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        LocalDate assemblyStartDate = LocalDate.of(2022, 5, 1); // Start of the 7th assembly

        LocalDate dateMinusDays = LocalDate.now().minusDays(0); // minus n days (for testing)

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

        while (dateMinusDays.isAfter(assemblyStartDate)) {
            String answeredApi = ("https://data.niassembly.gov.uk/questions.asmx/GetQuestionsForWrittenAnswer_AnsweredInRange_JSON?startDate="
                    + dateMinusDays.minusDays(30) + "&endDate=" + dateMinusDays);
            URL answeredUrl = new URL(answeredApi);
            APIIngest.fetchQuestionDetails(questionMap, answeredUrl);
            System.out.println(answeredApi);

            Print.printToJSON(questionMap);

            System.out.println("Dates ingested = " + dateMinusDays.minusDays(30) + " until " + dateMinusDays);

            dateMinusDays = dateMinusDays.minusDays(30);
        }

    }
}
