public class Minister {
    Integer personId;
    int affiliationId;
    String title;

    public String ministerTitle;
    public int ministerAffiliationId;
    public int ministerPersonId;

    public String getMinisterTitle() {
        return ministerTitle;
    }

    public void setMinisterTitle(String ministerTitle) {
        this.ministerTitle = SetterHelperMethods.requireText("MinisterTitle", ministerTitle);
    }

    public int getMinisterAffiliationId() {
        return ministerAffiliationId;
    }

    public void setMinisterAffiliationId(String ministerAffiliationId) {
        this.ministerAffiliationId = SetterHelperMethods.parseInt("MinisterAffiliationId", ministerAffiliationId);
    }

    public int getMinisterPersonId() {
        return ministerPersonId;
    }

    public void setMinisterPersonId(String ministerPersonId) {
        this.ministerPersonId = SetterHelperMethods.isMissing(ministerPersonId) ? 0
                : SetterHelperMethods.parseInt("MinisterAffiliateionId", ministerPersonId);
    }
}