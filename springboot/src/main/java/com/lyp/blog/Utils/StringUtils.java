package com.lyp.blog.Utils;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String replace(String text, Pattern pattern, HashMap<String, String> map) {

        StringBuilder buffer = new StringBuilder();
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String match = matcher.group();
            System.out.println(match + " -" + map.get(match));
            matcher.appendReplacement(buffer, map.get(match));
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }
}
