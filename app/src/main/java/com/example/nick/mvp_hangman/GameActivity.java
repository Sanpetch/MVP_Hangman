package com.example.nick.mvp_hangman;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nick.mvp_hangman.databinding.ActivityGameBinding;

import java.util.List;

public class GameActivity extends AppCompatActivity implements LetterAdapter.ItemClickCallback, GameView {

    ActivityGameBinding binding;
    private ImageView[] bodyParts;
    private LetterAdapter letterAdapter;
    private TextView[] txtView;
    private GamePresenter gamePresenter;
    private String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game);

        gameStart();
    }

    private void gameStart() {
        gamePresenter = new GamePresenter(this,new GameInteractor());
        initAdapter();
        setHideBody();
        binding.lnChar.removeAllViews();
        gamePresenter.getWord();
    }

    public void setHideBody() {
        bodyParts = new ImageView[6];
        bodyParts[0] = binding.head;
        bodyParts[1] = binding.body;
        bodyParts[2] = binding.arm1;
        bodyParts[3] = binding.arm2;
        bodyParts[4] = binding.leg1;
        bodyParts[5] = binding.leg2;

        for (ImageView bodyPart : bodyParts) {
            bodyPart.setVisibility(View.INVISIBLE);
        }
    }

    private void initAdapter() {
        letterAdapter = new LetterAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        binding.recycler.setLayoutManager(gridLayoutManager);
        binding.recycler.setItemAnimator(new DefaultItemAnimator());
        binding.recycler.setAdapter(letterAdapter);
        letterAdapter.setItemClickCallback(this);
    }

    @Override
    public void onItemClick(String txt) {
        gamePresenter.checkMatch(txt, word);
    }

    @Override
    public void setTextViewHint(String word) {
        this.word = word;

        txtView = new TextView[word.length()];

        for (int i = 0; i < word.length(); i++) {
            txtView[i] = new TextView(this);
            txtView[i].setText("" + word.charAt(i));
            txtView[i].setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT));
            txtView[i].setGravity(Gravity.CENTER);
            txtView[i].setTextColor(Color.WHITE);
            txtView[i].setBackgroundResource(R.drawable.letter_bg);

            binding.lnChar.addView(txtView[i]);
        }
    }

    @Override
    public void showAnswer(List<Integer> index) {
        for (int i = 0; i < index.size(); i++) {
            txtView[index.get(i)].setTextColor(Color.BLACK);
        }
    }

    @Override
    public void showPerson(int index) {
        bodyParts[index].setVisibility(View.VISIBLE);
    }

    @Override
    public void win() {
        showAlertDialog(getString(R.string.youWin), getString(R.string.congratulation));
    }

    @Override
    public void gameOver() {
        showAlertDialog(getString(R.string.gameOver), getString(R.string.answer_is) + word);
    }

    public void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.playAgain, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameStart();
            }
        });
        builder.setNeutralButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}
