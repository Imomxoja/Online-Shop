package uz.pdp.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.domain.dto.user.UserCreateDto;
import uz.pdp.domain.dto.user.UserReadDto;
import uz.pdp.service.user.UserService;

@Controller(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService service;

    @GetMapping("/sign-up")
    public String signUpPage() {
        return "signUp.html";
    }

    @PostMapping("/sign-up")
    public String signUp(
            @ModelAttribute UserCreateDto dto) {
        UserReadDto userReadDto = service.checkUser(dto);

        if (userReadDto == null) {
            service.create(dto);
            return "signIn";
        }
        return "signUp";
    }

    @GetMapping("/sign-in")
    public String signInPage() {
        return "signIn.html";
    }

    @PostMapping("/sign-in")
    public String signIn(
            @ModelAttribute UserCreateDto dto,
            @RequestParam("role") String role,
            HttpSession session
    ) {
        UserReadDto userReadDto = service.checkUser(dto);

        if (userReadDto != null) {
            session.setAttribute("id", userReadDto.getId());
            if (role.equals("SELLER")) {
                return "RedirectSellerPage";
            }
            return "RedirectCustomerPage";
        }
        return "signIn";
    }
}
