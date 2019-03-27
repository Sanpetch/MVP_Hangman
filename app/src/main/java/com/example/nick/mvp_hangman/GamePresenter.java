package com.example.nick.mvp_hangman;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nick on 3/12/17.
 */

public class GamePresenter {

    private GameView gameView;
    private GameInteractor gameInteractor;

    public GamePresenter(GameView gameView,GameInteractor gameInteractor) {
        this.gameInteractor = gameInteractor;
        this.gameView = gameView;
    }

    public void getWord() {
        gameInteractor.getWord()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (gameView != null) {
                            gameView.setTextViewHint(s);
                        }
                    }
                });
    }

    public void checkMatch(String txt, final String word) {
        gameInteractor.game(txt, word)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Game>() {
                    @Override
                    public void accept(Game game) throws Exception {

                        if (game.isCorrect()) {
                            if (gameInteractor.isWin(word)) {
                                gameView.win();
                            }
                            gameView.showAnswer(game.getIndexAnswers());
                        } else {
                            if (gameInteractor.isLoose()) {
                                gameView.gameOver();
                            }
                            gameView.showPerson(game.getCountWrong());
                        }
                    }
                });
    }
}
