package com.lyp.blog;

import com.lyp.blog.Utils.FileOperation;
import com.lyp.blog.Utils.MarkdownCorrection;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class BlogApplicationTests {

	@Test
	void contextLoads() {

//		String markdown = FileOperation.readFile("markdown/notes/A Survey on Advancing the DBMS Query Optimizer.md");
//
//		String[] markdownRegex = new String[]{"\u200B\t\t", "<img src=\""};
//
//		HashMap<String, String> markdownReplaceMap = new HashMap<>();
//		markdownReplaceMap.put("\u200B\t\t", "&emsp;&emsp;");
//		markdownReplaceMap.put("<img src=\"", "<img src=\"http://120.77.177.229:8082/" + "123" + "/");
//
//		System.out.println(MarkdownCorrection.correct(markdown, markdownRegex, markdownReplaceMap, true));

	}

}
