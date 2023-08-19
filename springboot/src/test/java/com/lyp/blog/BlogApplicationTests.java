package com.lyp.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.Instant;

@SpringBootTest
class BlogApplicationTests {

	@Test
	void contextLoads() {

		System.out.println(lastModifiedTime(new File("markdown/MainPage.md")));

	}

	public static String lastModifiedTime(File file) {
		if (file == null) {
			return null;
		}

		BasicFileAttributes attr = null;
		try {
			Path path =  file.toPath();
			attr = Files.readAttributes(path, BasicFileAttributes.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Instant instant = null;
		if (attr != null) {
			instant = attr.lastModifiedTime().toInstant();
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (instant != null) {
			return format.format(instant.getEpochSecond() * 1000L);
		}
		return  null;
	}

}
