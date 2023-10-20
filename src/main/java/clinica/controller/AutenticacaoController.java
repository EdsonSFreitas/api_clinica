package clinica.controller;

import clinica.domain.usuario.DadosAutenticacao;
import clinica.domain.usuario.Usuario;
import clinica.security.DadosTokenJWT;
import clinica.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
 * Controller de autenticacao que chama o autenticate do AuthenticationManager
 * O token é gerado pelo UsernamePasswordAuthenticationToken passando login e senha
 * Necessario dar permissao para carregar o AuthenticationManager na classe SecurityConfigurations usando @Bean
 *
 * @author Edson da Silva Freitas
 * {@code @created} 21/05/2023
 * {@code @project} api
 */
@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    /*
    Não posso chamar o método loadUserByUsername do AutenticacaoService.class diretamente,
    preciso instanciar o AuthenticationManager com @Autowired e ela irá chamar a minha AutenticacaoService.class automaticamente
     */
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService; //TokenService do projeto, não do Spring que tem mesmo nome

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken); //Dispara o processo de autenticacao
        //return ResponseEntity.ok(authenticationToken.gerarToken((Usuario) authentication.getPrincipal())); // Dessse modo retorna o token gerado sem um DTO

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @GetMapping
    public ResponseEntity<String> home() {
        String msg = "Acesso liberado sem autenticação para fins de testes";
        return ResponseEntity.ok().body(msg);
    }
}