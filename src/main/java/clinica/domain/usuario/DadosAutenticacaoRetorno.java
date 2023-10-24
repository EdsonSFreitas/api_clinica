package clinica.domain.usuario;

/**
 * @author Edson da Silva Freitas
 * {@code @created} 21/05/2023
 * {@code @project} api
 */
public record DadosAutenticacaoRetorno(Long id, String login, String role) {

    public DadosAutenticacaoRetorno(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getRole().toString());
    }
}