package io.pessoas_java.config.exceptions;

import lombok.Getter;

@Getter
public enum TipoDeErroEnum {

  REGRA_NEGOCIO_VIOLADA("Regra de Negócio Violada.", "/regra-de-negocio-violada"),
  RECURSO_NAO_ENCONTRADO("Recurso não encontrado!", "/recurso-nao-encontrado"),
  REQUISICAO_MAL_FORMULADA("Requisição mal formulada.", "/requisicao-mal-formulada"),
  DADOS_INVALIDOS("Dados inválidos.", "/dados-invalidos"),
  MIDIA_NAO_SUPORTADA("Tipo de mídia não suportada.", "/midia-nao-suportada"),
  PROBLEMA_INTERNO_SERVIDOR("Problema interno no servidor.", "/problema-interno-servidor");

  private String titulo;

  private String caminho;

  TipoDeErroEnum(String titulo, String caminho) {
    this.titulo = titulo;
    this.caminho = "http://localhost:8101" + caminho;
  }
}

