
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;


public class Main {

    public static void main(String[] args) throws IOException {

        LocalDate endDate = LocalDate.now(); // returns today as the most recent day
        LocalDate startDate = LocalDate.now().minusDays(7); // minus one week (for testing)

        String TabledApi = ("https://data.niassembly.gov.uk/questions.asmx/GetQuestionsDetailsJSON_TabledInRange_JSON?startDate="
                + startDate + "&endDate=" + endDate);
        URL TabledUrl = new URL(TabledApi);
        APIIngest.fetchQuestionDetails(TabledUrl);

                String AnsweredApi = ("https://data.niassembly.gov.uk/questions.asmx/GetQuestionsDetailsJSON_AnsweredInRange_JSON?startDate="
                + startDate + "&endDate=" + endDate);
        URL AnsweredUrl = new URL(AnsweredApi);
        APIIngest.fetchQuestionDetails(AnsweredUrl);

        System.out.println("OK?*******************");
    }

}
