import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;


public class Question {

    public int documentId;
    public String documentType;
    public String reference;
    public String tablerName;
    public String tablerTitle;
    public int tablerAffiliationId;
    public int tablerPersonId;
    public LocalDate tabledDate;
    public LocalDate answeredOnDate;
    public String questionText;
    public LocalDate answerByDate;
    public boolean priorityRequest;

    public String department;
    public String answerPlainText;
    public int departmentID;
    public String answerOpenXml;
    public String answerHtml;

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = SetterHelperMethods.parseInt("DocumentId", documentId);
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = SetterHelperMethods.requireText("DocumentType", documentType);
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = SetterHelperMethods.requireText("Reference", reference);
    }

    public String getTablerName() {
        return tablerName;
    }

    public void setTablerName(String tablerName) {
        this.tablerName = SetterHelperMethods.requireText("TablerName", tablerName);
    }

    public String getTablerTitle() {
        return tablerTitle;
    }

    public void setTablerTitle(String tablerTitle) {
        this.tablerTitle = SetterHelperMethods.requireText("TablerTitle", tablerTitle);
    }

    public int getTablerAffiliationId() {
        return tablerAffiliationId;
    }

    public void setTablerAffiliationId(String tablerAffiliationId) {
        this.tablerAffiliationId = SetterHelperMethods.parseInt("TablerAffiliationId", tablerAffiliationId);
    }

    public int getTablerPersonId() {
        return tablerPersonId;
    }

    public void setTablerPersonId(String tablerPersonId) {
        this.tablerPersonId = SetterHelperMethods.parseInt("TablerPersonId", tablerPersonId);
    }

    public LocalDate getTabledDate() {
        return tabledDate;
    }

    public void setTabledDate(String tabledDate) {
        this.tabledDate = SetterHelperMethods.parseDate("TabledDate", tabledDate);
    }

    public LocalDate getAnsweredOnDate() {
        return answeredOnDate;
    }

    // Unanswered store null for answeredOnDate
    public void setAnsweredOnDate(String answeredOnDate) {
        this.answeredOnDate = SetterHelperMethods.isMissing(answeredOnDate) ? null : parseDate("AnsweredOnDate", answeredOnDate);
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = SetterHelperMethods.requireText("QuestionText", questionText);
    }

    public LocalDate getAnswerByDate() {
        return answerByDate;
    }

    public void setAnswerByDate(String answerByDate) {
        this.answerByDate = SetterHelperMethods.parseDate("AnswerByDate", answerByDate);
    }

    public boolean isPriorityRequest() {
        return priorityRequest;
    }

    public void setPriorityRequest(String priorityRequest) {
        this.priorityRequest = SetterHelperMethods.parseBoolean("PriorityRequest", priorityRequest);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = SetterHelperMethods.requireText("Department", department);
    }

    public String getAnswerPlainText() {
        return answerPlainText;
    }

    // If unanswered, null. Also removes trailing whitespace with strip
    public void setAnswerPlainText(String answerPlainText) {
        this.answerPlainText = SetterHelperMethods.isMissing(answerPlainText) ? null : answerPlainText.strip();
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = SetterHelperMethods.parseInt("DepartmentID", departmentID);
    }

    public String getAnswerOpenXml() {
        return answerOpenXml;
    }

    // If unanswered, null
    public void setAnswerOpenXml(String answerOpenXml) {
        this.answerOpenXml = SetterHelperMethods.isMissing(answerOpenXml) ? null : answerOpenXml;
    }

    public String getAnswerHtml() {
        return answerHtml;
    }

    // If unanswered, null
    public void setAnswerHtml(String answerHtml) {
        this.answerHtml = SetterHelperMethods.isMissing(answerHtml) ? null : answerHtml;
    }
}

