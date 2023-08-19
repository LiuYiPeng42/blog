package com.lyp.blog.Utils;

import lombok.extern.slf4j.Slf4j;
import java.io.*;


@Slf4j
public class FileOperation {
    public static void copyFile(String src, String dst) {
        writeFile(dst, readFile(src));
    }

    public static String readFile(String filePath) {
        // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        // 不关闭文件会导致资源的泄露，读写文件都同理
        StringBuilder text = new StringBuilder();

        // Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；
        // 详细解读https://stackoverflow.com/a/12665271
        try (FileReader reader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(reader)  // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                text.append(line).append("\n");
            }
        } catch (IOException e) {
            return "打开文件失败";
        }

        return text.toString();
    }

    public static void writeFile(String filePath, String text) {
        try {
            File writeName = new File(filePath); // 相对路径，如果没有则要建立一个新的output.txt文件
            Boolean success = writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(text); // \r\n即为换行
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            log.error(String.valueOf(e));
        }
    }


}
