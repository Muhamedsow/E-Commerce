package uidt.master.ecommerce.Authentification.token;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private String accessToken;
    private String refreshToken;
}