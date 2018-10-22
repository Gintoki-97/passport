package cn.gin.passport.module.web.controller;

import cn.gin.passport.common.Constants;
import cn.gin.passport.common.util.Requests;
import cn.gin.passport.module.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {

    //// View Request

    @GetMapping({Constants.Mark.EMPTY, Constants.Mark.SLASH})
    public String index() {

        if (UserService.isAuthenticated()) {

            return Requests.redirect(Constants.Path.CTRL_USER);
        }

        return Requests.redirect(Constants.Path.CTRL_USER, Constants.Path.CTRL_USER_SIGNIN);
    }
}