package com.conexa.saude.plataforma_medicos.service;

import com.auth0.jwt.interfaces.JWTVerifier;
import com.conexa.saude.plataforma_medicos.exceptions.ErrorMessage;
import com.conexa.saude.plataforma_medicos.exceptions.ValidationException;
import com.conexa.saude.plataforma_medicos.model.entity.JWTBlacklist;
import com.conexa.saude.plataforma_medicos.model.entity.Medico;
import com.conexa.saude.plataforma_medicos.model.entity.dto.LoginDTO;
import com.conexa.saude.plataforma_medicos.model.entity.dto.MedicoDTO;
import com.conexa.saude.plataforma_medicos.model.entity.dto.TokenDTO;
import com.conexa.saude.plataforma_medicos.repository.JWTBlacklistRepository;
import com.conexa.saude.plataforma_medicos.repository.MedicoRepository;
import com.conexa.saude.plataforma_medicos.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {
    private final MedicoRepository medicoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final JWTVerifier verifier;
    private final JWTBlacklistRepository jwtBlacklistRepository;


    public void register(MedicoDTO medicoDTO) {
        if (medicoRepository.existsByCpf(medicoDTO.getCpf())) {
            throw new ValidationException(ErrorMessage.CPF_MEDICO_JA_CADASTRADO.getCode(),
                    ErrorMessage.CPF_MEDICO_JA_CADASTRADO.getMessage());
        }
        if (medicoRepository.existsByEmail(medicoDTO.getEmail())) {
            throw new ValidationException(ErrorMessage.EMAIL_MEDICO_JA_CADASTRADO.getCode(),
                    ErrorMessage.EMAIL_MEDICO_JA_CADASTRADO.getMessage());
        }

        Medico medico = Medico.builder()
                .email(medicoDTO.getEmail())
                .senha(passwordEncoder.encode(medicoDTO.getSenha()))
                .especialidade(medicoDTO.getEspecialidade())
                .cpf(medicoDTO.getCpf())
                .dataNascimento(medicoDTO.getDataNascimento())
                .telefone(medicoDTO.getTelefone())
                .build();
        medicoRepository.save(medico);
    }

    public TokenDTO authenticate(LoginDTO loginDTO) {
        Medico medico = medicoRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new ValidationException(ErrorMessage.USUARIO_NAO_LOCALIZADO.getCode(),
                ErrorMessage.USUARIO_NAO_LOCALIZADO.getMessage()));

        if (!passwordEncoder.matches(loginDTO.getSenha(), medico.getSenha())) {
            throw new ValidationException(ErrorMessage.SENHA_INCORRETA.getCode(),
                    ErrorMessage.SENHA_INCORRETA.getMessage());
        }

        String token = jwtUtil.generateToken(medico.getEmail());
        return new TokenDTO(token);

    }

    public void adicionarTokenNaBlacklist(String token) {
        JWTBlacklist blacklist = new JWTBlacklist();
        blacklist.setToken(token);
        blacklist.setRevokedAt(LocalDateTime.now());
        jwtBlacklistRepository.save(blacklist);
    }

    public boolean isTokenRevogado(String token) {
        return jwtBlacklistRepository.findByToken(token).isPresent();
    }
}



