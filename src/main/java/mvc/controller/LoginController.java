package mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.domain.User;
import mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author yasuaki
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "loginForm", method = RequestMethod.GET)
    String displayLoginPage() {
        return "login/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "top", method = RequestMethod.GET)
    String displayTopPage(Model model) {

        String email = LoginHelper.getLoginUserName();
        User user = userService.findOne(email);

        // ユーザー情報が削除されていた場合はログアウト
        if (user == null) {
            return "redirect:/logout";
        }

        model.addAttribute("user", user);
        return "login/top";
    }

}
