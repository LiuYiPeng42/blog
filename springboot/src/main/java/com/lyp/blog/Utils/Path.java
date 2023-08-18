package com.lyp.blog.Utils;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

@Service
public class Path {

    public static class Dir {
        String name;
        String path;
        String type;
        ArrayList<Dir> children;

        public Dir(String name, String path, String type) {
            this.name = name;
            this.path = path;
            this.type = type;
            this.children = new ArrayList<>();
        }

        private int indexOf(String name) {
            for (int i = 0; i < this.children.size(); i++) {
                if (this.children.get(i).name.equals(name)) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public String toString() {
            return "Dir{" +
                    "name='" + name + '\'' +
                    ", path='" + path + '\'' +
                    ", type='" + type + '\'' +
                    ", children=" + children +
                    '}';
        }
    }

    public Dir walk(String path) {

        Dir dirTree = new Dir("md", "/", "dir");
        walk(path, dirTree);

        return dirTree;
    }

    private void walk(String path, Dir dir) {
        File file = new File(path);
        File[] files = file.listFiles();
        assert files != null;
        for (File f : files) {                    //遍历File[]数组
            if (f.isDirectory()) {
                Dir newDir = new Dir(f.getName(), f.getPath(), "dir");
                int index = dir.indexOf(f.getName());
                if (index == -1) {
                    dir.children.add(newDir);
                    walk(f.getPath(), newDir);
                }
            } else {
                if (f.getPath().endsWith(".md")) {
                    String fileName = f.getName().substring(0, f.getName().length() - 3);
                    int index = dir.indexOf(fileName);
                    if (index > -1) {
                        dir.children.get(index).type = "file";
                        dir.children.get(index).path = f.getPath();
                    } else {
                        Dir newDir = new Dir(fileName, f.getPath(), "file");
                        dir.children.add(newDir);
                    }
                }
            }
        }
    }

}
