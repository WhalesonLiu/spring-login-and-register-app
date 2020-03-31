package com.caroline.fruit.web.controller;

import com.caroline.fruit.dto.AddUserDto;
import com.caroline.fruit.enums.ResultEnum;
import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.exception.FruitMsgEnum;
import com.caroline.fruit.message.Message;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.User;
import com.caroline.fruit.service.DeliveryInfoService;
import com.caroline.fruit.service.UserService;
import com.caroline.fruit.web.dto.RegisterUserForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;


    private Message message = new Message();

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    ModelAndView getRegister(Model model) {
        model.addAttribute("registerUserForm", new RegisterUserForm());
        return new ModelAndView("register", "registerUserForm", new RegisterUserForm());
    }

    @ResponseBody
    @GetMapping("/user/list")
    public Result getUserList(@RequestParam("page") Integer offset,
                              @RequestParam("size") Integer size) throws FruitException{

        return userService.getUserList(PageRequest.of(offset,size));

    }
    @PostMapping("/add/user")
    @ResponseBody
    public Result addUser(@RequestBody AddUserDto addUserDto) throws FruitException {

        if( addUserDto != null){

            return userService.addUser(addUserDto);

        }else {

            throw new FruitException(FruitMsgEnum.ParamEmpty);
        }

    }
    @PostMapping("/register")
    ModelAndView postRegister(@ModelAttribute("registerUserForm") @Valid RegisterUserForm userForm,
                              BindingResult bindingResult) {
        if (userService.findByUsername(userForm.getUsername().toLowerCase()) != null) {
            bindingResult
                    .addError(new FieldError(userForm.toString(), "email", "Email is already taken!"));
        }
        if (!bindingResult.hasErrors()) {

            User user = new User();
            user.setUsername(userForm.getUsername());
            //user.setEmail(userForm.getEmail().toLowerCase());
            user.setPassword(userForm.getPassword());
            userService.createBasicUser(user);
            userService.autoLogin(userForm.getUsername(), userForm.getPassword());
            return new ModelAndView("redirect:/index");
        } else {
            userForm.setUsername(userForm.getUsername().toLowerCase());
            ModelAndView template = new ModelAndView("register", "registerUserForm", userForm);
            template.setStatus(HttpStatus.BAD_REQUEST);
            return template;
        }
    }

    @GetMapping("/login")
    public String login(Model model,
                        @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("warning", "Invalid credentials");
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response,
                             RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        redirectAttributes.addFlashAttribute("success", "You have been logged out");
        return "redirect:/login";
    }
}
