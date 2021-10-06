/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suntun.itextmyanmar.render;


/**
 * UnicodeRender Class.
 * @author SunTun
 */
public class UnicodeRender {
private final int CC_RESERVED = 0;
  private final int CC_CONSONANT = 1; /* Consonant of type 1, that has conjunct form */
  private final int CC_CONSONANT2 = 2; /* Consonant of type 2, that has no conjunct form */
  private final int CC_NGA = 3; /* Consonant NGA */
  private final int CC_IND_VOWEL = 4; /* Independent vowel */
  private final int CC_MEDIAL_Y = 5; /* Medial YA */
  private final int CC_MEDIAL_R	= 6; /* Medial RA */
  private final int CC_MEDIAL_W	= 7; /* Medial WA */
  private final int CC_MEDIAL_H	= 8; /* Medial HA */
  private final int CC_VIRAMA  = 9; /* Subscript consonant combining character */
  private final int CC_ASAT  = 10; /* Explicit Asat */
  private final int CC_PRE_VOWEL = 11; /* Dependent vowel, prebase (Vowel e) */
  private final int CC_ABOVE_VOWEL = 12; /* Dependent vowel, abovebase (Vowel i, ii, ai) */
  private final int CC_BELOW_VOWEL = 13; /* Dependent vowel, belowbase (Vowel u, uu) */
  private final int CC_POST_VOWEL = 14; /* Dependent vowel, postbase (Vowel aa) */
  private final int CC_SIGN_ABOVE = 15;
  private final int CC_SIGN_BELOW = 16;
  private final int CC_SIGN_AFTER = 17;
    
  private final int CF_CLASS_MASK = 0x0000FFFF;

  private final int CF_CONSONANT = 0x01000000;  /* flag to speed up comparing */
  private final int CF_MEDIAL = 0x02000000;  /* flag to speed up comparing */
  private final int CF_IND_VOWEL = 0x04000000;  /* flag to speed up comparing */
  private final int CF_DEP_VOWEL = 0x08000000;  /* flag to speed up comparing */
  private final int CF_DOTTED_CIRCLE = 0x10000000;  /* add a dotted circle if a character with this flag is the first in a syllable */
  private final int CF_VIRAMA = 0x20000000;  /* flag to speed up comparing */
  private final int CF_ASAT = 0x40000000;  /* flag to speed up comparing */
  
  /* position flags */
  private final int CF_POS_BEFORE = 0x00080000;
  private final int CF_POS_BELOW = 0x00040000;
  private final int CF_POS_ABOVE = 0x00020000;
  private final int CF_POS_AFTER = 0x00010000;
 // private final int CF_POS_MASK = 0x000f0000;
  
  private final int _xx = CC_RESERVED;
  private final int _c1 = CC_CONSONANT + CF_CONSONANT;
  private final int _c2 = CC_CONSONANT2 + CF_CONSONANT;
  private final int _ng = CC_NGA + CF_CONSONANT;
  private final int _id = CC_IND_VOWEL + CF_IND_VOWEL;
  private final int _my = CC_MEDIAL_Y + CF_MEDIAL + CF_DOTTED_CIRCLE;
  private final int _mr = CC_MEDIAL_R + CF_MEDIAL + CF_DOTTED_CIRCLE;
  private final int _mw = CC_MEDIAL_W + CF_MEDIAL + CF_DOTTED_CIRCLE;
  private final int _mh = CC_MEDIAL_H + CF_MEDIAL + CF_DOTTED_CIRCLE;
  private final int _vi = CC_VIRAMA + CF_VIRAMA + CF_DOTTED_CIRCLE;
  private final int _va = CC_ASAT + CF_ASAT + CF_DOTTED_CIRCLE;
  private final int _dl = CC_PRE_VOWEL + CF_DEP_VOWEL + CF_POS_BEFORE + CF_DOTTED_CIRCLE;
  private final int _da = CC_ABOVE_VOWEL + CF_DEP_VOWEL + CF_POS_ABOVE + CF_DOTTED_CIRCLE;
  private final int _db = CC_BELOW_VOWEL + CF_DEP_VOWEL + CF_POS_BELOW + CF_DOTTED_CIRCLE;
  private final int _dr = CC_POST_VOWEL + CF_DEP_VOWEL + CF_POS_AFTER + CF_DOTTED_CIRCLE;
  private final int _sa = CC_SIGN_ABOVE + CF_DOTTED_CIRCLE + CF_POS_ABOVE;
  private final int _sb = CC_SIGN_BELOW + CF_DOTTED_CIRCLE + CF_POS_BELOW;
  private final int _sp = CC_SIGN_AFTER + CF_POS_AFTER + CF_DOTTED_CIRCLE;

  private String VIRAMA_DDA;
  private String VIRAMA_DDHA;
  private String VIRAMA_TTHA;
  private String MEDIAL_WAHA;
  private String MEDIAL_YAHA;
  private String MEDIAL_YAWA;
  private String MEDIAL_RAWA;

    private int[] mymrCharClasses = new int[] {
         _c1, _c1, _c1, _c1, _ng, _c1, _c1, _c1, _c1, _c1, _c2, _c1, _c1, _c1, _c1, _c1, /* 1000 - 100F */
         _c1, _c1, _c1, _c1, _c1, _c1, _c1, _c1, _c1, _c1, _c1, _c1, _c1, _c2, _c1, _c1, /* 1010 - 101F */
         _c2, _c2, _xx, _id, _id, _id, _id, _id, _xx, _id, _id, _dr, _dr, _da, _da, _db, /* 1020 - 102F */
         _db, _dl, _da, _xx, _xx, _xx, _sa, _sb, _sp, _vi, _va, _my, _mr, _mw, _mh, _c2, /* 1030 - 103F */
         _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _c2, _xx, /* 1040 - 104F */
         _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, /* 1050 - 105F */
    };
    
/**
 * Character class table
 *  _xx Character does not combine into syllable
 *  _c1 Consonant of type 1 that has subscript form
 *  _c2 Consonant of type 2 that has no subscript form 
 *  _ng Consonant nga
 *  _id Independent vowel
 *  _my Medial ya * medials are defined separately
 *  _mr Medial ra * to check the order.
 *  _mw Medial wa * Order is important here.
 *  _mh Medial ha *
 *  _vi Sign virama u1039
 *  _va Sign asat u103A
 *  _dl Dependent vowel left to base, vowel e
 *  _da Dependent vowel above base, vowel i, ii, ai
 *  _db Dependent vowel below base, vowel u, uu
 *  _dr Dependent vowel right to base, vowel aa
 *  _sa Sign above base, sign anusvara - it should be vowel
 *  _sb Sign below base, sign dot below (aukmyit)
 *  _sp Sign post base, sign visarga
 */    
    private short[][] mymrStateTable = new short[][] {
/* xx  c1, c2  ng  id  my  mr  mw  mh  vi  va  dl  da  db  dr  sa  sb  sp  */
  { 1,  3,  3,  2, 16,  7,  7,  8,  9, 21,  4, 10, 11, 12, 13, 15, 22,  1}, /*  0 - ground state 21 from 23, 22 from 24 */
  {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, /*  1 - exit state (or sp/sb to the right of the syllable) */
  {-1, -1, -1, -1, -1,  7,  7,  8,  9, -1, 17, 10, 11, 12, 13, 15, 22, -1}, /*  2 - Base NGA 22 from 24*/
  {-1, -1, -1, -1, -1,  7,  7,  8,  9,  5,  4, 10, 11, 12, 13, 15, 22, -1}, /*  3 - Base consonant 22 from 24*/
  {-1, -1, -1, -1, -1, 20, -1, -1, -1, -1, -1, -1, -1, 19, -1, -1, -1,  1}, /*  4 - Asat 19 from 21, 20 from 22*/ 
  {-2,  6, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2}, /*  5 - First virama */
  {-1, -1, -1, -1, -1,  7,  7,  8,  9, -1, -1, 10, 11, 12, 13, 15, -1, -1}, /*  6 - c1 after virama */
  {-1, -1, -1, -1, -1, -1, -1,  8,  9, -1, -1, 10, 11, 12, 13, 15, -1, -1}, /*  7 - First medial */
  {-1, -1, -1, -1, -1, -1, -1, -1,  9, -1, -1, 10, 11, 12, 13, 15, -1, -1}, /*  8 - Second medial */
  {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 15, -1, -1}, /*  9 - Third medial */
  {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 13, 15,  1,  1}, /* 10 - dl, Dependent vowel e */
  {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 12, -1,  1,  1,  1}, /* 11 - da, Dependent vowel i,ii,ai */
  {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 15,  1,  1}, /* 12 - db, Dependent vowel u,uu */
  {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  4, -1, -1, -1, -1, -1,  22, 1}, /* 13 - dr, Dependent vowel aa change 24 from 1, 22 from 24*/
  {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, /* 14 - Shayhto */
  {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1}, /* 15 - sa, Sign anusvara*/
  {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 15, -1,  1}, /* 16 - Independent vowel */
  {-1, -1, -1, -1, -1, -1, -1, -1, -1, 18, -1, -1, -1, -1, -1, -1, -1,  1}, /* 17 - Asat after NGA */
  {-2,  3,  3,  2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2}, /* 18 - Virama after NGA + Asat */
  //{-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, 17, -2, -2, -2, -2, -2, -2, -2}, /* 19 - Next base NGA */
  //{-2, -2, -2, -2, -2, -2, -2, -2, -2,  5,  4, -2, -2, -2, -2, -2, -2, -2}, /* 20 - Next base, rest */
  {-2, -1, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2}, /* 21 - Contractions, vowel u after Asat */
  {-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, 13, -2, -2, -2}, /* 22 - Contractions, Medial ya after virama */
  {-1,  6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, /* 23 - Virama after ground state */
  {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1, -1, -1, -1, -1, -1, -1, -1}, /* 24 - Asat after Aukmyit */
/* exit state > -1 is for invalid order of medials and combination of invalids
   with virama where virama should treat as start of next syllable */
    };
    private char VOWEL_E;
    private char VIRAMA;
    private char NGA;
    private char NYA; 
    private char NNYA; 
    private char NA; 
    private char RA;
    private char LLA;
    private char ASAT;
    private char MEDIAL_WA;
    private char MEDIAL_HA;
    private char MEDIAL_RA;
    private char MEDIAL_YA;
    private char IND_VOWEL_U;
    private char LONG_AA;
    private char VOWEL_I;
    private char VOWEL_U;
    private char VOWEL_UU;

    public UnicodeRender() { 
        NGA = UnicodeUtil.unicodeChar("0x1004");
        NYA = UnicodeUtil.unicodeChar("0x1009");
        NA = UnicodeUtil.unicodeChar("0x1014");
        RA = UnicodeUtil.unicodeChar("0x101B");
        NNYA = UnicodeUtil.unicodeChar("0x100A");
        IND_VOWEL_U = UnicodeUtil.unicodeChar("0x1025");  
        LLA = UnicodeUtil.unicodeChar("0x1020");
        LONG_AA = UnicodeUtil.unicodeChar("0x102B");
        ASAT = UnicodeUtil.unicodeChar("0x103A");
        VOWEL_I = UnicodeUtil.unicodeChar("0x102D");
        VOWEL_U = UnicodeUtil.unicodeChar("0x102F");
        VOWEL_UU = UnicodeUtil.unicodeChar("0x1030");
        VOWEL_E = UnicodeUtil.unicodeChar("0x1031");
        VIRAMA = UnicodeUtil.unicodeChar("0x1039");
        MEDIAL_WA = UnicodeUtil.unicodeChar("0x103D");
        MEDIAL_HA = UnicodeUtil.unicodeChar("0x103E");
        MEDIAL_RA = UnicodeUtil.unicodeChar("0x103C");
        MEDIAL_YA = UnicodeUtil.unicodeChar("0x103B");
        MEDIAL_WAHA = Character.toString(UnicodeUtil.unicodeChar("0x103D")).concat(Character.toString(UnicodeUtil.unicodeChar("0x103E")));
        MEDIAL_YAWA = Character.toString(UnicodeUtil.unicodeChar("0x103B")).concat(Character.toString(UnicodeUtil.unicodeChar("0x103D")));
        MEDIAL_YAHA = Character.toString(UnicodeUtil.unicodeChar("0x103B")).concat(Character.toString(UnicodeUtil.unicodeChar("0x103E")));
        MEDIAL_RAWA = Character.toString(UnicodeUtil.unicodeChar("0x103C")).concat(Character.toString(UnicodeUtil.unicodeChar("0x103D")));
        VIRAMA_DDA = Character.toString(UnicodeUtil.unicodeChar("0x1039")).concat(Character.toString(UnicodeUtil.unicodeChar("0x100D")));
        VIRAMA_TTHA = Character.toString(UnicodeUtil.unicodeChar("0x1039")).concat(Character.toString(UnicodeUtil.unicodeChar("0x100C")));
        VIRAMA_DDHA = Character.toString(UnicodeUtil.unicodeChar("0x1039")).concat(Character.toString(UnicodeUtil.unicodeChar("0x100E")));
    }

    // Gets the charactor class.
    private int getCharClass(final char uniChar) {
        int retValue = 0;
        int ch;
        ch = uniChar;
        if (ch > 255) {
            if (ch >= UnicodeUtil.unicodeChar("0x1000")) { 
                ch -= UnicodeUtil.unicodeChar("0x1000");
                if (ch < mymrCharClasses.length) {
                    retValue = mymrCharClasses[ch];
                }
            }
        }
        return retValue;
    }


    public String render(final String strInput) {
        //Given an input String of unicode cluster to reorder.
        //The return is the visual based cluster (legacy style) String.

        int cursor = 0;
        short state = 0;
        int charCount = strInput.length();
        StringBuilder result = new StringBuilder();

        while (cursor < charCount) {
            String _reserved = "";
            String _signAbove = "";
            String _signBelow = "";
            String _signAfter = "";
            String _base = "";
            String _vowelBefore = "";
            String _vowelBelow = "";
            String _vowelAbove = "";
            String _vowelAfter = "";
            String _medialBefore = "";
            String _medialAfter = "";
            String _kinzi = "";
            String _cluster;
            String _asat = "";
            String _subCons = "";
            
            boolean _virama = false;
            boolean _foundNgaAsat = false;


            while (cursor < charCount) {
                char curChar = strInput.charAt(cursor);
                int mChar = getCharClass(curChar);
                int charClass = mChar & CF_CLASS_MASK;
                try {
                    state = mymrStateTable[state][charClass];
                }
                catch (Exception ex) {
                    state = -1;
                }

                if (state < 0) {
                    if (state < -1)
                        cursor += (state + 1);
                    break;
                }

                //collect variable for cluster here

                if (mChar == _xx) {
                    _reserved = Character.toString(curChar);
                }
                else if (mChar == _sa) 
                {
                    _signAbove = Character.toString(curChar);
                }
                else if (mChar == _sp) 
                {
                    _signAfter = Character.toString(curChar);
                }
                else if (mChar == _c1 || mChar == _c2 ) 
                {
                    if (_virama){ 
                            _subCons = Character.toString(VIRAMA).concat(Character.toString(curChar));
                            _virama = false;
                    }
                    else {
                        _base = Character.toString(curChar);
                    }
                }
                else if (mChar == _ng){
                    _base = Character.toString(curChar);
                }
                else if (mChar == _vi) 
                {
                    if (_foundNgaAsat){
                        _kinzi = Character.toString(curChar);
                        _foundNgaAsat = false; 
                        _base = "";
                    }
                    else {
                    _virama = true;
                    }
                }
                else if (mChar == _mr) //Medial Ra
                {
                     _medialBefore = Character.toString(curChar);
                }
                else if (mChar == _my || mChar == _mw || mChar == _mh)
                {
                     _medialAfter += Character.toString(curChar);
                }
                else if (mChar == _dl) //Vowel E
                {
                    _vowelBefore = Character.toString(curChar);
                }
                else if (mChar == _db) //Dependent vowel placed below the base
                {
                    _vowelBelow = Character.toString(curChar);
                }
                else if (mChar == _da) //Dependent vowel placed above the base
                {
                    _vowelAbove = Character.toString(curChar);
                }
                else if (mChar == _dr) //Dependent vowel placed behind the base
                {
                    _vowelAfter = Character.toString(curChar);
                }
                else if (mChar == _sb) 
                {
                    _signBelow = Character.toString(curChar);
                }
                else if (mChar == _id)
                {
                   _base = Character.toString(curChar);
                }
                else if (mChar == _va) //Asat
                {             
                   if (Character.toString(NGA).equalsIgnoreCase(_base)) {
                        _foundNgaAsat = true;
                    }
                    _asat = Character.toString(curChar);
                }
                cursor += 1;
            }//end of while
            //
            //
            if (Character.toString(VIRAMA).equalsIgnoreCase(_kinzi)) {
                if (Character.toString(ASAT).equalsIgnoreCase(_asat)) {
                   _kinzi = Character.toString(UnicodeUtil.unicodeChar("0xE390"));
                   _asat = "";
                }
            }

          boolean bigMedialRa = false;
            if (!_medialBefore.equalsIgnoreCase("")) {
            String[] stringArray = new String[]{"\u1000", "\u1006", "\u1010", "\u1011", "\u1018", "\u101E"};
                for(String x : stringArray){
                    if(x.equals(_base)){
                        _medialBefore = UnicodeUtil.change(_medialBefore);
                        bigMedialRa = true;
                        break;
                    }
                }
            // start medial logic
                if (Character.toString(MEDIAL_WA).equalsIgnoreCase(_medialAfter) || (_medialAfter.equalsIgnoreCase(MEDIAL_WAHA))){
                    if (bigMedialRa){
                        _medialBefore = Character.toString(UnicodeUtil.unicodeChar("0xE1BC"));//double width medial ra below base   
                    }
                    else {
                        _medialBefore = Character.toString(UnicodeUtil.unicodeChar("0xE1B3"));//single width medial ra below base   
                    }
                }
                if (Character.toString(MEDIAL_HA).equalsIgnoreCase(_medialAfter)) {
                        _medialAfter = Character.toString(UnicodeUtil.unicodeChar("0xE1F3"));// small ha with medial ra
                }
                if (!_vowelAbove.equalsIgnoreCase("") || !_kinzi.equalsIgnoreCase("")){
                    if (bigMedialRa){
                        _medialBefore = UnicodeUtil.change(_medialBefore); 
                    }else {
                       _medialBefore = Character.toString(UnicodeUtil.unicodeChar("0xE1B6"));     
                    } 
                }
            }
            bigMedialRa = false;
            
            // medial lig
            if (_medialAfter.equalsIgnoreCase(MEDIAL_WAHA)) {
                _medialAfter = Character.toString(UnicodeUtil.unicodeChar("0xE1D1"));
            }
            if (_medialAfter.equalsIgnoreCase(MEDIAL_YAHA)) {
                _medialAfter = Character.toString(UnicodeUtil.unicodeChar("0xE1A3"));
            }
            if (_medialAfter.equalsIgnoreCase(MEDIAL_YAWA)) {
                _medialAfter = Character.toString(UnicodeUtil.unicodeChar("0xE1A4"));
            }
            if (!"".equalsIgnoreCase(_subCons)){
                if (VIRAMA_DDA.equalsIgnoreCase(_subCons)&&(_base.equalsIgnoreCase(Character.toString(UnicodeUtil.unicodeChar("0x100F"))))){
                        _base = "";
                        _subCons = Character.toString(UnicodeUtil.unicodeChar("0xE105"));
                }
                else if (VIRAMA_TTHA.equalsIgnoreCase(_subCons)&&(_base.equalsIgnoreCase(Character.toString(UnicodeUtil.unicodeChar("0x100B"))))){
                        _base = "";
                        _subCons = Character.toString(UnicodeUtil.unicodeChar("0xE103"));
                }
                else if (VIRAMA_DDHA.equalsIgnoreCase(_subCons)&&(_base.equalsIgnoreCase(Character.toString(UnicodeUtil.unicodeChar("0x100D"))))){
                        _base = "";
                        _subCons = Character.toString(UnicodeUtil.unicodeChar("0xE104"));
                }
                else {
                    _subCons = UnicodeUtil.getSubConsonant(_subCons);
                }
            } 
            if (Character.toString(NNYA).equalsIgnoreCase(_base) && Character.toString(MEDIAL_HA).equalsIgnoreCase(_medialAfter) ) {
                    _medialAfter = Character.toString(UnicodeUtil.unicodeChar("0xE1F3"));
            }
            if (!_medialAfter.equalsIgnoreCase("")|| !"".equalsIgnoreCase(_medialBefore)|| !"".equalsIgnoreCase(_subCons) || Character.toString(NNYA).equalsIgnoreCase(_base) || Character.toString(IND_VOWEL_U).equalsIgnoreCase(_base) || Character.toString(LLA).equalsIgnoreCase(_base)){
                 _vowelBelow = UnicodeUtil.change(_vowelBelow);// long u, uu
            }
            if (!_kinzi.equalsIgnoreCase("") && !_vowelAbove.equalsIgnoreCase("")){
                _kinzi = "";
                _vowelAbove = UnicodeUtil.change(_vowelAbove);
            }
            if (!_kinzi.equalsIgnoreCase("") && !_signAbove.equalsIgnoreCase("")){
                _kinzi = "";
                _signAbove = UnicodeUtil.change(_signAbove);
            }
            if (Character.toString(LONG_AA).equalsIgnoreCase(_vowelAfter) && !_asat.equalsIgnoreCase("")) {
                    _asat = "";
                    _vowelAfter = Character.toString(UnicodeUtil.unicodeChar("0xE02D"));
            }
            if (Character.toString(NYA).equalsIgnoreCase(_base)) {
                if (!_asat.equalsIgnoreCase("")){
                    _base = Character.toString(UnicodeUtil.unicodeChar("0xE009"));
                }
                if (!_vowelAfter.equalsIgnoreCase("")){
                    _base = Character.toString(UnicodeUtil.unicodeChar("0xE209"));
                }
                if (!_subCons.equalsIgnoreCase("")) {
                    _base = Character.toString(UnicodeUtil.unicodeChar("0xE100"));
                }
            }
            if (Character.toString(NNYA).equalsIgnoreCase(_base)) {
                if (!_medialAfter.equalsIgnoreCase("")){
                    if (Character.toString(MEDIAL_WA).equalsIgnoreCase(_medialAfter)||_medialAfter.equalsIgnoreCase(Character.toString(UnicodeUtil.unicodeChar("0xE1D1")))){
                        _base = Character.toString(UnicodeUtil.unicodeChar("0xE101"));
                    }
                }
            }
            if (Character.toString(NA).equalsIgnoreCase(_base)) {
                if (!_medialAfter.equalsIgnoreCase("") || !_medialBefore.equalsIgnoreCase("")|| !_vowelBelow.equalsIgnoreCase("") || !_subCons.equalsIgnoreCase("")){
                    _base = Character.toString(UnicodeUtil.unicodeChar("0xE107"));
                    if (_subCons.equalsIgnoreCase(Character.toString(UnicodeUtil.unicodeChar("0xE010")))){
                        _subCons = Character.toString(UnicodeUtil.unicodeChar("0xE01F"));
                    }
                    if  (_subCons.equalsIgnoreCase(Character.toString(UnicodeUtil.unicodeChar("0xE011")))){
                        _subCons = Character.toString(UnicodeUtil.unicodeChar("0xE020"));
                    }
                }
            }
            if (Character.toString(RA).equalsIgnoreCase(_base)){
                if (!_vowelBelow.equalsIgnoreCase("")){
                     _base = UnicodeUtil.change(_base);
                }
            }
            if (Character.toString(MEDIAL_HA).equalsIgnoreCase(_medialAfter) && _vowelBelow.equalsIgnoreCase(Character.toString(UnicodeUtil.unicodeChar("0xE2F1")))) {
                    _vowelBelow = Character.toString(UnicodeUtil.unicodeChar("0xE1F2"));
                    _medialAfter = "";
            }
            if (Character.toString(VOWEL_I).equalsIgnoreCase(_vowelAbove) && !_signAbove.equalsIgnoreCase("")) {
                    _signAbove = Character.toString(UnicodeUtil.unicodeChar("0xE2D1"));
                    _vowelAbove = "";
            }
            
            if (Character.toString(MEDIAL_HA).equalsIgnoreCase(_medialAfter) && Character.toString(VOWEL_UU).equalsIgnoreCase(_vowelBelow)) {
                    _vowelBelow = _vowelBelow = UnicodeUtil.change(_vowelBelow);
            }
            
            if (!_signBelow.equalsIgnoreCase("")){
                if (Character.toString(VOWEL_U).equalsIgnoreCase(_vowelBelow)||Character.toString(VOWEL_UU).equalsIgnoreCase(_vowelBelow)){
                    _signBelow = Character.toString(UnicodeUtil.unicodeChar("0xE237"));
                }
                else if (Character.toString(NA).equalsIgnoreCase(_base)||Character.toString(MEDIAL_HA).equalsIgnoreCase(_medialAfter)){
                    _signBelow = Character.toString(UnicodeUtil.unicodeChar("0xE237"));
                }
                else if (Character.toString(MEDIAL_WA).equalsIgnoreCase(_medialAfter)||Character.toString(MEDIAL_YA).equalsIgnoreCase(_medialAfter)){
                    _signBelow = Character.toString(UnicodeUtil.unicodeChar("0xE137"));
                }
                else if (_vowelBelow.equalsIgnoreCase(Character.toString(UnicodeUtil.unicodeChar("0xE1F2")))){
                    _signBelow = Character.toString(UnicodeUtil.unicodeChar("0xE137"));
                }
                else if (_vowelBelow.equalsIgnoreCase(Character.toString(UnicodeUtil.unicodeChar("0xE2F1")))||_vowelBelow.equalsIgnoreCase(Character.toString(UnicodeUtil.unicodeChar("0xE2F2")))){
                    _signBelow = Character.toString(UnicodeUtil.unicodeChar("0xE137"));
                }
            }
            if (Character.toString(UnicodeUtil.unicodeChar("0xE018")).equalsIgnoreCase(_subCons)&& Character.toString(UnicodeUtil.unicodeChar("0x1019")).equalsIgnoreCase(_base)) {
                   _subCons = Character.toString(UnicodeUtil.unicodeChar("0xE218"));
            }
            if (Character.toString(VOWEL_E).equalsIgnoreCase(_vowelBefore) && Character.toString(ASAT).equalsIgnoreCase(_asat)) {
                _cluster = _vowelBefore + _medialBefore + _base + _subCons + _kinzi + _medialAfter + _vowelAbove + _vowelBelow + _vowelAfter + _asat + _signAbove + _signBelow + _signAfter;
            }
            else {
                _cluster = _vowelBefore + _medialBefore + _base + _subCons + _asat + _kinzi + _medialAfter + _vowelAbove + _vowelBelow + _vowelAfter + _signAbove + _signBelow + _signAfter;
            }
            
            result.append(_cluster + _reserved);
            state = 0;
            //end of while
        }

        return result.toString();
    }
}
