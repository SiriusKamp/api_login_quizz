package testedeapi.com.controllers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testedeapi.com.dto.UserLoginDto;
import testedeapi.com.dto.AuthResponseDto;
import testedeapi.com.service.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody UserLoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.registroAcademico(), loginDto.password())
        );

        if (!authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body(new AuthResponseDto("Erro: Autenticação falhou", null));
        }
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.registroAcademico());
        String token = jwtService.generateToken(userDetails);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return ResponseEntity.ok()
            .headers(headers)
            .body(new AuthResponseDto("Autenticação bem-sucedida", token));
        
    }
}
