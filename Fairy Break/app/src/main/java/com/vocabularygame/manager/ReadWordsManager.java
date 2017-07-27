package com.vocabularygame.manager;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadWordsManager {

    public static ArrayList<String> getWordsFromFile(String textFileName, Context context) {
        ArrayList<String> wordList = new ArrayList<>();
        AssetManager am = context.getAssets();
        try {
            InputStream in = am.open(textFileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;

            while ((line = reader.readLine()) != null) {
                wordList.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }

}
