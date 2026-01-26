package org.zeki.racingxtreme.model;

public class Question {
    private int idQuestion;
    private String title;
    private Answer[] answers;

    public Question(){

    }

    public Question(String title, Answer[] answers) {
        this.title = title;
        this.answers = answers;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }
}
