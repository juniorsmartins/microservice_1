package io.pessoas_java.application.core.domain.value_object;

import io.pessoas_java.config.exceptions.http_400.TelefoneInvalidoException;

import java.io.Serial;
import java.io.Serializable;
import java.util.regex.Pattern;

public final class Telefone implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private static final Pattern VALID_PHONE_NUMBER = Pattern.compile("^\\d{10,11}$");

  private Long id;

  private String numero;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    if (!VALID_PHONE_NUMBER.matcher(numero).matches()) {
      throw new TelefoneInvalidoException(numero);
    }
    this.numero = numero;
  }
}

