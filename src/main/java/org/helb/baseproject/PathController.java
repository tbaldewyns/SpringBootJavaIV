package org.helb.baseproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PathController {


    @GetMapping("/")
    public String home(){
        return "public/index";
    }

    @GetMapping("/about")
    public String about() {
        return "public/about";
    }

}
