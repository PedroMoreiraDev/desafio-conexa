package com.conexa.saude.plataforma_medicos.utils;


public class Constants {

    public static final String ERRO_AO_PROCESSAR_TOKEN = "Erro ao processar o token:";
    public static final String TOKEN_INVALIDO_OU_EXPIRADO = "Token invalido ou expirado";
    public static final String AUTH_API = "auth-api";
    private static final long MILISSEGUNDOS_EM_UM_SEGUNDO = 1000;
    private static final long SEGUNDOS_EM_UM_MINUTO = 60;
    public static final long TEMPO_EXPIRACAO_TOKEN_15_MINUTOS =
            15 * SEGUNDOS_EM_UM_MINUTO * MILISSEGUNDOS_EM_UM_SEGUNDO;
    private static final long MINUTOS_EM_UMA_HORA = 60;
    private static final long HORAS_EM_UM_DIA = 24;
    public static final long TEMPO_EXPIRACAO_REFRESH_TOKEN_1_DIA = HORAS_EM_UM_DIA * MINUTOS_EM_UMA_HORA
            * SEGUNDOS_EM_UM_MINUTO * MILISSEGUNDOS_EM_UM_SEGUNDO;


    private Constants() {
        throw new UnsupportedOperationException("Esta classe não pode ser instanciada");
    }

}
