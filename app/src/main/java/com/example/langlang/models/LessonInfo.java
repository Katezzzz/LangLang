package com.example.langlang.models;

import android.os.Parcel;
import android.os.Parcelable;

public class LessonInfo implements Parcelable {

    int idSubject;

    int idCourse;
    int lessonNumber;
    String title;
    String text;
    String homework;
    String words;

    public LessonInfo() {
    }

    protected LessonInfo(Parcel in) {
        idSubject = in.readInt();
        idCourse = in.readInt();
        lessonNumber = in.readInt();
        title = in.readString();
        text = in.readString();
        homework = in.readString();
        words = in.readString();
    }

    public static final Creator<LessonInfo> CREATOR = new Creator<LessonInfo>() {
        @Override
        public LessonInfo createFromParcel(Parcel in) {
            return new LessonInfo(in);
        }

        @Override
        public LessonInfo[] newArray(int size) {
            return new LessonInfo[size];
        }
    };

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idSubject);
        dest.writeInt(idCourse);
        dest.writeInt(lessonNumber);
        dest.writeString(title);
        dest.writeString(text);
        dest.writeString(homework);
        dest.writeString(words);
    }
}
