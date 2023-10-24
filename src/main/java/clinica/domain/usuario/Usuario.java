package clinica.domain.usuario;

import clinica.domain.enums.RolesEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Entidade Usuario para autenticação/autorização
 * @author Edson da Silva Freitas
 * {@code @created} 21/05/2023
 * {@code @project} api
 */
@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String login;
    private String senha;

    @Column(name = "is_enabled")
    private boolean isEnabled = true;

    @Column(name = "is_account_locked")
    private boolean isAccountLocked = false;

    @Column(name = "credentials_expiration")
    private LocalDateTime credentialsExpiration;

    @Column(name = "account_expiration")
    private LocalDateTime accountExpiration;

    private Integer role = 1;


    public RolesEnum getRole() {
        return RolesEnum.valueOf(role);
    }

    public void setRole(RolesEnum role) {
        if (role != null) {
            this.role = role.getCode();
        }
    }

    public Usuario(String login, String password) {
        this.login = login;
        this.senha = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole().toString()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountExpiration == null || !accountExpiration.isBefore(LocalDateTime.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isAccountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsExpiration == null || !credentialsExpiration.isBefore(LocalDateTime.now());
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}