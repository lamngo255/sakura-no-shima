package dev.freaking.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by MinhQ on HIRAGANAHIRAGANA/4/KATAKANA0HIRAGANA6.
 */

public class Alphabet {
    public static final int HIRAGANA = 0;
    public static final int KATAKANA = 1;
    public static final int MIX = 2;
    private String japanese;
    private String latin;
    private int type;
    
    public static ArrayList<Alphabet> alphabets = new ArrayList<>();
    public static void initAlphabet() {
        alphabets.add(new Alphabet("a", "あ", HIRAGANA));
        alphabets.add(new Alphabet("i", "い", HIRAGANA));
        alphabets.add(new Alphabet("u", "う", HIRAGANA));
        alphabets.add(new Alphabet("e", "え", HIRAGANA));
        alphabets.add(new Alphabet("o", "お", HIRAGANA));
        alphabets.add(new Alphabet("ka", "か", HIRAGANA));
        alphabets.add(new Alphabet("ki", "き", HIRAGANA));
        alphabets.add(new Alphabet("ku", "く", HIRAGANA));
        alphabets.add(new Alphabet("ke", "け", HIRAGANA));
        alphabets.add(new Alphabet("ko", "こ", HIRAGANA));
        alphabets.add(new Alphabet("sa", "さ", HIRAGANA));
        alphabets.add(new Alphabet("shi", "し", HIRAGANA));
        alphabets.add(new Alphabet("su", "す", HIRAGANA));
        alphabets.add(new Alphabet("se", "せ", HIRAGANA));
        alphabets.add(new Alphabet("so", "そ", HIRAGANA));
        alphabets.add(new Alphabet("ta", "た", HIRAGANA));
        alphabets.add(new Alphabet("chi", "ち", HIRAGANA));
        alphabets.add(new Alphabet("tsu", "つ", HIRAGANA));
        alphabets.add(new Alphabet("te", "て", HIRAGANA));
        alphabets.add(new Alphabet("to", "と", HIRAGANA));
        alphabets.add(new Alphabet("na", "な", HIRAGANA));
        alphabets.add(new Alphabet("ni", "に", HIRAGANA));
        alphabets.add(new Alphabet("nu", "ぬ", HIRAGANA));
        alphabets.add(new Alphabet("ne", "ね", HIRAGANA));
        alphabets.add(new Alphabet("no", "の", HIRAGANA));
        alphabets.add(new Alphabet("ha", "は", HIRAGANA));
        alphabets.add(new Alphabet("hi", "ひ", HIRAGANA));
        alphabets.add(new Alphabet("fu", "ふ", HIRAGANA));
        alphabets.add(new Alphabet("he", "へ", HIRAGANA));
        alphabets.add(new Alphabet("ho", "ほ", HIRAGANA));
        alphabets.add(new Alphabet("ma", "ま", HIRAGANA));
        alphabets.add(new Alphabet("mi", "み", HIRAGANA));
        alphabets.add(new Alphabet("mu", "む", HIRAGANA));
        alphabets.add(new Alphabet("me", "め", HIRAGANA));
        alphabets.add(new Alphabet("mo", "も", HIRAGANA));
        alphabets.add(new Alphabet("ya", "や", HIRAGANA));
        alphabets.add(new Alphabet("yu", "ゆ", HIRAGANA));
        alphabets.add(new Alphabet("yo", "よ", HIRAGANA));
        alphabets.add(new Alphabet("ra", "ら", HIRAGANA));
        alphabets.add(new Alphabet("ri", "り", HIRAGANA));
        alphabets.add(new Alphabet("ru", "る", HIRAGANA));
        alphabets.add(new Alphabet("re", "れ", HIRAGANA));
        alphabets.add(new Alphabet("ro", "ろ", HIRAGANA));
        alphabets.add(new Alphabet("wa", "わ", HIRAGANA));
        alphabets.add(new Alphabet("n", "ん", HIRAGANA));
        alphabets.add(new Alphabet("ga", "が", HIRAGANA));
        alphabets.add(new Alphabet("gi", "ぎ", HIRAGANA));
        alphabets.add(new Alphabet("gu", "ぐ", HIRAGANA));
        alphabets.add(new Alphabet("ge", "げ", HIRAGANA));
        alphabets.add(new Alphabet("go", "ご", HIRAGANA));
        alphabets.add(new Alphabet("za", "ざ", HIRAGANA));
        alphabets.add(new Alphabet("ji", "じ", HIRAGANA));
        alphabets.add(new Alphabet("zu", "ず", HIRAGANA));
        alphabets.add(new Alphabet("ze", "ぜ", HIRAGANA));
        alphabets.add(new Alphabet("zo", "ぞ", HIRAGANA));
        alphabets.add(new Alphabet("da", "だ", HIRAGANA));
        alphabets.add(new Alphabet("ji", "ぢ", HIRAGANA));
        alphabets.add(new Alphabet("zu", "づ", HIRAGANA));
        alphabets.add(new Alphabet("de", "で", HIRAGANA));
        alphabets.add(new Alphabet("do", "ど", HIRAGANA));
        alphabets.add(new Alphabet("ba", "ば", HIRAGANA));
        alphabets.add(new Alphabet("bi", "び", HIRAGANA));
        alphabets.add(new Alphabet("bu", "ぶ", HIRAGANA));
        alphabets.add(new Alphabet("be", "べ", HIRAGANA));
        alphabets.add(new Alphabet("bo", "ぼ", HIRAGANA));
        alphabets.add(new Alphabet("pa", "ぱ", HIRAGANA));
        alphabets.add(new Alphabet("pi", "ぴ", HIRAGANA));
        alphabets.add(new Alphabet("pu", "ぷ", HIRAGANA));
        alphabets.add(new Alphabet("pe", "ぺ", HIRAGANA));
        alphabets.add(new Alphabet("po", "ぽ", HIRAGANA));
        alphabets.add(new Alphabet("kya", "きゃ", HIRAGANA));
        alphabets.add(new Alphabet("kyu", "きゅ", HIRAGANA));
        alphabets.add(new Alphabet("kyo", "きょ", HIRAGANA));
        alphabets.add(new Alphabet("sha", "しゃ", HIRAGANA));
        alphabets.add(new Alphabet("shu", "しゅ", HIRAGANA));
        alphabets.add(new Alphabet("sho", "しょ", HIRAGANA));
        alphabets.add(new Alphabet("cha", "ちゃ", HIRAGANA));
        alphabets.add(new Alphabet("chu", "ちゅ", HIRAGANA));
        alphabets.add(new Alphabet("cho", "ちょ", HIRAGANA));
        alphabets.add(new Alphabet("nya", "にゃ", HIRAGANA));
        alphabets.add(new Alphabet("nyu", "にゅ", HIRAGANA));
        alphabets.add(new Alphabet("nyo", "にょ", HIRAGANA));
        alphabets.add(new Alphabet("hya", "ゃ", HIRAGANA));
        alphabets.add(new Alphabet("hyu", "ひゅ", HIRAGANA));
        alphabets.add(new Alphabet("hyo", "ひょ", HIRAGANA));
        alphabets.add(new Alphabet("mya", "みゃ", HIRAGANA));
        alphabets.add(new Alphabet("myu", "みゅ", HIRAGANA));
        alphabets.add(new Alphabet("myo", "みょ", HIRAGANA));
        alphabets.add(new Alphabet("rya", "りゃ", HIRAGANA));
        alphabets.add(new Alphabet("ryu", "りゅ", HIRAGANA));
        alphabets.add(new Alphabet("ryo", "りょ", HIRAGANA));
        alphabets.add(new Alphabet("gya", "ぎゃ", HIRAGANA));
        alphabets.add(new Alphabet("gyu", "ぎゅ", HIRAGANA));
        alphabets.add(new Alphabet("gyo", "ぎょ", HIRAGANA));
        alphabets.add(new Alphabet("ja", "じゃ", HIRAGANA));
        alphabets.add(new Alphabet("ju", "じゅ", HIRAGANA));
        alphabets.add(new Alphabet("jo", "じょ", HIRAGANA));
        alphabets.add(new Alphabet("bya", "びゃ", HIRAGANA));
        alphabets.add(new Alphabet("byu", "びゅ", HIRAGANA));
        alphabets.add(new Alphabet("byo", "びょ", HIRAGANA));
        alphabets.add(new Alphabet("pya", "ぴゃ", HIRAGANA));
        alphabets.add(new Alphabet("pyu", "ぴゅ", HIRAGANA));
        alphabets.add(new Alphabet("pyo", "ぴょ", HIRAGANA));

        alphabets.add(new Alphabet("a", "ア", KATAKANA));
        alphabets.add(new Alphabet("i", "イ", KATAKANA));
        alphabets.add(new Alphabet("u", "ウ", KATAKANA));
        alphabets.add(new Alphabet("e", "エ", KATAKANA));
        alphabets.add(new Alphabet("o", "オ", KATAKANA));
        alphabets.add(new Alphabet("ka", "カ", KATAKANA));
        alphabets.add(new Alphabet("ki", "キ", KATAKANA));
        alphabets.add(new Alphabet("ku", "ク", KATAKANA));
        alphabets.add(new Alphabet("ke", "ケ", KATAKANA));
        alphabets.add(new Alphabet("ko", "コ", KATAKANA));
        alphabets.add(new Alphabet("sa", "サ", KATAKANA));
        alphabets.add(new Alphabet("shi", "シ", KATAKANA));
        alphabets.add(new Alphabet("su", "ス", KATAKANA));
        alphabets.add(new Alphabet("se", "セ", KATAKANA));
        alphabets.add(new Alphabet("so", "ソ", KATAKANA));
        alphabets.add(new Alphabet("ta", "タ", KATAKANA));
        alphabets.add(new Alphabet("chi", "チ", KATAKANA));
        alphabets.add(new Alphabet("tsu", "ツ", KATAKANA));
        alphabets.add(new Alphabet("te", "テ", KATAKANA));
        alphabets.add(new Alphabet("to", "ト", KATAKANA));
        alphabets.add(new Alphabet("na", "ナ", KATAKANA));
        alphabets.add(new Alphabet("ni", "ニ", KATAKANA));
        alphabets.add(new Alphabet("nu", "ヌ", KATAKANA));
        alphabets.add(new Alphabet("ne", "ネ", KATAKANA));
        alphabets.add(new Alphabet("no", "ノ", KATAKANA));
        alphabets.add(new Alphabet("ha", "ハ", KATAKANA));
        alphabets.add(new Alphabet("hi", "ヒ", KATAKANA));
        alphabets.add(new Alphabet("fu", "フ", KATAKANA));
        alphabets.add(new Alphabet("he", "ヘ", KATAKANA));
        alphabets.add(new Alphabet("ho", "ホ", KATAKANA));
        alphabets.add(new Alphabet("ma", "マ", KATAKANA));
        alphabets.add(new Alphabet("mi", "ミ", KATAKANA));
        alphabets.add(new Alphabet("mu", "ム", KATAKANA));
        alphabets.add(new Alphabet("me", "メ", KATAKANA));
        alphabets.add(new Alphabet("mo", "モ", KATAKANA));
        alphabets.add(new Alphabet("ya", "ヤ", KATAKANA));
        alphabets.add(new Alphabet("yu", "ユ", KATAKANA));
        alphabets.add(new Alphabet("yo", "ヨ", KATAKANA));
        alphabets.add(new Alphabet("ra", "ラ", KATAKANA));
        alphabets.add(new Alphabet("ri", "リ", KATAKANA));
        alphabets.add(new Alphabet("ru", "ル", KATAKANA));
        alphabets.add(new Alphabet("re", "レ", KATAKANA));
        alphabets.add(new Alphabet("ro", "ロ", KATAKANA));
        alphabets.add(new Alphabet("wa", "ワ", KATAKANA));
        alphabets.add(new Alphabet("n", "ｎ", KATAKANA));
        alphabets.add(new Alphabet("ga", "ガ", KATAKANA));
        alphabets.add(new Alphabet("gi", "ギ", KATAKANA));
        alphabets.add(new Alphabet("gu", "グ", KATAKANA));
        alphabets.add(new Alphabet("ge", "ゲ", KATAKANA));
        alphabets.add(new Alphabet("go", "ゴ", KATAKANA));
        alphabets.add(new Alphabet("za", "ザ", KATAKANA));
        alphabets.add(new Alphabet("ji", "ジ", KATAKANA));
        alphabets.add(new Alphabet("zu", "ズ", KATAKANA));
        alphabets.add(new Alphabet("ze", "ゼ", KATAKANA));
        alphabets.add(new Alphabet("zo", "ゾ", KATAKANA));
        alphabets.add(new Alphabet("da", "ダ", KATAKANA));
        alphabets.add(new Alphabet("ji", "ヂ", KATAKANA));
        alphabets.add(new Alphabet("zu", "ヅ", KATAKANA));
        alphabets.add(new Alphabet("de", "デ", KATAKANA));
        alphabets.add(new Alphabet("do", "ド", KATAKANA));
        alphabets.add(new Alphabet("ba", "バ", KATAKANA));
        alphabets.add(new Alphabet("bi", "ビ", KATAKANA));
        alphabets.add(new Alphabet("bu", "ブ", KATAKANA));
        alphabets.add(new Alphabet("be", "ベ", KATAKANA));
        alphabets.add(new Alphabet("bo", "ボ", KATAKANA));
        alphabets.add(new Alphabet("pa", "パ", KATAKANA));
        alphabets.add(new Alphabet("pi", "ピ", KATAKANA));
        alphabets.add(new Alphabet("pu", "プ", KATAKANA));
        alphabets.add(new Alphabet("pe", "ペ", KATAKANA));
        alphabets.add(new Alphabet("po", "ポ", KATAKANA));
        alphabets.add(new Alphabet("kya", "キャ", KATAKANA));
        alphabets.add(new Alphabet("kyu", "キュ", KATAKANA));
        alphabets.add(new Alphabet("kyo", "キョ", KATAKANA));
        alphabets.add(new Alphabet("sha", "シャ", KATAKANA));
        alphabets.add(new Alphabet("shu", "シュ", KATAKANA));
        alphabets.add(new Alphabet("sho", "ショ", KATAKANA));
        alphabets.add(new Alphabet("cha", "チャ", KATAKANA));
        alphabets.add(new Alphabet("chu", "チュ", KATAKANA));
        alphabets.add(new Alphabet("cho", "チョ", KATAKANA));
        alphabets.add(new Alphabet("nya", "ニャ", KATAKANA));
        alphabets.add(new Alphabet("nyu", "ニュ", KATAKANA));
        alphabets.add(new Alphabet("nyo", "ニョ", KATAKANA));
        alphabets.add(new Alphabet("hya", "ヒャ", KATAKANA));
        alphabets.add(new Alphabet("hyu", "ヒュ", KATAKANA));
        alphabets.add(new Alphabet("hyo", "ヒョ", KATAKANA));
        alphabets.add(new Alphabet("mya", "ミャ", KATAKANA));
        alphabets.add(new Alphabet("myu", "ミュ", KATAKANA));
        alphabets.add(new Alphabet("myo", "ミョ", KATAKANA));
        alphabets.add(new Alphabet("rya", "リャ", KATAKANA));
        alphabets.add(new Alphabet("ryu", "リュ", KATAKANA));
        alphabets.add(new Alphabet("ryo", "リョ", KATAKANA));
        alphabets.add(new Alphabet("gya", "ギャ", KATAKANA));
        alphabets.add(new Alphabet("gyu", "ギュ", KATAKANA));
        alphabets.add(new Alphabet("gyo", "ギョ", KATAKANA));
        alphabets.add(new Alphabet("ja", "ジャ", KATAKANA));
        alphabets.add(new Alphabet("ju", "ジュ", KATAKANA));
        alphabets.add(new Alphabet("jo", "ジョ", KATAKANA));
        alphabets.add(new Alphabet("bya", "ビャ", KATAKANA));
        alphabets.add(new Alphabet("byu", "ビュ", KATAKANA));
        alphabets.add(new Alphabet("byo", "ビョ", KATAKANA));
        alphabets.add(new Alphabet("pya", "ピャ", KATAKANA));
        alphabets.add(new Alphabet("pyu", "ピュ", KATAKANA));
        alphabets.add(new Alphabet("pyo", "ピョ", KATAKANA));
        alphabets.add(new Alphabet("she", "シェ", KATAKANA));
        alphabets.add(new Alphabet("che", "チェ", KATAKANA));
        alphabets.add(new Alphabet("je", "ジェ", KATAKANA));
        alphabets.add(new Alphabet("tsa", "ツァ", KATAKANA));
        alphabets.add(new Alphabet("tse", "ツェ", KATAKANA));
        alphabets.add(new Alphabet("tso", "ツォ", KATAKANA));
        alphabets.add(new Alphabet("fa", "ファ", KATAKANA));
        alphabets.add(new Alphabet("fi", "フィ", KATAKANA));
        alphabets.add(new Alphabet("fe", "フェ", KATAKANA));
        alphabets.add(new Alphabet("fo", "フォ", KATAKANA));
        alphabets.add(new Alphabet("ti", "チィ", KATAKANA));
        alphabets.add(new Alphabet("dhi", "ディ", KATAKANA));
        alphabets.add(new Alphabet("dyu", "ヂュ", KATAKANA));
        alphabets.add(new Alphabet("wi", "ウィ", KATAKANA));
        alphabets.add(new Alphabet("we", "ウェ", KATAKANA));
        alphabets.add(new Alphabet("wo", "ヲ", KATAKANA));
        alphabets.add(new Alphabet("quo", "クオ", KATAKANA));
        alphabets.add(new Alphabet("va", "ヴァ", KATAKANA));
        alphabets.add(new Alphabet("vi", "ヴィ", KATAKANA));
        alphabets.add(new Alphabet("ve", "ヴェ", KATAKANA));
        alphabets.add(new Alphabet("vo", "ヴォ", KATAKANA));
    }

    public static ArrayList<Alphabet> getAlphabet(int type) {
        if (type == MIX) {
            return alphabets;
        }
        ArrayList<Alphabet> resAlphabet = new ArrayList<>();
        for (Alphabet alpha : alphabets) {
            if (alpha.getType() == type) {
                resAlphabet.add(alpha);
            }
        }
        return resAlphabet;
    }
    
    public Alphabet(String latin, String japanese, int type) {
        this.japanese = japanese;
        this.latin = latin;
        this.type = type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setJapanese(String japanese) {
        this.japanese = japanese;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    public int getType() {
        return type;
    }

    public String getJapanese() {
        return japanese;
    }

    public String getLatin() {
        return latin;
    }
}
