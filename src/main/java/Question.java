import java.sql.Date;

/**
 * Question
 */
public class Question {

    public static int documentId;
    public static String documentType;
    public static String reference;
    public static String tablerName;
    public static String tablerTitle;
    public static int tablerAffiliationId;
    public static int tablerPersonId;
    public static Date tabledDate;
    public static String questionText;
    public static Date answerByDate;
    public static boolean priorityRequest;
    public static String ministerTitle;
    public static int ministerAffiliationId;
    public static int ministerPersonId;
    public static String department;
    public static int departmentID;

    public static int getDocumentId() {
        return documentId;
    }

    public static void setDocumentId(int documentId) {
        Question.documentId = documentId;
    }

    public static String getDocumentType() {
        return documentType;
    }

    public static void setDocumentType(String documentType) {
        Question.documentType = documentType;
    }

    public static String getReference() {
        return reference;
    }

    public static void setReference(String reference) {
        Question.reference = reference;
    }

    public static String getTablerName() {
        return tablerName;
    }

    public static void setTablerName(String tablerName) {
        Question.tablerName = tablerName;
    }

    public static String getTablerTitle() {
        return tablerTitle;
    }

    public static void setTablerTitle(String tablerTitle) {
        Question.tablerTitle = tablerTitle;
    }

    public static int getTablerAffiliationId() {
        return tablerAffiliationId;
    }

    public static void setTablerAffiliationId(int tablerAffiliationId) {
        Question.tablerAffiliationId = tablerAffiliationId;
    }

    public static int getTablerPersonId() {
        return tablerPersonId;
    }

    public static void setTablerPersonId(int tablerPersonId) {
        Question.tablerPersonId = tablerPersonId;
    }

    public static Date getTabledDate() {
        return tabledDate;
    }

    public static void setTabledDate(Date tabledDate) {
        Question.tabledDate = tabledDate;
    }

    public static String getQuestionText() {
        return questionText;
    }

    public static void setQuestionText(String questionText) {
        Question.questionText = questionText;
    }

    public static Date getAnswerByDate() {
        return answerByDate;
    }

    public static void setAnswerByDate(Date answerByDate) {
        Question.answerByDate = answerByDate;
    }

    public static boolean isPriorityRequest() {
        return priorityRequest;
    }

    public static void setPriorityRequest(boolean priorityRequest) {
        Question.priorityRequest = priorityRequest;
    }

    public static String getMinisterTitle() {
        return ministerTitle;
    }

    public static void setMinisterTitle(String ministerTitle) {
        Question.ministerTitle = ministerTitle;
    }

    public static int getMinisterAffiliationId() {
        return ministerAffiliationId;
    }

    public static void setMinisterAffiliationId(int ministerAffiliationId) {
        Question.ministerAffiliationId = ministerAffiliationId;
    }

    public static int getMinisterPersonId() {
        return ministerPersonId;
    }

    public static void setMinisterPersonId(int ministerPersonId) {
        Question.ministerPersonId = ministerPersonId;
    }

    public static String getDepartment() {
        return department;
    }

    public static void setDepartment(String department) {
        Question.department = department;
    }

    public static int getDepartmentID() {
        return departmentID;
    }

    public static void setDepartmentID(int departmentID) {
        Question.departmentID = departmentID;
    }

}
