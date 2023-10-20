package clinica.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Repository do Usuario, cada entidade deve ter seu proprio Repository
 * @author Edson da Silva Freitas
 * {@code @created} 21/05/2023
 * {@code @project} api
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
    Page<Usuario> findAll(Pageable paginacao);

    boolean existsByLogin(String login);
}