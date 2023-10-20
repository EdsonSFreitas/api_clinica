package clinica.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import clinica.domain.endereco.DadosEndereco;

/**
 * Classe DTO
 * @author Edson da Silva Freitas
 * {@code @created} 18/05/2023
 * {@code @project} api
 */
public record DadosCadastroPaciente(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
      //  @Pattern(regexp = "^(?!([0-9])\\1+$)\\d{10,14}$\n") //Nao permite numeros repetidos em todas as causas e limita entre 10 a 14 numeros
        String telefone,
        @NotBlank
        //  @Pattern(regexp = "^\\d{11}$\n") //Testar
        // @NotBlank @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}") //Sugestao instrutor
        String cpf,
        @NotNull
        @Valid
        DadosEndereco endereco
) {
}