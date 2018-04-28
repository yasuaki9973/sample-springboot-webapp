package mvc.controller;

import java.util.List;
import mvc.domain.User;
import mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author yasuaki
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    String displayUsers(Model model) {

        // 一覧表示用の変数を設定する
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        // 更新・削除用の変数を設定する
        model.addAttribute("user", new User());
        return "user/users";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    String displayProfile(Model model) {

        String email = LoginHelper.getLoginUserName();
        User user = userService.findOne(email);
        model.addAttribute("user", user);

        return "user/profile";
    }

    @RequestMapping(value = "/update/role", method = RequestMethod.POST)
    String updateUserRole(Model model, @ModelAttribute User user) {

        userService.updateUser(user);
        return "redirect:/user";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    String deleteUser(Model model, @ModelAttribute User user) {

        userService.deleteUser(user);
        return "redirect:/user";
    }

}
