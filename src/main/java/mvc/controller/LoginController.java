package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author yasuaki
 */
@Controller
public class LoginController {

    @RequestMapping(value = "loginForm", method = RequestMethod.GET)
    String loginForm() {
        return "login";
    }

}
