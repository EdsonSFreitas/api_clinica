package clinica.controller;

import clinica.domain.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Controller para criação de usuarios em uma sessão autenticada
 *
 * @author Edson da Silva Freitas
 * {@code @created} 21/05/2023
 * {@code @project} api clinica
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<DadosAutenticacaoRetorno> registraUsuario(@RequestBody @Valid DadosAutenticacao dados) {
        final Usuario usuario = service.createUsuario(
                new Usuario(dados.login(),
                        passwordEncoder.encode(dados.senha())));
        return ok(new DadosAutenticacaoRetorno(usuario.getId(), usuario.getLogin()));
    }

    @GetMapping
    public ResponseEntity<Page<DadosAutenticacaoRetorno>> findAllUsuarios(
            @PageableDefault(size = 30, page = 0, sort = {"id"})
            Pageable paginacao) {
        var page = repository.findAll(paginacao);
        return ResponseEntity.ok(page.map(DadosAutenticacaoRetorno::new));
    }
}