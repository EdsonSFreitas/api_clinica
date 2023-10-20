package clinica.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * @author Edson da Silva Freitas
 * {@code @created} 18/05/2023
 * {@code @project} api
 */
public record DadosEndereco(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z]{2}$") //Permite apenas 2 letras
        String uf,
        String complemento,
        String numero
) {
}