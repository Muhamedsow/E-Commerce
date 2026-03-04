package uidt.master.ecommerce.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uidt.master.ecommerce.entity.Connexion;
import uidt.master.ecommerce.entity.User;
import uidt.master.ecommerce.service.UserService;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("login")
    public String connexion(@RequestBody Connexion connexion) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            connexion.getEmail(),
                            connexion.getPassword()
                    )
            );
            return "Login successfully!";
        }catch (Exception e) {
            return "Login failed: " + e.getMessage();
        }
    }

    @PostMapping("register")
    public String register(@RequestBody User user) {
        return this.userService.save(user);
    }
}