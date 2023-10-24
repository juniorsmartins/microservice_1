package io.pessoas_java.adapters.in.controller;

import io.pessoas_java.PessoasJavaApplication;
import io.pessoas_java.adapters.out.entity.PessoaEntity;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.util.CriadorDeBuilders;
import io.pessoas_java.util.TestConverterUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = PessoasJavaApplication.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PessoaControllerIntegrationTest {

    public static final String END_POINT = "/api/v1/pessoas";

    public static final String UTF8 = "UTF-8";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PessoaRepository pessoaRepository;

    private PessoaEntity pessoaEntity;

    @BeforeEach
    void setUp() {

        pessoaEntity = CriadorDeBuilders.gerarPessoaEntityBuilder().build();
        pessoaEntity = this.pessoaRepository.save(pessoaEntity);
    }

    @AfterEach
    void tearDown() {

        this.pessoaRepository.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("Cadastrar - Http 201")
    void deveRetornarHttp201_quandoCadastrar() throws Exception {

        var pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder().build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(2)
    @DisplayName("Cadastrar - Valores Iguais")
    void deveRetornarValoresIguais_quandoCadastrar() throws Exception {

        var dtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder().build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(dtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpectAll(MockMvcResultMatchers.status().isCreated(),
                MockMvcResultMatchers.jsonPath("$.chave", Matchers.notNullValue()),
                MockMvcResultMatchers.jsonPath("$.nome", Matchers.equalToIgnoringCase(dtoIn.nome())),
                MockMvcResultMatchers.jsonPath("$.sobrenome", Matchers.equalTo(dtoIn.sobrenome())),
                MockMvcResultMatchers.jsonPath("$.cpf", Matchers.equalTo(dtoIn.cpf())),
                MockMvcResultMatchers.jsonPath("$.dataNascimento", Matchers.equalTo(dtoIn.dataNascimento())),
                MockMvcResultMatchers.jsonPath("$.sexo", Matchers.equalTo(dtoIn.sexo())),
                MockMvcResultMatchers.jsonPath("$.genero", Matchers.equalTo(dtoIn.genero())),
                MockMvcResultMatchers.jsonPath("$.nivelEducacional", Matchers.equalTo(dtoIn.nivelEducacional())),
                MockMvcResultMatchers.jsonPath("$.nacionalidade", Matchers.equalTo(dtoIn.nacionalidade())))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(3)
    @DisplayName("Cadastrar - Capitalizar nome completo")
    void deveRetornarNomeCompletoCapitalizado_quandoCadastrar() throws Exception {

        var pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .nome("arie")
            .sobrenome("van bennekum")
            .build();

        var nomeCapitalizado = "Arie";
        var sobrenomeCapitalizado = "Van Bennekum";

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpectAll(
                MockMvcResultMatchers.jsonPath("$.nome", Matchers.equalTo(nomeCapitalizado)),
                MockMvcResultMatchers.jsonPath("$.sobrenome", Matchers.equalTo(sobrenomeCapitalizado)))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(4)
    @DisplayName("Cadastrar - Persistência")
    void deveRetornarValoresIguaisPersistidos_quandoCadastrar() throws Exception {

        var pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .nome("Arie")
            .sobrenome("Van Bennekum")
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andDo(MockMvcResultHandlers.print());

        var pessoaPersistida = this.pessoaRepository.findByCpf(pessoaDtoIn.cpf()).get();

        Assertions.assertNotNull(pessoaPersistida.getId());
        Assertions.assertNotNull(pessoaPersistida.getChave());
        Assertions.assertEquals(pessoaDtoIn.nome(), pessoaPersistida.getNome());
        Assertions.assertEquals(pessoaDtoIn.sobrenome(), pessoaPersistida.getSobrenome());
        Assertions.assertEquals(pessoaDtoIn.cpf(), pessoaPersistida.getCpf());
        Assertions.assertEquals(pessoaDtoIn.sexo(), pessoaPersistida.getSexo());
        Assertions.assertEquals(pessoaDtoIn.genero(), pessoaPersistida.getGenero());
        Assertions.assertEquals(pessoaDtoIn.dataNascimento(), pessoaPersistida.getDataNascimento());
        Assertions.assertEquals(pessoaDtoIn.nivelEducacional(), pessoaPersistida.getNivelEducacional());
        Assertions.assertEquals(pessoaDtoIn.nacionalidade(), pessoaPersistida.getNacionalidade());
    }

    @Test
    @Order(10)
    @DisplayName("Cadastrar - Http 409 por CPF não único")
    void deveRetornarHttp409_quandoCadastrarComCpfNaoUnico() throws Exception {

        var pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .cpf(pessoaEntity.getCpf())
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isConflict())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(11)
    @DisplayName("Cadastrar - Http 400 por CPF inválido")
    void deveRetornarHttp400_quandoCadastrarComCpfInválido() throws Exception {

        var pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .cpf("99944455577")
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(12)
    @DisplayName("Cadastrar - Http 400 nome nulo")
    void deveRetornarHttp400_quandoCadastrarComNomeNulo() throws Exception {

        var pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .nome(null)
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(13)
    @DisplayName("Cadastrar - Http 400 sobrenome vazio")
    void deveRetornarHttp400_quandoCadastrarComSobrenomeVazio() throws Exception {

        var pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .sobrenome(" ")
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(14)
    @DisplayName("Cadastrar - Http 400 nivel educacional nulo")
    void deveRetornarHttp400_quandoCadastrarComNivelEducacionalNulo() throws Exception {

        var pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .nivelEducacional(null)
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(15)
    @DisplayName("Cadastrar - Http 400 nacionalidade vazio")
    void deveRetornarHttp400_quandoCadastrarComNacionalidadeVazio() throws Exception {

        var pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .nacionalidade(" ")
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());
    }



//    @Test
//    void pesquisar() {
//    }


    @Test
    @Order(50)
    @DisplayName("Editar - Http 200")
    void deveRetornarHttp200_quandoEditar() throws Exception {

        var pessoaEntity = CriadorDeBuilders.gerarPessoaEntityBuilder().build();
        var pessoaSalva = this.pessoaRepository.save(pessoaEntity);

        var pessoaEditarDtoIn = CriadorDeBuilders.gerarPessoaEditarDtoInBuilder()
            .chave(pessoaSalva.getChave())
            .build();

        mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT.concat("/") + pessoaEditarDtoIn.chave())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaEditarDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNoContent())
            .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @Order(80)
    @DisplayName("Deletar - Http 204")
    void deveRetornarHttp204_quandoDeletar() throws Exception {

        var pessoaEntity = CriadorDeBuilders.gerarPessoaEntityBuilder().build();
        var pessoaSalva = this.pessoaRepository.save(pessoaEntity);

        mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT.concat("/") + pessoaSalva.getChave()))
            .andExpect(MockMvcResultMatchers.status().isNoContent())
            .andDo(MockMvcResultHandlers.print());
    }
}

