package com.github.tax1driver.sectors.utils.parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingUtils {

    public static int indexOfRegex(String in, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(in);

        if (m.find()) {
            return m.start();
        }

        return -1;
    }




}
