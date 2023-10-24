package clinica.infra;

import clinica.domain.enums.RolesEnum;
import clinica.domain.usuario.Usuario;
import clinica.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static clinica.domain.enums.RolesEnum.ROLE_ADMIN;

@Configuration
@Profile("dev")
public class CargaInicial implements CommandLineRunner {

    UsuarioRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public CargaInicial(UsuarioRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        Usuario userTest = Usuario.builder()
                .id(null)
                .login("devadmin")
                .senha(passwordEncoder.encode("123456"))
                .accountExpiration(LocalDateTime.now().plusYears(1))
                .isEnabled(true)
                .isAccountLocked(false)
                .credentialsExpiration(LocalDateTime.now().plusYears(1))
                .role(ROLE_ADMIN.getCode())
                .build();

        userRepository.save(userTest);
    }
}