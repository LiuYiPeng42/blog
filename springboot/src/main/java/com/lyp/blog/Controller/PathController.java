package com.lyp.blog.Controller;


import com.google.gson.Gson;
import com.lyp.blog.Utils.Path;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/path")
public class PathController {

    @GetMapping("/get")
    public String getMarkDown() {

        Path path = new Path();
        Path.Dir dirTree = path.walk("markdown/notes");
        Gson gson = new Gson();
//        System.out.println(dirTree);

        return gson.toJson(dirTree);
    }
}
