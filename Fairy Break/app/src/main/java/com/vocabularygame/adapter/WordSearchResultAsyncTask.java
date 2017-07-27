package com.vocabularygame.adapter;

import android.os.AsyncTask;

import java.util.ArrayList;

public class WordSearchResultAsyncTask extends AsyncTask<Void, String, Void> {

    private WordSearchResultListViewAdapter wordSearchResultListView;
    private ArrayList<String> words;

    public WordSearchResultAsyncTask(WordSearchResultListViewAdapter wordSearchResultListView, ArrayList<String> words) {
        this.wordSearchResultListView = wordSearchResultListView;
        this.words = words;
    }

    @Override
    protected void onPreExecute() {
        wordSearchResultListView.clear();
        wordSearchResultListView.notifyDataSetChanged();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {

        for (int i = 0; i < words.size(); i++) {
            publishProgress(words.get(i));
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        wordSearchResultListView.addWord(values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void result) {
        wordSearchResultListView.notifyDataSetChanged();
        super.onPostExecute(result);
    }
}
