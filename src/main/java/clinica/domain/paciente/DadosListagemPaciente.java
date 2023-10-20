package clinica.domain.paciente;

/**
 * DTO para listar somente alguns campos da Entidade Pacientes
 * @author Edson da Silva Freitas
 * {@code @created} 19/05/2023
 * {@code @project} api
 */
public record DadosListagemPaciente(Long id, String nome, String email, String cpf) {

    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(),paciente.getEmail(), paciente.getCpf());
    }
}