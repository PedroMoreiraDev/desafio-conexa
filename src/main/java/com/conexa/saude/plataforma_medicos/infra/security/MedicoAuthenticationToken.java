package com.conexa.saude.plataforma_medicos.infra.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class MedicoAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public MedicoAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(principal, null, authorities);
    }

    public MedicoAuthenticationToken(Object principal) {
        super(principal, null, Collections.emptyList());
    }
}
