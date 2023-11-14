package io.pessoas_java.application.core.domain.value_object;

import io.pessoas_java.config.exceptions.http_400.CpfInvalidoException;
import io.pessoas_java.config.exceptions.http_400.RequiredObjectIsNullException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(of = {"cpf"})
public final class CadastroPessoaFisica implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private String cpf;

  public CadastroPessoaFisica(String cpf) {
    if (ObjectUtils.isEmpty(cpf)) throw new RequiredObjectIsNullException();

    if (!this.ehValido(cpf)) {
      throw new CpfInvalidoException(cpf);
    }
    this.cpf = cpf;
  }

  public boolean ehValido(String cpf) {
    // Remove qualquer caractere que não seja dígito (0-9)
    cpf = cpf.replaceAll("[^0-9]", "");


    // Verifica se o CPF possui 11 dígitos após a remoção de caracteres não numéricos
    if (cpf.length() != 11) {
      return false;
    }

    // Verifica se todos os dígitos são iguais (não é um CPF válido)
    char firstDigit = cpf.charAt(0);
    for (char digit : cpf.toCharArray()) {
      if (digit != firstDigit) {
        break;  // Pelo menos um dígito é diferente
      }
    }

    // Calcula o primeiro dígito verificador
    int sum = 0;
    int weight = 10;
    for (int i = 0; i < 9; i++) {
      sum += Character.getNumericValue(cpf.charAt(i)) * weight--;
    }
    int firstVerifier = 11 - (sum % 11);

    // Verifica o primeiro dígito verificador
    if (firstVerifier > 9) {
      firstVerifier = 0;
    }
    if (Character.getNumericValue(cpf.charAt(9)) != firstVerifier) {
      return false;
    }

    // Calcula o segundo dígito verificador
    sum = 0;
    weight = 11;
    for (int i = 0; i < 10; i++) {
      sum += Character.getNumericValue(cpf.charAt(i)) * weight--;
    }
    int secondVerifier = 11 - (sum % 11);

    // Verifica o segundo dígito verificador
    if (secondVerifier > 9) {
      secondVerifier = 0;
    }
    if (Character.getNumericValue(cpf.charAt(10)) != secondVerifier) {
      return false;
    }

    // Se passou por todas as verificações, o CPF é válido
    return true;
  }

}

