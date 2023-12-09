package io.micronoticias.application.core.usecase;

import io.micronoticias.adapter.out.NoticiaSalvarAdapter;
import io.micronoticias.application.core.domain.NoticiaBusiness;
import io.micronoticias.application.port.out.NoticiaSalvarOutputPort;
import io.micronoticias.util.CriadorDeObjetos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
class NoticiaCriarUseCaseUnitTest {

    @MockBean
    private NoticiaSalvarOutputPort salvarOutputPort;

    @MockBean
    public NoticiaSalvarAdapter salvarAdapter;

    @Autowired
    private NoticiaCriarUseCase criarUseCase;

    @Test
    void criarNoticia_ComDadosValidos_RetornarNoticiaBusiness() {

        var business = CriadorDeObjetos.gerarNoticiaBusiness();
        business.setId(2L);
        business.setDataHoraCriacao(Instant.now());

        Mockito.when(salvarOutputPort.salvar(Mockito.any(NoticiaBusiness.class))).thenReturn(business);

        var resposta = this.criarUseCase.criar(business);

        Assertions.assertNotNull(resposta.getId());
        Assertions.assertEquals(business.getChapeu(), resposta.getChapeu());
        Assertions.assertEquals(business.getTitulo(), resposta.getTitulo());
        Assertions.assertEquals(business.getLinhaFina(), resposta.getLinhaFina());
        Assertions.assertEquals(business.getLide(), resposta.getLide());
        Assertions.assertEquals(business.getCorpo(), resposta.getCorpo());
        Assertions.assertEquals(business.getNomeAutor(), resposta.getNomeAutor());
        Assertions.assertEquals(business.getFonte(), resposta.getFonte());
        Assertions.assertEquals(business.getDataHoraCriacao().truncatedTo(ChronoUnit.SECONDS),
                resposta.getDataHoraCriacao().truncatedTo(ChronoUnit.SECONDS));
    }
}

