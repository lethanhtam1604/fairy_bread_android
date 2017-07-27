package com.vocabularygame.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.Toast;

import com.mikepenz.iconics.view.IconicsImageView;
import com.vocabularygame.R;
import com.vocabularygame.adapter.WordSearchResultAsyncTask;
import com.vocabularygame.adapter.WordSearchResultListViewAdapter;
import com.vocabularygame.manager.SoundManager;

import java.util.ArrayList;

public class SearchWordDialog  extends Dialog {
    private Context context;
    private IconicsImageView closeBtn;
    private ListView wordResultListView;
    private SearchView search_view;
    private WordSearchResultAsyncTask wordSearchResultAsyncTask;
    private WordSearchResultListViewAdapter wordSearchResultListViewAdapter;

    private WebView webView;

    public SearchWordDialog(final Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_search_word);
        this.context = context;
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        webView = (WebView) findViewById(R.id.webView);

//        Window window = getWindow();
//        window.setLayout(6, RelativeLayout.LayoutParams.WRAP_CONTENT);

        closeBtn = (IconicsImageView) findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playAudio(context, "click.wav");
                hide();
            }
        });

        closeBtn = (IconicsImageView) findViewById(R.id.closeBtn);
//        wordResultListView = (ListView) findViewById(R.id.wordResultListView);
//        search_view= (SearchView) findViewById(R.id.search_view);
//        search_view.setOnQueryTextListener( new SearchView.OnQueryTextListener( ) {
//            @Override
//            public boolean onQueryTextChange(String newText ) {
//                loadWordList(PlayGameManager.getInstance(context).search(newText));
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//        });
//
//        loadWordList(PlayGameManager.getInstance(context).allCurrentWordList);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getContext(), description, Toast.LENGTH_SHORT).show();
            }
        });
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.loadUrl("http://www.google.com");
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    private void loadWordList(ArrayList<String> words) {
        wordSearchResultListViewAdapter = new WordSearchResultListViewAdapter(context);
        wordResultListView.setAdapter(wordSearchResultListViewAdapter);
        WordSearchResultAsyncTask wordSearchResultAsyncTask = new WordSearchResultAsyncTask(wordSearchResultListViewAdapter, words);
        wordSearchResultAsyncTask.execute();
    }

    public void show_Dialog() {
        show();
    }

}

