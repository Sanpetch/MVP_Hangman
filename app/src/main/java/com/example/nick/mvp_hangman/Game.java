package com.example.nick.mvp_hangman;

import java.util.List;

/**
 * Created by nick on 3/15/17.
 */

public class Game {

    private boolean isCorrect;
    private List<Integer> indexAnswers;
    private int countWrong;

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public List<Integer> getIndexAnswers() {
        return indexAnswers;
    }

    public void setIndexAnswers(List<Integer> indexAnswers) {
        this.indexAnswers = indexAnswers;
    }

    public int getCountWrong() {
        return countWrong;
    }

    public void setCountWrong(int countWrong) {
        this.countWrong = countWrong;
    }
}
