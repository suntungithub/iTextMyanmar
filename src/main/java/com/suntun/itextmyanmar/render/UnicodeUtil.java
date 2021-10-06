
package com.suntun.itextmyanmar.render;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author SunTun
 */
public class UnicodeUtil {
    private static final String VIRAMA = Character.toString(UnicodeUtil.unicodeChar("0x1039"));
    private static Map<String, String> subConsonant;
    //private static Map<String, String> vowelAbove;
    private static Map<String, String> vowelBelow;
    private static Map<String, String> changeVowel;
    private static Map<String, String> kinzi;
    
    public static char unicodeChar(final String strInput) {
        return (char) (Integer.parseInt(strInput.substring(2, strInput.length()), 16));
    }

    private static String getSubConsonantKey(final char charValue) {
        return VIRAMA.concat(Character.toString(charValue));
    }

    private static String getKey(final char charValue) {
        return Character.toString(charValue);
    }
   
    public static String change(final String key) {
        if (changeVowel == null) {
            loadChangeVowel();
        }
        if (changeVowel.containsKey(key)) {
            return Character.toString(UnicodeUtil.unicodeChar(changeVowel.get(key)));
        }
        return key;
    }

    public static String getSubConsonant(final String key) {
        if (subConsonant == null) {
            loadConsonant();
        }
        if (subConsonant.containsKey(key)) {
            return Character.toString(UnicodeUtil.unicodeChar(subConsonant.get(key)));
        }
        return key;
    }
    public static String getKinzi(final String key) {
        if (kinzi == null) {
            loadKinzi();
        }
        if (kinzi.containsKey(key)) {
            return Character.toString(UnicodeUtil.unicodeChar(kinzi.get(key)));
        }
        return key;
    }
    /**
     * Load vowel below.
     */
    private static void loadVowelBelow() {
        vowelBelow = new LinkedHashMap<String, String>();
        vowelBelow.put(getKey(UnicodeUtil.unicodeChar("0x102F")), "0xE2F1");
        vowelBelow.put(getKey(UnicodeUtil.unicodeChar("0x1030")), "0xE2F2");
    }
        /**
     * Load subscription by Consonant.
     */
    private static void loadConsonant() {
        subConsonant = new LinkedHashMap<String, String>();
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1000")), "0xE000");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1001")), "0xE001");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1002")), "0xE002");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1003")), "0xE003");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1005")), "0xE005");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1006")), "0xE006");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1007")), "0xE007");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1008")), "0xE008");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x100C")), "0xE00C");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x100E")), "0xE00E");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x100F")), "0xE00F");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1010")), "0xE010");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1011")), "0xE011");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1012")), "0xE012");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1013")), "0xE013");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1014")), "0xE014");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1015")), "0xE015");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1016")), "0xE016");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1017")), "0xE017");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1018")), "0xE018");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x1019")), "0xE019");
        subConsonant.put(getSubConsonantKey(UnicodeUtil.unicodeChar("0x101C")), "0xE01C");
    }
   /**
     * Load change glyphs
     */
    private static void loadChangeVowel() {
        changeVowel = new LinkedHashMap<String, String>();
        //medial
        changeVowel.put(getKey(UnicodeUtil.unicodeChar("0x103C")), "0xE1B2");//double width medial ra
        changeVowel.put(getKey(UnicodeUtil.unicodeChar("0xE1B2")), "0xE1B7");//double width medial ra
        
        changeVowel.put(getKey(UnicodeUtil.unicodeChar("0x102F")), "0xE2F1");//long vowel u
        changeVowel.put(getKey(UnicodeUtil.unicodeChar("0x1030")), "0xE2F2");//long vowel u
        //Vowel Above
        changeVowel.put(getKey(UnicodeUtil.unicodeChar("0x102D")), "0xE391");
        changeVowel.put(getKey(UnicodeUtil.unicodeChar("0x102E")), "0xE392");
        changeVowel.put(getKey(UnicodeUtil.unicodeChar("0x1036")), "0xE393");
        //consonants
        changeVowel.put(getKey(UnicodeUtil.unicodeChar("0x101B")), "0xE108");
    }
    private static void loadKinzi(){
        kinzi = new LinkedHashMap<String, String>();
        kinzi.put(getKey(UnicodeUtil.unicodeChar("0x1039")), "0xE390");
        
    }
}
