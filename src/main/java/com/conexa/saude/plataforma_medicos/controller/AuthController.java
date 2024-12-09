package com.conexa.saude.plataforma_medicos.controller;

import com.conexa.saude.plataforma_medicos.model.entity.dto.LoginDTO;
import com.conexa.saude.plataforma_medicos.model.entity.dto.MedicoDTO;
import com.conexa.saude.plataforma_medicos.model.entity.dto.TokenDTO;
import com.conexa.saude.plataforma_medicos.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<Void> singup(@RequestBody @Valid MedicoDTO medicoDTO) {
        authService.register(medicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        TokenDTO tokenDTO = authService.authenticate(loginDTO);
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/logoff")
    public ResponseEntity<Void> logoff(@RequestHeader("Authorization") String token) {
        authService.adicionarTokenNaBlacklist(token);
        return ResponseEntity.ok().build();
    }


}
