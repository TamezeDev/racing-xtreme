package org.zeki.racingxtreme.model;

public class Question {
    private String title;
    private Answer[] answers;

    public Question(){

    }

    public Question(String title, Answer[] answers) {
        this.title = title;
        this.answers = answers;
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
