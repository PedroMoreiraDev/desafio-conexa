package com.conexa.saude.plataforma_medicos.service;

import com.conexa.saude.plataforma_medicos.exceptions.ErrorMessage;
import com.conexa.saude.plataforma_medicos.exceptions.ValidationException;
import com.conexa.saude.plataforma_medicos.model.entity.Agendamento;
import com.conexa.saude.plataforma_medicos.model.entity.Medico;
import com.conexa.saude.plataforma_medicos.model.entity.Paciente;
import com.conexa.saude.plataforma_medicos.model.entity.dto.AgendamentoDTO;
import com.conexa.saude.plataforma_medicos.repository.AgendamentoRepository;
import com.conexa.saude.plataforma_medicos.repository.MedicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final MedicoRepository medicoRepository;

    public void criarAgendamento(AgendamentoDTO agendamentoDTO, String emailMedico) {
        Medico medico = medicoRepository.findByEmail(emailMedico)
                .orElseThrow(() -> new ValidationException(ErrorMessage.USUARIO_NAO_LOCALIZADO.getCode(),
                        ErrorMessage.USUARIO_NAO_LOCALIZADO.getMessage()));

        if (agendamentoDTO.getDataHora().isBefore(LocalDateTime.now())) {
            throw new ValidationException(ErrorMessage.DATA_INVALIDA.getCode(),
                    ErrorMessage.DATA_INVALIDA.getMessage());
        }

        if (agendamentoRepository.existsByDataHoraAndMedico(agendamentoDTO.getDataHora(), medico)) {
            throw new ValidationException(ErrorMessage.HORARIO_OCUPADO.getCode(),
                    ErrorMessage.HORARIO_OCUPADO.getMessage());
        }

        Agendamento agendamento = Agendamento.builder()
                .dataHora(agendamentoDTO.getDataHora())
                .medico(medico)
                .paciente(
                        Paciente.builder()
                                .nome(agendamentoDTO.getPaciente().getNome())
                                .cpf(agendamentoDTO.getPaciente().getCpf())
                                .build()
                )
                .build();

        agendamentoRepository.save(agendamento);
    }
}
