package com.example.langlang.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    int idTest;

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    String question;
    String A;
    String B;
    String C;
    String D;
    String correctAns;

    public Question(int idTest, String question, String a, String b, String c, String d, String correctAns) {
        this.idTest = idTest;
        this.question = question;
        A = a;
        B = b;
        C = c;
        D = d;
        this.correctAns = correctAns;
    }

    public Question() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
