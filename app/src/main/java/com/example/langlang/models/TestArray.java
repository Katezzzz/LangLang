package com.example.langlang.models;

import java.util.ArrayList;

public class TestArray {

    int idTest;
    ArrayList<Question> questions;

    public TestArray() {
    }

    public TestArray(int idTest, ArrayList<Question> questions) {
        this.idTest = idTest;
        this.questions = questions;
    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
