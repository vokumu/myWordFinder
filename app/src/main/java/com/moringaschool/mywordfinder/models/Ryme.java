 package com.moringaschool.mywordfinder.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ryme {

    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("numSyllables")
    @Expose
    private Integer numSyllables;

    /**
     * No args constructor for use in serialization
     *
     */
    public Ryme() {
    }

    /**
     *
     * @param score
     * @param numSyllables
     * @param word
     */
    public Ryme(String word, Integer score, Integer numSyllables) {
        super();
        this.word = word;
        this.score = score;
        this.numSyllables = numSyllables;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getNumSyllables() {
        return numSyllables;
    }

    public void setNumSyllables(Integer numSyllables) {
        this.numSyllables = numSyllables;
    }

}