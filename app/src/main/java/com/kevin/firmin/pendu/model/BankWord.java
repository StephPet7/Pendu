package com.kevin.firmin.pendu.model;

import java.util.Collections;
import java.util.List;

public class BankWord {

    private List<String> mListOfWords;
    private String mMotCourant;

    //constructor

    public BankWord(List<String> mListOfWords){
        Collections.shuffle(mListOfWords);
        this.mListOfWords=mListOfWords;
        this.mMotCourant=mListOfWords.get(0);
    }

    //getters ans setters
    public List<String> getListOfWords() {
        return mListOfWords;
    }

    public void setListOfWords(List<String> mListOfWords) {
        this.mListOfWords = mListOfWords;
    }

    public String getMotCourant() {
        return mMotCourant;
    }

    public void setMotCourant(String motCourant) {
        this.mMotCourant = motCourant;
    }
}
