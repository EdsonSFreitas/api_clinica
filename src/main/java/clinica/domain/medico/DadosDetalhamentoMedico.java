package clinica.domain.medico;

import clinica.domain.endereco.Endereco;

/**
 * @author Edson da Silva Freitas
 * {@code @created} 20/05/2023
 * {@code @project} api
 */
public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(),medico.getEndereco());
    }
}