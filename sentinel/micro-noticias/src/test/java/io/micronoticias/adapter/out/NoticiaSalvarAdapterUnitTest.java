package io.micronoticias.adapter.out;

import io.micronoticias.adapter.out.entity.NoticiaEntity;
import io.micronoticias.adapter.out.repository.NoticiaRepository;
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

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
class NoticiaSalvarAdapterUnitTest {

    @MockBean
    private NoticiaRepository repository;

    @Autowired
    private NoticiaSalvarAdapter salvarAdapter;

    @Test
    void salvarNoticia_ComDadosValidos_RetornarNoticiaBusiness() {
        var businessIn = CriadorDeObjetos.gerarNoticiaBusiness();

        var noticiaEntity = new NoticiaEntity();
        noticiaEntity.setId(2L);
        noticiaEntity.setChapeu(businessIn.getChapeu());
        noticiaEntity.setTitulo(businessIn.getTitulo());
        noticiaEntity.setLinhaFina(businessIn.getLinhaFina());
        noticiaEntity.setLide(businessIn.getLide());
        noticiaEntity.setCorpo(businessIn.getCorpo());
        noticiaEntity.setNomeAutor(businessIn.getNomeAutor());
        noticiaEntity.setFonte(businessIn.getFonte());
        noticiaEntity.setDataHoraCriacao(Instant.now());

        Mockito.when(repository.save(Mockito.any(NoticiaEntity.class))).thenReturn(noticiaEntity);

        var resposta = this.salvarAdapter.salvar(businessIn);

        Assertions.assertAll("Asserções Salvar Adapter",
            () -> Assertions.assertNotNull(resposta.getId()),
            () -> Assertions.assertEquals(businessIn.getChapeu(), resposta.getChapeu()),
            () -> Assertions.assertEquals(businessIn.getTitulo(), resposta.getTitulo()),
            () -> Assertions.assertEquals(businessIn.getLinhaFina(), resposta.getLinhaFina()),
            () -> Assertions.assertEquals(businessIn.getLide(), resposta.getLide()),
            () -> Assertions.assertEquals(businessIn.getCorpo(), resposta.getCorpo()),
            () -> Assertions.assertEquals(businessIn.getNomeAutor(), resposta.getNomeAutor()),
            () -> Assertions.assertEquals(businessIn.getFonte(), resposta.getFonte()),
            () -> Assertions.assertEquals(noticiaEntity.getDataHoraCriacao().truncatedTo(ChronoUnit.SECONDS),
                    resposta.getDataHoraCriacao().truncatedTo(ChronoUnit.SECONDS))
        );
    }
}

