package uidt.master.ecommerce.Authentification.token;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uidt.master.ecommerce.Authentification.exception.classe.RefreshTokenExpiredException;
import uidt.master.ecommerce.Authentification.exception.classe.RefreshTokenNotFoundException;
import uidt.master.ecommerce.Authentification.exception.classe.RefreshTokenRevokedException;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {
    private final RefreshTokenRepository repository;
    private static final long REFRESH_EXPIRATION = 1000L * 60 * 60 * 24 * 7; // 7 jours

    public RefreshToken createOrRotate(String email) {
        // Supprime ancien token pour le même utilisateur
        repository.deleteByEmail(email);
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setEmail(email);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(REFRESH_EXPIRATION));
        refreshToken.setRevoked(false);
        return repository.save(refreshToken);
    }

    public RefreshToken verify(String token) {
        RefreshToken refreshToken = repository.findByToken(token)
                .orElseThrow(RefreshTokenNotFoundException::new);
        if (refreshToken.isRevoked()) {
            throw new RefreshTokenRevokedException();
        }
        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            deleteByEmail(refreshToken.getEmail());
            throw new RefreshTokenExpiredException();
        }
        return refreshToken;
    }

    public void revoke(String token) {
        repository.findByToken(token).ifPresent(rt -> {
            rt.setRevoked(true);
            repository.save(rt);
        });
    }

    // Supprimer tous les tokens d'un utilisateur (logout)
    public void deleteByEmail(String username) {
        repository.deleteByEmail(username);
    }
}