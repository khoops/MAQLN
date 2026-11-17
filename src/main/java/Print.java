import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Print {

    public static void printToJSON(Map<Integer, Question> questionMap) throws StreamWriteException, IOException {

        ObjectMapper mapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT); // "pretty printing" for leibility

        Path out = Path.of("output", "questions.json");
        out.getParent().toFile().mkdirs();

        mapper.writeValue(out.toFile(), questionMap);
    }
}