package it.univaq.disim.model;

import java.util.Arrays;

public class QuestionModel {

    private Integer id;
    private Integer mandatory;
    private Integer number;
    private String type;
    private String[] answers;
    private String question;
    private String note;
    private Integer surveyId;

    public QuestionModel(Integer id, Integer mandatory, Integer number, String type,String[] answers, String question, String note, Integer surveyId) {

        this.id = id;
        this.mandatory = mandatory;
        this.number = number;
        this.note = note;
        this.surveyId = surveyId;
        this.type = type;
        this.answers = answers;
        this.question = question;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMandatory() {
        return mandatory;
    }

    public void setMandatory(Integer mandatory) {
        this.mandatory = mandatory;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getNote() {
        return note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    @Override
    public String toString() {
        return "QuestionModel{" +
                "id=" + id +
                ", mandatory=" + mandatory +
                ", number=" + number +
                ", type='" + type + '\'' +
                ", answers=" + Arrays.toString(answers) +
                ", question='" + question + '\'' +
                ", note='" + note + '\'' +
                ", surveyId=" + surveyId +
                '}';
    }

}
