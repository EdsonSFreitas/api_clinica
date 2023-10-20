package clinica.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe com logica de autenticacao do projeto e implementa UserDetailsService
 * Sprint ira carregar automaticamente essa classe por ter @Service e implementar UserDetailsSerivce
 * @author Edson da Silva Freitas
 * {@code @created} 21/05/2023
 * {@code @project} api
 */
@Service //Por ser de um serviço de autenticação
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario createUsuario(Usuario usuario) {
        if(!repository.existsByLogin(usuario.getLogin())){
            return repository.save(usuario);
        }else {
            throw new RuntimeException("Login name unavailable");
        }
    }
}