package com.example.lab5.controller;

import com.ua.faculty.dto.UserDTO;
import com.ua.faculty.exceptions.UserAlreadyExistsException;
import com.ua.faculty.service.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @SneakyThrows
    @PostMapping(value = "/registration")
    public String register(@ModelAttribute("userDTO") @Valid UserDTO user,
                           BindingResult bindingResult, Model model,
                           @RequestParam String role) {
        if (bindingResult.hasErrors()) {
            if (role.equals("user")) {
                return "registration";
            } else {
                return "create_teacher";
            }
        }
        registrationService.register(user, role);
        return "redirect:profile";
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    public String handleException(RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("userExists", "");
        return "redirect:/registration";
    }

}
