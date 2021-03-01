package app.exam_t.web.controllers;

import app.exam_t.models.models.UserLoginBindingModel;
import app.exam_t.models.models.UserRegisterBindingModel;
import app.exam_t.models.services.UserLoginServiceModel;
import app.exam_t.models.services.UserRegisterServiceModel;
import app.exam_t.services.contracts.UserService;
import app.exam_t.util.ModelValidationChecker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelValidationChecker modelValidationChecker;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(ModelValidationChecker modelValidationChecker,
                          UserService userService, ModelMapper modelMapper) {
        this.modelValidationChecker = modelValidationChecker;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("isExists", false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel model,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !model.getPassword().equals(model.getConfirmPassword())) {
            return this.modelValidationChecker.checkErrors(model, bindingResult, redirectAttributes,
                    "register");
        }
        UserRegisterServiceModel serviceModel = this.modelMapper.map(model, UserRegisterServiceModel.class);
        if (!this.userService.registerUser(serviceModel)) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", model);
            redirectAttributes.addFlashAttribute("isExists", true);
            return "redirect:register";
        }

        return "redirect:login";
    }

    @GetMapping("/login")
    public String loginUser(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("notFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel model,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return this.modelValidationChecker.checkErrors(model, bindingResult, redirectAttributes, "login");
        }
        UserLoginServiceModel serviceModel = this.modelMapper.map(model, UserLoginServiceModel.class);
        if (!this.userService.checkIfUserExists(serviceModel)) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", model);
            redirectAttributes.addFlashAttribute("notFound", true);
            return "redirect:login";
        }

        httpSession.setAttribute("user", model);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
