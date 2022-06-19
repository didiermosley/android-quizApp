package com.example.proyectoii;

public class ModeloPregunta {
    private String question, rb, rb2, rb3, rb4;
    private int correctas;

    public ModeloPregunta(String question, String rb, String rb2, String rb3, String rb4, int correctas) {
        this.question = question;
        this.rb = rb;
        this.rb2 = rb2;
        this.rb3 = rb3;
        this.rb4 = rb4;
        this.correctas = correctas;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRb() {
        return rb;
    }

    public void setRb(String rb) {
        this.rb = rb;
    }

    public String getRb2() {
        return rb2;
    }

    public void setRb2(String rb2) {
        this.rb2 = rb2;
    }

    public String getRb3() {
        return rb3;
    }

    public void setRb3(String rb3) {
        this.rb3 = rb3;
    }

    public String getRb4() {
        return rb4;
    }

    public void setRb4(String rb4) {
        this.rb4 = rb4;
    }

    public int getCorrectas() {
        return correctas;
    }

    public void setCorrectas(int correctas) {
        this.correctas = correctas;
    }
}
