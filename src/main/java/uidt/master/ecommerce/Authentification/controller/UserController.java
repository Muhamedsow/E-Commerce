package uidt.master.ecommerce.Authentification.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uidt.master.ecommerce.Authentification.entity.Connexion;
import uidt.master.ecommerce.Authentification.entity.User;
import uidt.master.ecommerce.Authentification.service.UserService;
import uidt.master.ecommerce.Authentification.token.*;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtService;
    private final RefreshTokenService refreshService;

    @PostMapping("login")
    public ResponseEntity<?> connexion(@RequestBody Connexion connexion) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            connexion.getEmail(),
                            connexion.getPassword()
                    )
            );
            User user = userService.findByEmail(connexion.getEmail());
            String accesstoken = jwtService.generateToken(user);
            RefreshToken refreshToken = refreshService.createOrRotate(user.getEmail());
            return ResponseEntity.ok(new Response(accesstoken, refreshToken.getToken()));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
    }

    @PostMapping("refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequest request) {
        RefreshToken oldToken = refreshService.verify(request.getRefreshToken());
        refreshService.revoke(oldToken.getToken());
        RefreshToken newToken = refreshService.createOrRotate(oldToken.getEmail());
        User user = userService.findByEmail(newToken.getEmail());
        String accessToken = jwtService.generateToken(user);
        return ResponseEntity.ok(new Response(accessToken, newToken.getToken()));
    }

    @PostMapping("user/logout")
    public ResponseEntity<?> deconnexion(@RequestBody RefreshRequest request) {
        refreshService.revoke(request.getRefreshToken());
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("register")
    public String register(@RequestBody User user) {
        return this.userService.save(user);
    }
}