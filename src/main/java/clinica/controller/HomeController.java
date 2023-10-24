package clinica.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Edson da Silva Freitas
 * {@code @created} 23/10/2023
 * {@code @project} api
 */

@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping
    public ResponseEntity<String> home() {
        String msg = "Acesso liberado sem autenticação para fins de testes";
        return ResponseEntity.ok().body(msg);
    }
}