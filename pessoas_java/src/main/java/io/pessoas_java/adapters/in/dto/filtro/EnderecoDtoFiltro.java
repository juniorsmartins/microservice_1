package io.pessoas_java.adapters.in.dto.filtro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class EnderecoDtoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String pais;

    private String estado;

    private String cidade;
}

