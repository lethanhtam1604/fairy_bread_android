package com.vocabularygame.manager;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

public class PlayGameManager {

    private static PlayGameManager playGameManager;
    private Context context;

    private int currentWord = 0;
    public ArrayList<String> allCurrentWordList;

    private PlayGameManager(Context context) {
        allCurrentWordList = new ArrayList<>();
        allCurrentWordList = ReadWordsManager.getWordsFromFile("Words.txt", context);
    }

    public static PlayGameManager getInstance(Context context) {
        if (playGameManager == null)
            playGameManager = new PlayGameManager(context);
        return playGameManager;
    }

    public String randomWord() {
        Random randomGenerator = new Random();
        currentWord = randomGenerator.nextInt(allCurrentWordList.size());
        return allCurrentWordList.get(currentWord);
    }

    public String playNext() {
        if (currentWord == allCurrentWordList.size() - 1)
            currentWord = 0;
        else
            currentWord += 1;
        return allCurrentWordList.get(currentWord);
    }

    public String playBack() {
        if (currentWord == 0)
            currentWord = allCurrentWordList.size() - 1;
        else
            currentWord -= 1;
        return allCurrentWordList.get(currentWord);
    }

    public ArrayList<String> search(String keyWord) {

        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < allCurrentWordList.size(); i++) {
            if (allCurrentWordList.get(i).toLowerCase().contains(keyWord.toLowerCase()))
                result.add(allCurrentWordList.get(i));
        }
        return result;
    }
}
