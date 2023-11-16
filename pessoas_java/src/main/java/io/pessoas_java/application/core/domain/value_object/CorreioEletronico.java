package io.pessoas_java.application.core.domain.value_object;

import io.pessoas_java.config.exceptions.http_400.EmailInvalidoException;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

public final class CorreioEletronico implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

  private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

  private Long id;

  private String email;

  public CorreioEletronico(Long id, String email) {
    setId(id);
    setEmail(email);
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = Objects.requireNonNull(email);
    if (!ehValido()) {
      throw new EmailInvalidoException(email);
    }
  }

  public boolean ehValido() {
    return EMAIL_PATTERN.matcher(email).matches();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}

