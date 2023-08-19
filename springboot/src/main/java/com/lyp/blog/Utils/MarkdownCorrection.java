package com.lyp.blog.Utils;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownCorrection {

    private static final HashMap<String, String> markdownReplaceMap;

    private static final HashMap<String, String> formulaReplaceMap;

    private static final Pattern markdownPattern;

    private static final Pattern formulaPattern;


    static {

        String[] markdownRegex = new String[]{
                "\u200B\t\t", // 段落缩进
                "<img.*?/>|!\\[[0-9]\\]\\(.*?\\)", // 图片
                "(\\$\\$(.|\\n)*?\\$\\$)|(\\$.*?\\$)" // 公式块
        };
        String regex = String.join("|", markdownRegex);
        markdownPattern = Pattern.compile(regex);

        markdownReplaceMap = new HashMap<>();
        markdownReplaceMap.put("\u200B\t\t", "&emsp;&emsp;");

        String[] formulaRegex = new String[]{"\\\\}", "\\\\\\{", "\\\\", "\\$"};
        formulaPattern = Pattern.compile(String.join("|", formulaRegex));

        formulaReplaceMap = new HashMap<>();
        formulaReplaceMap.put("\\}", "\\\\\\\\\\\\\\\\}");
        formulaReplaceMap.put("\\{", "\\\\\\\\\\\\\\\\{");
        formulaReplaceMap.put("\\", "\\\\\\\\\\\\\\\\");
        formulaReplaceMap.put("$", "\\\\\\$");
    }

    public static String correct(String path) {

        String markdown = FileOperation.readFile(path);
        // 图片所在的文件夹
        String dirPath = path.substring(0, path.lastIndexOf("."));

//        markdownReplaceMap.put("<img src=\"", "<img src=\"http://120.77.177.229:8082/" + dirPath + "/");

        StringBuilder buffer = new StringBuilder();

        Matcher matcher = markdownPattern.matcher(markdown);
        while (matcher.find()) {
            String match = matcher.group();

            if (match.startsWith("$")) {
                matcher.appendReplacement(buffer, correctFormula(match));
            } else {
                if (match.startsWith("<img") || match.startsWith("![")){
                    matcher.appendReplacement(buffer, correctImage(match, dirPath));
                } else {
                    matcher.appendReplacement(buffer, markdownReplaceMap.get(match));
                }
            }

        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private static String correctFormula(String text) {
        StringBuilder buffer = new StringBuilder();
        Matcher matcher = formulaPattern.matcher(text);

        while (matcher.find()) {
            String match = matcher.group();
//            System.out.println(match + " -" + formulaReplaceMap.get(match));
            matcher.appendReplacement(buffer, formulaReplaceMap.get(match));
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private static String correctImage(String text, String dirPath) {

        String html = "<div style=\"text-align: center;\"><img src=\"http://120.77.177.229:8082/";

        String imgPath;
        String zoom;

        String pathPattern = "/.*?([)\"])";
        String zoomPattern = "zoom:.*?%";

        Matcher pathMatcher = Pattern.compile(pathPattern).matcher(text);
        Matcher zoomMatcher = Pattern.compile(zoomPattern).matcher(text);

        if (pathMatcher.find()){
            imgPath = pathMatcher.group();
            imgPath = dirPath + imgPath.substring(imgPath.lastIndexOf("/"), imgPath.length() - 1);

            if (zoomMatcher.find()) {
                zoom = zoomMatcher.group();
                return html + imgPath + "\" style=\""+ zoom  +";\" /></div>";
            }
            return html + imgPath + "\"/></div>";
        }

        return "";
    }
}
