package clinica.domain.paciente;

import clinica.domain.endereco.Endereco;

/**
 * @author Edson da Silva Freitas
 * {@code @created} 20/05/2023
 * {@code @project} api
 */
public record DadosDetalhamentoPaciente(Long id, String nome, String email, String telefone, String cpf, Endereco endereco, Boolean ativo) {

    public DadosDetalhamentoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco(), paciente.getAtivo());
    }
}