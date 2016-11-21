package dev.game.kanji.entities;

/**
 * Created by Lam Ngo on 11/20/2016.
 */
public class Alphabet {
    public static String alphabets = "一いうえおかきくけこさしす";
    public static String getNextLetter(String currentLetter) {
        int index = alphabets.indexOf(currentLetter);
        if (index == alphabets.length() - 1) {
            return String.valueOf(alphabets.charAt(0));
        } else {
            return String.valueOf(alphabets.charAt(index + 1));
        }
    }
}
