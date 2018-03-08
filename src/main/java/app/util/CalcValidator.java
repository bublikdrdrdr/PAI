package app.util;

import java.util.regex.Pattern;

public class CalcValidator {
    private static Pattern numberPattern = Pattern.compile("^[+-]?\\d*\\.?\\d+$");
    private static Pattern operationPattern = Pattern.compile("^(add|sub|mul|div)$");

    public static boolean checkNumber(String number){
        return numberPattern.matcher(number).find();
    }

    public static boolean checkOperation(String operation){
        return operationPattern.matcher(operation).find();
    }

    public static String formatDouble(double d){
        return (String)((long) d == d ? "" + (long) d : "" + d);
    }
}
