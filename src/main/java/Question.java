import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

/**
 * Question
 */
public class Question {

    public int documentId;
    public String documentType;
    public String reference;
    public String tablerName;
    public String tablerTitle;
    public int tablerAffiliationId;
    public int tablerPersonId;
    public LocalDate tabledDate;
    public String questionText;
    public LocalDate answerByDate;
    public boolean priorityRequest;
    public String ministerTitle;
    public int ministerAffiliationId;
    public int ministerPersonId;
    public String department;
    public int departmentID;

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = parseInt("DocumentId", documentId);
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = requireText("DocumentType", documentType);
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = requireText("Reference", reference);
    }

    public String getTablerName() {
        return tablerName;
    }

    public void setTablerName(String tablerName) {
        this.tablerName = requireText("TablerName", tablerName);
    }

    public String getTablerTitle() {
        return tablerTitle;
    }

    public void setTablerTitle(String tablerTitle) {
        this.tablerTitle = requireText("TablerTitle", tablerTitle);
    }

    public int getTablerAffiliationId() {
        return tablerAffiliationId;
    }

    public void setTablerAffiliationId(String tablerAffiliationId) {
        this.tablerAffiliationId = parseInt("TablerAffiliationId", tablerAffiliationId);
    }

    public int getTablerPersonId() {
        return tablerPersonId;
    }

    public void setTablerPersonId(String tablerPersonId) {
        this.tablerPersonId = parseInt("TablerPersonId", tablerPersonId);
    }

    public LocalDate getTabledDate() {
        return tabledDate;
    }

    public void setTabledDate(String tabledDate) {
        this.tabledDate = parseDate("TabledDate", tabledDate);
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = requireText("QuestionText", questionText);
    }

    public LocalDate getAnswerByDate() {
        return answerByDate;
    }

    public void setAnswerByDate(String answerByDate) {
        this.answerByDate = parseDate("AnswerByDate", answerByDate);
    }

    public boolean isPriorityRequest() {
        return priorityRequest;
    }

    public void setPriorityRequest(String priorityRequest) {
        this.priorityRequest = parseBoolean("PriorityRequest", priorityRequest);
    }

    public String getMinisterTitle() {
        return ministerTitle;
    }

    public void setMinisterTitle(String ministerTitle) {
        this.ministerTitle = requireText("MinisterTitle", ministerTitle);
    }

    public int getMinisterAffiliationId() {
        return ministerAffiliationId;
    }

    public void setMinisterAffiliationId(String ministerAffiliationId) {
        this.ministerAffiliationId = parseInt("MinisterAffiliationId", ministerAffiliationId);
    }

    public int getMinisterPersonId() {
        return ministerPersonId;
    }

    public void setMinisterPersonId(String ministerPersonId) {
        this.ministerPersonId = parseInt("MinisterPersonId", ministerPersonId);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = requireText("Department", department);
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = parseInt("DepartmentID", departmentID);
    }

    // IllegalArgumentException for the setters
    // Field and value are named if bad for tracing

    private static String requireText(String field, String value) {
        if (value == null) {
            throw new IllegalArgumentException(field + " missing");
        }
        return value;
    }

    private static int parseInt(String field, String value) {
        requireText(field, value);
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(field + " is not a whole number: \"" + value + "\"", e);
        }
    }

    // Trims API's YYYY-MM-DDT00:00:00+00:00 formatting to keep only YYYY-MM-DD
    private static LocalDate parseDate(String field, String value) {
        requireText(field, value);
        try {
            return OffsetDateTime.parse(value.trim()).toLocalDate();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(field + " is not a valid date: \"" + value + "\"", e);
        }
    }

    private static boolean parseBoolean(String field, String value) {
        String v = requireText(field, value).trim().toLowerCase();
        if (!v.equals("true") && !v.equals("false")) {
            throw new IllegalArgumentException(field + " is not true/false: \"" + value + "\"");
        }
        return Boolean.parseBoolean(v);
    }

}
