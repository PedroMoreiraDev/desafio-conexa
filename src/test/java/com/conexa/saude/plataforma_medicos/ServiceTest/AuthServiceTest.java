package com.conexa.saude.plataforma_medicos.ServiceTest;

import com.conexa.saude.plataforma_medicos.enums.EspecialidadeEnum;
import com.conexa.saude.plataforma_medicos.exceptions.ErrorMessage;
import com.conexa.saude.plataforma_medicos.exceptions.ValidationException;
import com.conexa.saude.plataforma_medicos.model.entity.Medico;
import com.conexa.saude.plataforma_medicos.model.entity.dto.LoginDTO;
import com.conexa.saude.plataforma_medicos.model.entity.dto.TokenDTO;
import com.conexa.saude.plataforma_medicos.repository.MedicoRepository;
import com.conexa.saude.plataforma_medicos.service.AuthService;
import com.conexa.saude.plataforma_medicos.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    LoginDTO loginDTO = new LoginDTO("medico@exemplo.com", "senha123");
    Medico medico = Medico.builder()
            .id(1L)
            .email(loginDTO.getEmail())
            .senha("senhaCodificada")
            .especialidade(EspecialidadeEnum.CARDIOLOGISTA)
            .cpf("123.456.789-00")
            .dataNascimento(LocalDate.of(1980, 1, 1))
            .telefone("(11) 98765-4321")
            .build();
    @InjectMocks
    private AuthService authService;
    @Mock
    private MedicoRepository medicoRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtUtil jwtUtil;

    @Test
    void deveAutenticarComSucesso() {

        when(medicoRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(medico));
        when(passwordEncoder.matches(loginDTO.getSenha(), "senhaCodificada")).thenReturn(true);
        when(jwtUtil.generateToken(medico.getEmail())).thenReturn("tokenGerado");

        TokenDTO tokenDTO = authService.authenticate(loginDTO);

        assertNotNull(tokenDTO);
        assertEquals("tokenGerado", tokenDTO.getToken());

        verify(medicoRepository).findByEmail(loginDTO.getEmail());
        verify(passwordEncoder).matches(loginDTO.getSenha(), "senhaCodificada");
        verify(jwtUtil).generateToken(medico.getEmail());
    }


    @Test
    void deveLancarExcecaoQuandoEmailNaoEncontrado() {

        when(medicoRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.empty());

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            authService.authenticate(loginDTO);
        });

        assertEquals(ErrorMessage.USUARIO_NAO_LOCALIZADO.getCode(), exception.getCode());
        assertEquals(ErrorMessage.USUARIO_NAO_LOCALIZADO.getMessage(), exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoSenhaIncorreta() {


        when(medicoRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(medico));
        when(passwordEncoder.matches(loginDTO.getSenha(), medico.getSenha())).thenReturn(false);

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            authService.authenticate(loginDTO);
        });

        assertEquals(ErrorMessage.SENHA_INCORRETA.getCode(), exception.getCode());
        assertEquals(ErrorMessage.SENHA_INCORRETA.getMessage(), exception.getMessage());
    }


    @Test
    void testAuthenticate_Success() {
        when(medicoRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(medico));
        when(passwordEncoder.matches(loginDTO.getSenha(), medico.getSenha())).thenReturn(true);
        when(jwtUtil.generateToken(medico.getEmail())).thenReturn("mockedToken");

        TokenDTO result = authService.authenticate(loginDTO);

        assertNotNull(result);
        assertEquals("mockedToken", result.getToken());
        verify(medicoRepository).findByEmail(loginDTO.getEmail());
        verify(passwordEncoder).matches(loginDTO.getSenha(), medico.getSenha());
        verify(jwtUtil).generateToken(medico.getEmail());
    }

    @Test
    void testAuthenticate_UserNotFound() {
        when(medicoRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.empty());

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            authService.authenticate(loginDTO);
        });

        assertEquals(ErrorMessage.USUARIO_NAO_LOCALIZADO.getCode(), exception.getCode());
        assertEquals(ErrorMessage.USUARIO_NAO_LOCALIZADO.getMessage(), exception.getMessage());
    }

    @Test
    void testAuthenticate_IncorrectPassword() {
        when(medicoRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(medico));
        when(passwordEncoder.matches(loginDTO.getSenha(), medico.getSenha())).thenReturn(false);

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            authService.authenticate(loginDTO);
        });

        assertEquals(ErrorMessage.SENHA_INCORRETA.getCode(), exception.getCode());
        assertEquals(ErrorMessage.SENHA_INCORRETA.getMessage(), exception.getMessage());
    }


}

