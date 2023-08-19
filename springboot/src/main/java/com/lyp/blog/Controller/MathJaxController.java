package com.lyp.blog.Controller;

import com.lyp.blog.Utils.FileOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mj")
public class MathJaxController {

    @GetMapping("/get")
    public String getMarkDown(){
        return FileOperation.readFile("js/tex-svg.min.js");
    }
}
