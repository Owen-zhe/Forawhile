package com.forawhile.blog.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhotoShowController {

    @GetMapping("/photo")
    public String photo(){
        return "photo";
    }
}
