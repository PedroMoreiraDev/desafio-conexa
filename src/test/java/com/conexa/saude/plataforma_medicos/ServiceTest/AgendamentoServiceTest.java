package com.conexa.saude.plataforma_medicos.ServiceTest;

import com.conexa.saude.plataforma_medicos.exceptions.ValidationException;
import com.conexa.saude.plataforma_medicos.model.entity.Agendamento;
import com.conexa.saude.plataforma_medicos.model.entity.Medico;
import com.conexa.saude.plataforma_medicos.model.entity.dto.AgendamentoDTO;
import com.conexa.saude.plataforma_medicos.model.entity.dto.PacienteDTO;
import com.conexa.saude.plataforma_medicos.repository.AgendamentoRepository;
import com.conexa.saude.plataforma_medicos.repository.MedicoRepository;
import com.conexa.saude.plataforma_medicos.service.AgendamentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgendamentoServiceTest {

    AgendamentoDTO agendamentoDTO = AgendamentoDTO
            .builder()
            .dataHora(LocalDateTime.now().plusDays(1))
            .paciente(PacienteDTO
                    .builder()
                    .cpf("562.842.100-60")
                    .nome("paciente teste")
                    .build()).build();
    Medico medico = Medico.builder().email("medico@example.com").build();
    @Mock
    private AgendamentoRepository agendamentoRepository;
    @Mock
    private MedicoRepository medicoRepository;
    @InjectMocks
    private AgendamentoService agendamentoService;

    @Test
    void testCriarAgendamento_Success() {
        when(medicoRepository.findByEmail("medico@example.com")).thenReturn(Optional.of(medico));
        when(agendamentoRepository.existsByDataHoraAndMedico(agendamentoDTO.getDataHora(), medico)).thenReturn(false);

        agendamentoService.criarAgendamento(agendamentoDTO, "medico@example.com");

        verify(agendamentoRepository).save(any(Agendamento.class));
    }

    @Test
    void testCriarAgendamento_UserNotFound() {
        when(medicoRepository.findByEmail("medico@example.com")).thenReturn(Optional.empty());

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            agendamentoService.criarAgendamento(agendamentoDTO, "medico@example.com");
        });

        assertEquals("API-PLATAFORMA-MEDICOS-0003", exception.getCode());
        assertEquals("Usuario não localizado no banco de dados", exception.getMessage());
    }

    @Test
    void testCriarAgendamento_InvalidDate() {
        agendamentoDTO.setDataHora(LocalDateTime.now().minusDays(1));

        when(medicoRepository.findByEmail("medico@example.com")).thenReturn(Optional.of(medico));


        ValidationException exception = assertThrows(ValidationException.class, () -> {
            agendamentoService.criarAgendamento(agendamentoDTO, "medico@example.com");
        });

        assertEquals("API-PLATAFORMA-MEDICOS-0006", exception.getCode());
        assertEquals("Só é permitido agendar para datas no futuro", exception.getMessage());
    }

    @Test
    void testCriarAgendamento_HorarioOcupado() {
        when(medicoRepository.findByEmail("medico@example.com")).thenReturn(Optional.of(medico));
        when(agendamentoRepository.existsByDataHoraAndMedico(agendamentoDTO.getDataHora(), medico)).thenReturn(true);

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            agendamentoService.criarAgendamento(agendamentoDTO, "medico@example.com");
        });

        assertEquals("API-PLATAFORMA-MEDICOS-0007", exception.getCode());
        assertEquals("O horário informado já está ocupado", exception.getMessage());
    }
}
