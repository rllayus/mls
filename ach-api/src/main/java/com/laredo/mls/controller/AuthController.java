package com.laredo.mls.controller;

import com.laredo.UserEntity;
import com.laredo.dto.OKAuthDto;
import com.laredo.mls.config.JwtTokenProvider;
import com.laredo.dto.AuthenticationDto;
import com.laredo.service.UserService;
import com.laredo.service.client.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;
@Tag(name = "auth", description = "API para procesos de autentificación")
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final ClientService clientService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          UserService userService, ClientService clientService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.clientService =clientService;
    }

    @PostMapping("/login")
    @Operation(summary = "Autentificación de usuario",
            description = "Método para autentificar a un usuario ",
            tags = {"auth"},
            responses = {
                    @ApiResponse(description = "Operación satisfactorio", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OKAuthDto.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso no encontrado", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Fallo de autentificación", content = @Content(schema = @Schema(hidden = true))),
            })
    public ResponseEntity<OKAuthDto> signin(
            @Parameter(schema = @Schema(implementation = AuthenticationDto.class), description = "Request de autenticación")
            @RequestBody AuthenticationDto data) throws Exception {
        try {
            String token = validateAuthData(data);
            log.info("Sesión iniciada por el usuario: {}", data.getUsername());
            return ok(OKAuthDto.builder()
                    .username(data.getUsername())
                    .token(token)
                    .build());
        } catch (Exception e) {
            log.error("Error al autentificar el usuario: {}", data.getUsername(), e);
            throw e;
        }
    }
    private String validateAuthData(AuthenticationDto data) {
        String username = data.getUsername();
        UserEntity authAuthUser;
        try {
            authAuthUser = userService.findUserByUsername(data.getUsername());
            if (authAuthUser == null) {
                throw new BadCredentialsException("Username " + username + "no encontrado.");
            }
        } catch (Exception e) {
            log.error("No se encontró el usuario " + username + " Registrado en la base de datos");
            throw e;
        }

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtTokenProvider.createToken(data.getUsername());
        } catch (AuthenticationException e) {
            log.error("Error al verificar las credenciales", e);
            throw new BadCredentialsException("Las credenciales son incorrectas");
        } catch (Exception e) {
            log.error("Error de autentificacion: ", e);
            throw e;
        }
    }

}
