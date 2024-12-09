package com.conexa.saude.plataforma_medicos.infra.security;

import com.conexa.saude.plataforma_medicos.service.AuthService;
import com.conexa.saude.plataforma_medicos.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.conexa.saude.plataforma_medicos.utils.Constants.ERRO_AO_PROCESSAR_TOKEN;
import static com.conexa.saude.plataforma_medicos.utils.Constants.TOKEN_INVALIDO_OU_EXPIRADO;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            if (authService.isTokenRevogado(authHeader) || !jwtUtil.validateToken(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(TOKEN_INVALIDO_OU_EXPIRADO);
                return;
            }

            String email = jwtUtil.getSubject(token);

            MedicoAuthenticationToken authentication = new MedicoAuthenticationToken(email);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(ERRO_AO_PROCESSAR_TOKEN + ex.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }
}
