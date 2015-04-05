package com.flol.semrankerengine.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GeographicalSecretKeyMap {

	public static final Map<Integer,String> KEYS;
    static {
        Map<Integer, String> KEYS_ = new HashMap<Integer, String>();
        KEYS_.put(4, "E");
        KEYS_.put(5, "F");
        KEYS_.put(6, "G");
        KEYS_.put(7, "H");
        KEYS_.put(8, "I");
        KEYS_.put(9, "J");
        KEYS_.put(10, "K");
        KEYS_.put(11, "L");
        KEYS_.put(12, "M");
        KEYS_.put(13, "N");
        KEYS_.put(14, "O");
        KEYS_.put(15, "P");
        KEYS_.put(16, "Q");
        KEYS_.put(17, "R");
        KEYS_.put(18, "S");
        KEYS_.put(19, "T");
        KEYS_.put(20, "U");
        KEYS_.put(21, "V");
        KEYS_.put(22, "W");
        KEYS_.put(23, "X");
        KEYS_.put(24, "Y");
        KEYS_.put(25, "Z");
        KEYS_.put(26, "a");
        KEYS_.put(27, "b");
        KEYS_.put(28, "c");
        KEYS_.put(29, "d");
        KEYS_.put(30, "e");
        KEYS_.put(31, "f");
        KEYS_.put(32, "g");
        KEYS_.put(33, "h");
        KEYS_.put(34, "i");
        KEYS_.put(35, "j");
        KEYS_.put(36, "k");
        KEYS_.put(37, "l");
        KEYS_.put(38, "m");
        KEYS_.put(39, "n");
        KEYS_.put(40, "o");
        KEYS_.put(41, "p");
        KEYS_.put(42, "q");
        KEYS_.put(43, "r");
        KEYS_.put(44, "s");
        KEYS_.put(45, "t");
        KEYS_.put(46, "u");
        KEYS_.put(47, "v");
        KEYS_.put(48, "w");
        KEYS_.put(49, "x");
        KEYS_.put(50, "y");
        KEYS_.put(51, "z");
        KEYS_.put(52, "0");
        KEYS_.put(53, "1");
        KEYS_.put(54, "2");
        KEYS_.put(55, "3");
        KEYS_.put(56, "4");
        KEYS_.put(57, "5");
        KEYS_.put(58, "6");
        KEYS_.put(59, "7");
        KEYS_.put(60, "8");
        KEYS_.put(61, "9");
        KEYS_.put(62, "-");
        KEYS_.put(63, "");
        KEYS_.put(64, "A");
        KEYS_.put(65, "B");
        KEYS_.put(66, "C");
        KEYS_.put(67, "D");
        KEYS_.put(68, "E");
        KEYS_.put(69, "F");
        KEYS_.put(70, "G");
        KEYS_.put(71, "H");
        KEYS_.put(72, "I");
        KEYS_.put(73, "J");
        KEYS_.put(76, "M");
        KEYS_.put(83, "T");
        KEYS_.put(89, "L");
        KEYS = Collections.unmodifiableMap(KEYS_);
    }
    
    public static String getKey(int lenght)
    {
    	return KEYS.get(lenght);
    }
    
    
}
