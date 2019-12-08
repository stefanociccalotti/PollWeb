package it.univaq.disim.model;

public class SurveyModel {

    private Integer id;
    private String url;
    private String privacy;
    private String status;
    private String title;
    private String opening;
    private String closing;
    private String statusCss;
    private Integer user;

    public SurveyModel() {}

    public SurveyModel(Integer id, String url, String privacy, String status, String title, String opening, String closing, Integer user, String page) {
        this.id = id;
        this.url = url;
        this.privacy = privacy;
        this.status = status;
        this.title = title;
        this.opening = opening;
        this.closing = closing;
        this.user = user;

        if(page.equals("home")) setStatusCss(status);
        else setStatusCss(status,privacy);

    }

    public String getStatusCss() {
        return statusCss;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    private void setStatusCss(String status) {

        switch(status) {
            case "pubblicato":
                this.statusCss = "success";
                break;
            case "chiuso":
                this.statusCss = "danger";
                break;
            default:
                this.statusCss = "info";
        }

    }

    private void setStatusCss(String status, String privacy) {

        String statusPrivacy = status + "-" + privacy;

        switch(statusPrivacy) {
            case "pubblicato-pubblico":
                this.statusCss = "success";
                break;
            case "chiuso-pubblico":
                this.statusCss = "danger";
                break;
            case "pubblicato-riservato":
                this.statusCss = "primary";
                break;
            case "chiuso-riservato":
                this.statusCss = "danger";
                break;
            default:
                this.statusCss = "active";
        }

    }
}
