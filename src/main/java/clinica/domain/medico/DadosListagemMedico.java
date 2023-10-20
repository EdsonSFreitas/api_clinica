package clinica.domain.medico;

/**
 * DTO para listar somente alguns campos da Entidade Medicos
 * @author Edson da Silva Freitas
 * {@code @created} 19/05/2023
 * {@code @project} api
 */
public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(),medico.getEmail(), medico.getCrm(),medico.getEspecialidade());
    }
}