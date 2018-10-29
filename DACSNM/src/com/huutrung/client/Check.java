package com.huutrung.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {

    public Check() {
    }
    public static boolean checkName(String n) {
        return true;
        //them dieu kien check
    }

    public static boolean check_IP(String n) {
        if (n == null || n.length() > 15 || n.length() < 11) {
            return false;
        } else {
            String strPattern = "[0-9]{1,3}+\\.[0-9]{1,3}+\\.[0-9]{1,3}+\\.[0-9]{1,3}";
//        	String strPattern = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
            Pattern p;
            Matcher m;
            int flag = Pattern.CASE_INSENSITIVE;
            p = Pattern.compile(strPattern, flag);
            m = p.matcher(n);
            return m.find();
        }
    }

    public static boolean check_hoten(String n) {
        return true;
    }

    public static boolean checkMark(String n) {
        if (n == null || n.length() > 2 || n.length() < 1) {
            return false;
        } else {
            String strPattern = "[^0-9]";
            Pattern p;
            Matcher m;
            int flag = Pattern.CASE_INSENSITIVE;
            p = Pattern.compile(strPattern, flag);
            m = p.matcher(n);
            return !m.find();
        }
    }

    public static boolean checkSpace(String n) {
        return true;
//        String strPattern = "[^ ]";
//        Pattern p;
//        Matcher m;
//        int flag = Pattern.CASE_INSENSITIVE;
//        p = Pattern.compile(strPattern, flag);
//        m = p.matcher(n);
//        return m.find();

    }

    public static boolean checkNumber(String n) {
        if (n == null || n.length() > 10 || n.length() < 1) {
            return false;
        } else {
            String strPattern = "[^0-9]";
            Pattern p;
            Matcher m;
            int flag = Pattern.CASE_INSENSITIVE;
            p = Pattern.compile(strPattern, flag);
            m = p.matcher(n);
            return !m.find();
        }
    }

    public static boolean check(String n) {
        if (n == null || n.length() == 0) {
            return false;
        }
        return true;
    }

    public static boolean checkID(String n) {
        String strPattern = "[^0-9]";
        Pattern p;
        Matcher m;
        int flag = Pattern.CASE_INSENSITIVE;
        p = Pattern.compile(strPattern, flag);
        m = p.matcher(n);
        return !m.find();
//        if (n == null || n.length() > 4 || n.length() < 2) {
//            return false;
//        } else {
//            String strPattern = "[^A-Z0-9]";
//            Pattern p;
//            Matcher m;
//            int flag = Pattern.CASE_INSENSITIVE;
//            p = Pattern.compile(strPattern, flag);
//            m = p.matcher(n);
//            return !m.find();
//
//        }
    }

}
