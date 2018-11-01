package cn.gin.passport.module.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SiteController {

    @GetMapping({"", "/"})
    @ResponseBody
    public String index() {

        return "http://passport.wandan.site";
    }
}
