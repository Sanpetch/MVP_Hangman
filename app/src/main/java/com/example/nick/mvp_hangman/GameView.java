package com.example.nick.mvp_hangman;

import java.util.List;

/**
 * Created by nick on 3/12/17.
 */

public interface GameView {


    void setTextViewHint(String word);
    void showAnswer(List<Integer> index);
    void showPerson(int index);
    void win();
    void gameOver();
}
