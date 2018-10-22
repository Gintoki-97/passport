package cn.gin.passport.module.web.controller;

import cn.gin.passport.common.Constants;
import cn.gin.passport.common.util.JsonObject;
import cn.gin.passport.common.util.Requests;
import cn.gin.passport.module.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(Constants.Path.CTRL_USER)
public class UserController {

    //// View Request

    @GetMapping(Constants.Path.CTRL_USER_SIGNIN)
    public String signinViewRequest() {

        if (UserService.isAuthenticated()) {

            return Requests.redirect(Constants.Path.CTRL_USER);
        }

        return Constants.Path.VIEW_SIGNIN;
    }

    //// Handler Request

    @PostMapping(Constants.Path.CTRL_USER_SIGNIN)
    public String signinHandler() {

        if (UserService.isAuthenticated()) {

            return Requests.redirect(Constants.Path.CTRL_USER);
        }

        return Constants.Path.VIEW_SIGNIN;
    }


    //// REST Interface

    @GetMapping({Constants.Mark.EMPTY, Constants.Mark.SLASH})
    @ResponseBody
    public String currentUser() {

        UserDetails currentUser = UserService.getCurrentUser();
        JsonObject jsonObject = new JsonObject();

        if (currentUser == null) {
            jsonObject.setCode(1);
            jsonObject.setSuccess(false);
            jsonObject.setMsg("No user signed in");
        } else {
            jsonObject.setCode(0);
            jsonObject.setSuccess(true);
            jsonObject.setMsg("Request success");
            jsonObject.addParam("user", currentUser);
        }

        return jsonObject.toJson();
    }
}
