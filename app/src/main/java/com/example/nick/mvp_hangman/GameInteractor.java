package com.example.nick.mvp_hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;

/**
 * Created by nick on 3/12/17.
 */

public class GameInteractor {

    private int count = 0;
    private int correct = 0;

    public GameInteractor(){
        count = 0;
        correct = 0;
    }

    public Observable<String> getWord() {
        return Observable.just(getRandomWord());
    }


    public Observable<Game> game(String ans, String word) {
        return Observable.just(gameCollection(ans,word));
    }



    public String getRandomWord() {
        List<String> words = new ArrayList<>();
        words.add("Disney");
        words.add("RasberryPi");
        words.add("Android");
        words.add("Pinklocy");
        words.add("FookPucy");
        words.add("Animation");
        words.add("Hippo");
        Random random = new Random();
        return words.get(random.nextInt(words.size()));

    }


    public Game gameCollection(String answer,String word){
        Game game = new Game();
        List<Integer> lsWord = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            if(answer.toLowerCase().charAt(0) == word.toLowerCase().charAt(i)){
                lsWord.add(i);
            }
        }

        if(lsWord.isEmpty()) {
            game.setCorrect(false);
            game.setCountWrong(count);
            count++;
        }else{
            game.setCorrect(true);
            correct = lsWord.size()+correct;
            game.setIndexAnswers(lsWord);
        }
        return game;
    }


    public Boolean isWin(String word){
        return correct == word.length();
    }

    public Boolean isLoose(){
        return count == 6;
    }





}
