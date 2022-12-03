package com.nhi.english.Revise_PhuongDong;

public class Question_listen {
    String session;
    String id;
    String question;
    String answerA, answerB, answerC;
    String answer;

    public Question_listen(String session, String id, String question,
                           String answerA, String answerB, String answerC,
                           String answer) {
        this.session = session;
        this.id = id;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answer = answer;
    }
}
