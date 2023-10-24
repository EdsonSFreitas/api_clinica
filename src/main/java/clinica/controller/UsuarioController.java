package clinica.controller;

import clinica.domain.usuario.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        return ok(new DadosAutenticacaoRetorno(usuario.getId(), usuario.getLogin(), usuario.getRole().toString()));
    }

    @GetMapping
    public ResponseEntity<Page<DadosAutenticacaoRetorno>> findAllUsuarios(
            @PageableDefault(size = 30, page = 0, sort = {"id"})
            Pageable paginacao) {
        var page = repository.findAll(paginacao);
        return ResponseEntity.ok(page.map(DadosAutenticacaoRetorno::new));
    }

    @Operation(summary = "Change user status", description = "Block or Expire credential or account an existing user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Action completed successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })

    @RolesAllowed("ADMIN")
    @PatchMapping("/changestatus")
    public ResponseEntity<Optional<UsuarioStatusRetornoDTO>> changeStatusUserById(@RequestBody UsuarioStatusUpdateDTO updateStatus) {
        final Optional<UsuarioStatusRetornoDTO> userStatusRetornoDTO = service.updateUsuario(updateStatus.getId(), updateStatus);
        return ResponseEntity.ok().body(userStatusRetornoDTO);
    }
}