package com.example.nick.mvp_hangman;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nick.mvp_hangman.databinding.LetterBinding;

/**
 * Created by nick on 3/12/17.
 */

public class LetterAdapter extends RecyclerView.Adapter<LetterAdapter.MyViewHolder> {

    String[] letters;
    private ItemClickCallback itemClickCallback;

    public LetterAdapter() {
        letters = new String[26];

        for (int i = 0; i < letters.length; i++) {
            letters[i] = "" + (char) (i + 'A');
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LetterBinding binding = LetterBinding.inflate(layoutInflater, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return letters.length;
    }

    public void setItemClickCallback(ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public interface ItemClickCallback {
        void onItemClick(String txt);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LetterBinding binding;
        private int position;

        MyViewHolder(LetterBinding dataBinding) {
            super(dataBinding.getRoot());
            this.binding = dataBinding;
        }

        void bind(int position) {
            this.position = position;
            binding.btnLetter.setText(letters[position]);
            binding.btnLetter.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnLetter) {
                v.setEnabled(false);
                binding.btnLetter.setEnabled(false);
                binding.btnLetter.setBackgroundResource(R.drawable.letter_down);
                binding.btnLetter.setTextColor(Color.BLACK);
                itemClickCallback.onItemClick(binding.btnLetter.getText().toString());
            }
        }
    }
}
