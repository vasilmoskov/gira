package bg.softuni.gira.web;

import bg.softuni.gira.models.dto.LoginDto;
import bg.softuni.gira.service.UserService;
import bg.softuni.gira.utils.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("login")
public class LoginController {
    private final UserService userService;
    private final CurrentUser currentUser;

    public LoginController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping
    public String getLogin() {
        if (currentUser.isLoggedIn()) {
                return "redirect:/";
        }
        return "login";
    }

    @ModelAttribute("loginDto")
    public LoginDto loginModel() {
        return new LoginDto();
    }

    @PostMapping
    public String doLogin(@Valid LoginDto loginDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginDto", bindingResult);
            return "redirect:login";
        }

        if (!this.userService.loginSuccessful(loginDto)) {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute("badCredentials", true);
            return "redirect:/login";
        }

        return "redirect:/";
    }
}
