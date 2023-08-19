package com.lyp.blog.Controller;

import com.lyp.blog.Utils.MarkdownCorrection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/md")
public class MarkDownController {

    @GetMapping("/get")
    public String getMarkDown(@RequestParam("path") String path){


        //        System.out.println(markdown);

        return MarkdownCorrection.correct(path);
    }
}
