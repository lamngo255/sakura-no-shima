package com.game.Model;

/**
 * Created by MinhQ on 11/4/2016.
 */

public class Alphabet {
    private String japansese;
    private String latin;
    private int alpNum;



    public void Alphabet() {
    }
    public Alphabet(String latin, String japansese, int alpNum) {
        this.japansese=japansese;
        this.latin=latin;
        this.alpNum=alpNum;
    }

    public void setAlpNum(int alpNum) {
        this.alpNum = alpNum;
    }

    public void setJapansese(String japansese) {
        this.japansese = japansese;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    public int getAlpNum() {
        return alpNum;
    }

    public String getJapansese() {
        return japansese;
    }

    public String getLatin() {
        return latin;
    }
}
