package com.example.word_finder;

public class UserHelperClass {
    String words, meaning;

    public UserHelperClass(){

    }

    public UserHelperClass(String words, String meaning) {
        this.words = words;
        this.meaning = meaning;
    }


    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
