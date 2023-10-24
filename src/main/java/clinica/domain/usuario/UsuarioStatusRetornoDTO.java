package clinica.domain.usuario;

import clinica.domain.enums.RolesEnum;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Edson da Silva Freitas
 * {@code @created} 23/10/2023
 * {@code @project} api
 */


public record UsuarioStatusRetornoDTO(
        Long id, String login, LocalDateTime accountExpiration, Boolean isAccountLocked, LocalDateTime credentialsExpiration, Boolean isEnabled, RolesEnum role) {
    public UsuarioStatusRetornoDTO {
    }
}