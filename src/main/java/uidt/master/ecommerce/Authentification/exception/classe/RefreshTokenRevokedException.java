package uidt.master.ecommerce.Authentification.exception.classe;

public class RefreshTokenRevokedException extends RuntimeException {
    public RefreshTokenRevokedException() {
        super("Refresh token has been revoked");
    }
}