package com.vocabularygame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vocabularygame.R;

import java.util.ArrayList;

public class WordSearchResultListViewAdapter extends BaseAdapter {

    private ArrayList<String> words;
    private Context context;

    public WordSearchResultListViewAdapter(Context context) {
        this.context = context;
        words = new ArrayList<>();
    }

    public void addWord(String word) {
        this.words.add(word);
    }

    @Override
    public Object getItem(int position) {
        return words.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return words.size();
    }

    public void clear() {
        words.clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String word = words.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_word, parent, false);
        }

        TextView wordTV = (TextView) convertView.findViewById(R.id.wordTV);
        wordTV.setText(word);

        return convertView;
    }
}