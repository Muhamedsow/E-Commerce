package uidt.master.ecommerce.Authentification.token;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class RefreshRequest {
    private String refreshToken;
}