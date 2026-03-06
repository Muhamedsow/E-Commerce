package uidt.master.ecommerce.Authentification.exception.classe;

public class RefreshTokenNotFoundException extends RuntimeException{
    public RefreshTokenNotFoundException() {
        super("Refresh token not found");
    }
}