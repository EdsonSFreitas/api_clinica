package clinica.domain.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Entidade Usuario para autenticação/autorização
 * @author Edson da Silva Freitas
 * {@code @created} 21/05/2023
 * {@code @project} api
 */
@Entity(name = "Usuario") //Adotar mesmo nome da classe
@Table(name = "usuarios") //Minusculo e no plural com sera no banco
@Getter //Lombok irá gerar os getters
@NoArgsConstructor //Lombok irá criar um construtor sem argumento
@AllArgsConstructor //Lombok irá criar um construtor com todos os argumentos
@Builder
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String login;
    private String senha;

    public Usuario(String login, String password) {
        this.login = login;
        this.senha = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        //Se tivesse perfis de acesso eu deveria ter uma colletion com todos os perfis
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}