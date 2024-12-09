package com.conexa.saude.plataforma_medicos.controller;

import com.conexa.saude.plataforma_medicos.model.entity.dto.AgendamentoDTO;
import com.conexa.saude.plataforma_medicos.service.AgendamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/attendance")
@AllArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<Void> criarAgendamento(@RequestBody @Valid AgendamentoDTO agendamentoDTO) {
        String emailMedico = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        agendamentoService.criarAgendamento(agendamentoDTO, emailMedico);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

