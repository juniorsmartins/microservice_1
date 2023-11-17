package io.pessoas_java.adapters.in.controller;

import io.pessoas_java.PessoasJavaApplication;
import io.pessoas_java.adapters.in.dto.request.EmailCadastrarDtoIn;
import io.pessoas_java.adapters.in.dto.request.TelefoneCadastrarDtoIn;
import io.pessoas_java.adapters.in.dto.response.PessoaCadastrarDtoOut;
import io.pessoas_java.adapters.out.entity.PessoaEntity;
import io.pessoas_java.adapters.out.repository.EmailRepository;
import io.pessoas_java.adapters.out.repository.EnderecoRepository;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.adapters.out.repository.TelefoneRepository;
import io.pessoas_java.application.core.domain.enums.NivelEducacionalEnum;
import io.pessoas_java.configs.AbstractIntegrationTest;
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
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.UUID;

@SpringBootTest(classes = PessoasJavaApplication.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PessoaControllerIntegrationTest extends AbstractIntegrationTest {

    public static final String END_POINT = "/api/v1/pessoas";

    public static final String UTF8 = "UTF-8";

    private static ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    private PessoaEntity pessoaEntity;

    @BeforeAll
    static void setUpGeral() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

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
    @Order(2)
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
    @Order(3)
    @DisplayName("Cadastrar - Valores Iguais")
    void deveRetornarValoresIguais_quandoCadastrar() throws Exception {

        var dtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(dtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpectAll(MockMvcResultMatchers.status().isCreated(),
                MockMvcResultMatchers.jsonPath("$.chave", Matchers.notNullValue()),
                MockMvcResultMatchers.jsonPath("$.nome", Matchers.equalToIgnoringCase(dtoIn.nome())),
                MockMvcResultMatchers.jsonPath("$.sobrenome", Matchers.equalToIgnoringCase(dtoIn.sobrenome())),
                MockMvcResultMatchers.jsonPath("$.cpf", Matchers.equalTo(dtoIn.cpf())),
                MockMvcResultMatchers.jsonPath("$.dataNascimento", Matchers.equalTo(dtoIn.dataNascimento())),
                MockMvcResultMatchers.jsonPath("$.sexo", Matchers.equalToIgnoringCase(dtoIn.sexo())),
                MockMvcResultMatchers.jsonPath("$.genero", Matchers.equalToIgnoringCase(dtoIn.genero())),
                MockMvcResultMatchers.jsonPath("$.nivelEducacional", Matchers.equalTo(NivelEducacionalEnum.MESTRADO_COMPLETO.toString())),
                MockMvcResultMatchers.jsonPath("$.nacionalidade", Matchers.equalToIgnoringCase(dtoIn.nacionalidade())))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(4)
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
    @Order(5)
    @DisplayName("Cadastrar - Persistência")
    void deveRetornarValoresIguaisPersistidos_quandoCadastrar() throws Exception {

        var pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .nome("Arie")
            .sobrenome("Van Bennekum")
            .build();

        var resposta = mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

        var responseBody = resposta.getResponse().getContentAsString();
        var pessoaDtoOut = objectMapper.readValue(responseBody, PessoaCadastrarDtoOut.class);

        var pessoaPersistida = this.pessoaRepository.findByCpf(pessoaDtoIn.cpf()).get();

        Assertions.assertNotNull(pessoaPersistida.getId());
        Assertions.assertNotNull(pessoaPersistida.getChave());
        Assertions.assertEquals(pessoaDtoOut.getNome(), pessoaPersistida.getNome());
        Assertions.assertEquals(pessoaDtoOut.getSobrenome(), pessoaPersistida.getSobrenome());
        Assertions.assertEquals(pessoaDtoOut.getCpf(), pessoaPersistida.getCpf());
        Assertions.assertEquals(pessoaDtoOut.getSexo(), pessoaPersistida.getSexo());
        Assertions.assertEquals(pessoaDtoOut.getGenero(), pessoaPersistida.getGenero());
        Assertions.assertEquals(pessoaDtoOut.getDataNascimento(), pessoaPersistida.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Assertions.assertEquals(pessoaDtoOut.getNivelEducacional(), pessoaPersistida.getNivelEducacional());
        Assertions.assertEquals(pessoaDtoOut.getNacionalidade(), pessoaPersistida.getNacionalidade());
        Assertions.assertEquals(pessoaDtoOut.getEstadoCivil(), pessoaPersistida.getEstadoCivil());
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

    @Test
    @Order(16)
    @DisplayName("Cadastrar - Telefones Iguais e Persistidos")
    void deveRetornarTelefonesIguaisAndPersistidos_quandoCadastrar() throws Exception {

        var tel1 = TelefoneCadastrarDtoIn.builder().numero("11933334444").build();
        var tel2 = TelefoneCadastrarDtoIn.builder().numero("65922227777").build();

        var dtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .telefones(Set.of(tel1, tel2))
            .build();

        var resposta = mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(dtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpectAll(MockMvcResultMatchers.status().isCreated(),
                MockMvcResultMatchers.jsonPath("$.telefones[0].numero", Matchers.notNullValue()),
                MockMvcResultMatchers.jsonPath("$.telefones[0].numero", Matchers.equalTo(tel1.numero())),
                MockMvcResultMatchers.jsonPath("$.telefones[1].numero", Matchers.notNullValue()),
                MockMvcResultMatchers.jsonPath("$.telefones[1].numero", Matchers.equalTo(tel2.numero())))
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

        var responseBody = resposta.getResponse().getContentAsString();
        var dtoOut = objectMapper.readValue(responseBody, PessoaCadastrarDtoOut.class);

        var pessoaPersistida = this.pessoaRepository.findByCpf(dtoOut.getCpf()).get();

        Assertions.assertNotNull(pessoaPersistida);
        Assertions.assertNotNull(pessoaPersistida.getTelefones());
        Assertions.assertEquals(pessoaPersistida.getTelefones().size(), 2);
    }

    @Test
    @Order(17)
    @DisplayName("Cadastrar - Http 400 por telefone inválido")
    void deveRetornarHttp400_quandoCadastrarComTelefoneInvalido() throws Exception {

        var tel1 = TelefoneCadastrarDtoIn.builder().numero("").build();

        var pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .telefones(Set.of(tel1))
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        tel1 = TelefoneCadastrarDtoIn.builder().numero("  ").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .telefones(Set.of(tel1))
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        tel1 = TelefoneCadastrarDtoIn.builder().numero("6599999").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .telefones(Set.of(tel1))
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        tel1 = TelefoneCadastrarDtoIn.builder().numero("659888877771111").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .telefones(Set.of(tel1))
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        tel1 = TelefoneCadastrarDtoIn.builder().numero("659888866tt").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .telefones(Set.of(tel1))
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
    @Order(18)
    @DisplayName("Cadastrar - Emails Iguais e Persistidos")
    void deveRetornarEmailsIguaisAndPersistidos_quandoCadastrar() throws Exception {

        var email1 = EmailCadastrarDtoIn.builder().email("teste1@email.com").build();
        var email2 = EmailCadastrarDtoIn.builder().email("teste2@email.com").build();

        var dtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .emails(Set.of(email1, email2))
            .build();

        var resposta = mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(dtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpectAll(MockMvcResultMatchers.status().isCreated(),
                MockMvcResultMatchers.jsonPath("$.emails[0].email", Matchers.notNullValue()),
                MockMvcResultMatchers.jsonPath("$.emails[0].email", Matchers.equalTo(email2.email())),
                MockMvcResultMatchers.jsonPath("$.emails[1].email", Matchers.notNullValue()),
                MockMvcResultMatchers.jsonPath("$.emails[1].email", Matchers.equalTo(email1.email())))
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

        var responseBody = resposta.getResponse().getContentAsString();
        var dtoOut = objectMapper.readValue(responseBody, PessoaCadastrarDtoOut.class);

        var pessoaPersistida = this.pessoaRepository.findByCpf(dtoOut.getCpf()).get();

        Assertions.assertNotNull(pessoaPersistida);
        Assertions.assertNotNull(pessoaPersistida.getEmails());
        Assertions.assertEquals(pessoaPersistida.getEmails().size(), 2);
    }

    @Test
    @Order(19)
    @DisplayName("Cadastrar - Http 400 por email inválido")
    void deveRetornarHttp400_quandoCadastrarComEmailInvalido() throws Exception {

        var email1 = EmailCadastrarDtoIn.builder().email(null).build();

        var pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .emails(Set.of(email1))
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        email1 = EmailCadastrarDtoIn.builder().email("").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .emails(Set.of(email1))
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        email1 = EmailCadastrarDtoIn.builder().email(" ").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .emails(Set.of(email1))
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        email1 = EmailCadastrarDtoIn.builder().email("invalid@email").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .emails(Set.of(email1))
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
    @Order(20)
    @DisplayName("Cadastrar - Endereço Igual e Persistido")
    void deveRetornarEnderecoIgualAndPersistido_quandoCadastrar() throws Exception {

        var dtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .build();

        var resposta = mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(dtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpectAll(MockMvcResultMatchers.status().isCreated(),
                MockMvcResultMatchers.jsonPath("$.endereco.pais", Matchers.equalTo(dtoIn.endereco().pais())),
                MockMvcResultMatchers.jsonPath("$.endereco.cep", Matchers.equalTo(dtoIn.endereco().cep())),
                MockMvcResultMatchers.jsonPath("$.endereco.estado", Matchers.equalTo(dtoIn.endereco().estado())),
                MockMvcResultMatchers.jsonPath("$.endereco.cidade", Matchers.equalTo(dtoIn.endereco().cidade())),
                MockMvcResultMatchers.jsonPath("$.endereco.bairro", Matchers.equalTo(dtoIn.endereco().bairro())),
                MockMvcResultMatchers.jsonPath("$.endereco.logradouro", Matchers.equalTo(dtoIn.endereco().logradouro())),
                MockMvcResultMatchers.jsonPath("$.endereco.numero", Matchers.equalTo(dtoIn.endereco().numero())),
                MockMvcResultMatchers.jsonPath("$.endereco.complemento", Matchers.equalTo(dtoIn.endereco().complemento())))
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

        var responseBody = resposta.getResponse().getContentAsString();
        var dtoOut = objectMapper.readValue(responseBody, PessoaCadastrarDtoOut.class);

        var pessoaPersistida = this.pessoaRepository.findByCpf(dtoOut.getCpf()).get();

        Assertions.assertNotNull(pessoaPersistida);
        Assertions.assertNotNull(pessoaPersistida.getEndereco());
        Assertions.assertEquals(dtoIn.endereco().pais(), pessoaPersistida.getEndereco().getPais());
        Assertions.assertEquals(dtoIn.endereco().cep(), pessoaPersistida.getEndereco().getCep());
        Assertions.assertEquals(dtoIn.endereco().estado(), pessoaPersistida.getEndereco().getEstado());
        Assertions.assertEquals(dtoIn.endereco().cidade(), pessoaPersistida.getEndereco().getCidade());
        Assertions.assertEquals(dtoIn.endereco().bairro(), pessoaPersistida.getEndereco().getBairro());
        Assertions.assertEquals(dtoIn.endereco().logradouro(), pessoaPersistida.getEndereco().getLogradouro());
        Assertions.assertEquals(dtoIn.endereco().numero(), pessoaPersistida.getEndereco().getNumero());
        Assertions.assertEquals(dtoIn.endereco().complemento(), pessoaPersistida.getEndereco().getComplemento());
    }

    @Test
    @Order(21)
    @DisplayName("Cadastrar - Http 400 por endereço inválido")
    void deveRetornarHttp400_quandoCadastrarComEnderecoInvalido() throws Exception {

        var pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .endereco(null)
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        var end1 = CriadorDeBuilders.gerarEnderecoCadastrarDtoInBuilder()
            .pais(null)
            .build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .endereco(end1)
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        end1 = CriadorDeBuilders.gerarEnderecoCadastrarDtoInBuilder()
            .pais("República Federativas do Brasil Bananolândia da Corrupção, do Estelionato e da Fraude.")
            .build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder()
            .endereco(end1)
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        end1 = CriadorDeBuilders.gerarEnderecoCadastrarDtoInBuilder()
            .cep(null).build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder().endereco(end1).build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        end1 = CriadorDeBuilders.gerarEnderecoCadastrarDtoInBuilder()
            .cep("123").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder().endereco(end1).build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        end1 = CriadorDeBuilders.gerarEnderecoCadastrarDtoInBuilder()
            .cep("12345678910444444444444441234567891044444444444444").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder().endereco(end1).build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        end1 = CriadorDeBuilders.gerarEnderecoCadastrarDtoInBuilder()
            .estado("   ").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder().endereco(end1).build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        end1 = CriadorDeBuilders.gerarEnderecoCadastrarDtoInBuilder()
            .estado("Estado da Roubalheira da Maranhão de Flávio Dinossauro da Silva").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder().endereco(end1).build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        end1 = CriadorDeBuilders.gerarEnderecoCadastrarDtoInBuilder()
            .cidade("   ").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder().endereco(end1).build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        end1 = CriadorDeBuilders.gerarEnderecoCadastrarDtoInBuilder()
            .cidade("Cidade da Maracutaia do Rio de Janeiro Controlada por Crime Organizado").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder().endereco(end1).build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        end1 = CriadorDeBuilders.gerarEnderecoCadastrarDtoInBuilder()
            .bairro(null).build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder().endereco(end1).build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        end1 = CriadorDeBuilders.gerarEnderecoCadastrarDtoInBuilder()
            .bairro("Favela da Rocinha dominada pelo crime organizado").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder().endereco(end1).build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        end1 = CriadorDeBuilders.gerarEnderecoCadastrarDtoInBuilder()
            .logradouro(null).build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder().endereco(end1).build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        end1 = CriadorDeBuilders.gerarEnderecoCadastrarDtoInBuilder()
            .logradouro("Avenida do Líder da Facção Criminosa do PCC, Fulano da Silva Beltrano, da matança e do tráfico internacional.").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder().endereco(end1).build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        end1 = CriadorDeBuilders.gerarEnderecoCadastrarDtoInBuilder()
            .numero("1234567890123456789012").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder().endereco(end1).build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        end1 = CriadorDeBuilders.gerarEnderecoCadastrarDtoInBuilder()
            .complemento("Entre pela lateral esquerda da edificação, depois dobre a direita, depois a esquerda, depois siga em frente e encontrará uma porta dourada nos fundos. Lá apertará a campainha duas vezes seguida, esperar 5 segundos, apertar mais duas vezes, então esperar 10 segundos e apertar mais uma vez rapidamente.").build();

        pessoaDtoIn = CriadorDeBuilders.gerarPessoaDtoInBuilder().endereco(end1).build();

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoIn))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());
    }




    @Test
    @Order(30)
    @DisplayName("Consultar Por Chave - Http 200")
    void deveRetornarHttp200_quandoConsultarPorChave() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT)
                .param("chave", pessoaEntity.getChave().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(31)
    @DisplayName("Consultar Por Chave - Http 400")
    void deveRetornarHttp400_quandoConsultarPorChaveInvalida() throws Exception {

        var chave = "17768530-82ef-11ee-b962-0242ac12ttt2";

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT)
                .param("chave", chave)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());
    }





    @Test
    @Order(50)
    @DisplayName("Pesquisar - Http 200")
    void deveRetornarHttp200_quandoPesquisar() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(51)
    @DisplayName("Pesquisar - retorna dois objetos")
    void deveRetornarDoisObjetos_quandoPesquisarTodos() throws Exception {

        var pessoa = CriadorDeBuilders.gerarPessoaEntityBuilder().build();
        this.pessoaRepository.save(pessoa);

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements", Matchers.equalTo(2)))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(52)
    @DisplayName("Pesquisar - por chave")
    void deveRetornarUmObjetoComChaveIgual_quandoPesquisarPorChave() throws Exception {

        var pessoa = CriadorDeBuilders.gerarPessoaEntityBuilder().build();
        this.pessoaRepository.save(pessoa);

        var chave = pessoaEntity.getChave();

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT)
                .param("chave", chave.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .accept(MediaType.APPLICATION_JSON))
            .andExpectAll(
                MockMvcResultMatchers.jsonPath("$.content[0].chave", Matchers.equalTo(chave.toString())),
                MockMvcResultMatchers.jsonPath("$.totalElements", Matchers.equalTo(1)),
                MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(53)
    @DisplayName("Pesquisar - por CPF")
    void deveRetornarUmObjetoComCPFIgual_quandoPesquisarPorCPF() throws Exception {

        var pessoa = CriadorDeBuilders.gerarPessoaEntityBuilder().build();
        this.pessoaRepository.save(pessoa);

        var cpf = pessoaEntity.getCpf();

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT)
                .param("cpf", cpf)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .accept(MediaType.APPLICATION_JSON))
            .andExpectAll(
                MockMvcResultMatchers.jsonPath("$.content[0].cpf", Matchers.equalTo(cpf)),
                MockMvcResultMatchers.jsonPath("$.totalElements", Matchers.equalTo(1)),
                MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(54)
    @DisplayName("Pesquisar - por Nacionalidade")
    void deveRetornarUmObjetoComNacionalidadeIgual_quandoPesquisarPorNacionalidade() throws Exception {

        var pessoa = CriadorDeBuilders.gerarPessoaEntityBuilder()
            .nacionalidade("Americano")
            .build();
        this.pessoaRepository.save(pessoa);

        var nacionalidade = pessoaEntity.getNacionalidade();

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT)
                .param("nacionalidade", nacionalidade)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF8)
                .accept(MediaType.APPLICATION_JSON))
            .andExpectAll(
                MockMvcResultMatchers.jsonPath("$.content[0].nacionalidade", Matchers.equalTo(nacionalidade)),
                MockMvcResultMatchers.jsonPath("$.totalElements", Matchers.equalTo(1)),
                MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }





//    @Test
//    @Order(80)
//    @DisplayName("Editar - Http 200")
//    void deveRetornarHttp200_quandoEditar() throws Exception {
//
//        var pessoaEntity = CriadorDeBuilders.gerarPessoaEntityBuilder().build();
//        var pessoaSalva = this.pessoaRepository.save(pessoaEntity);
//
//        var pessoaEditarDtoIn = CriadorDeBuilders.gerarPessoaEditarDtoInBuilder()
//            .chave(pessoaSalva.getChave())
//            .build();
//
//        mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT.concat("/") + pessoaEditarDtoIn.chave())
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding(UTF8)
//                .content(TestConverterUtil.converterObjetoParaJson(pessoaEditarDtoIn))
//                .accept(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isNoContent())
//            .andDo(MockMvcResultHandlers.print());
//    }

//    @Test
//    @Order(81)
//    @DisplayName("Editar - Valores Iguais")
//    void deveRetornarValoresIguais_quandoEditar() throws Exception {
//
//        var pessoaEntity = CriadorDeBuilders.gerarPessoaEntityBuilder().build();
//        var pessoaSalva = this.pessoaRepository.save(pessoaEntity);
//
//        var tel1 = TelefoneEditarDtoIn.builder().numero("44999990000").build();
//        var email1 = EmailEditarDtoIn.builder().email("editar@email.com.br").build();
//
//        var editarDtoIn = CriadorDeBuilders.gerarPessoaEditarDtoInBuilder()
//            .chave(pessoaSalva.getChave())
//            .nome("robert")
//            .sobrenome("martin")
//            .telefones(Set.of(tel1))
//            .emails(Set.of(email1))
//            .build();
//
//        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding(UTF8)
//                .content(TestConverterUtil.converterObjetoParaJson(editarDtoIn))
//                .accept(MediaType.APPLICATION_JSON))
//            .andExpectAll(MockMvcResultMatchers.status().isCreated(),
//                MockMvcResultMatchers.jsonPath("$.chave", Matchers.notNullValue()),
//                MockMvcResultMatchers.jsonPath("$.nome", Matchers.equalToIgnoringCase(editarDtoIn.nome())),
//                MockMvcResultMatchers.jsonPath("$.sobrenome", Matchers.equalToIgnoringCase(editarDtoIn.sobrenome())),
//                MockMvcResultMatchers.jsonPath("$.cpf", Matchers.equalTo(editarDtoIn.cpf())),
//                MockMvcResultMatchers.jsonPath("$.dataNascimento", Matchers.equalTo(editarDtoIn.dataNascimento())),
//                MockMvcResultMatchers.jsonPath("$.sexo", Matchers.equalToIgnoringCase(editarDtoIn.sexo())),
//                MockMvcResultMatchers.jsonPath("$.genero", Matchers.equalTo(editarDtoIn.genero())),
//                MockMvcResultMatchers.jsonPath("$.nivelEducacional", Matchers.equalTo(NivelEducacionalEnum.ANALFABETO.toString())),
//                MockMvcResultMatchers.jsonPath("$.nacionalidade", Matchers.equalTo(editarDtoIn.nacionalidade())),
//                MockMvcResultMatchers.jsonPath("$.estadoCivil", Matchers.equalToIgnoringCase(editarDtoIn.estadoCivil())),
//                MockMvcResultMatchers.jsonPath("$.telefones[0].numero", Matchers.equalTo(tel1.numero())),
//                MockMvcResultMatchers.jsonPath("$.emails[0].email", Matchers.equalToIgnoringCase(email1.email())),
//                MockMvcResultMatchers.jsonPath("$.endereco.pais", Matchers.equalTo(editarDtoIn.endereco().pais())),
//                MockMvcResultMatchers.jsonPath("$.endereco.cep", Matchers.equalTo(editarDtoIn.endereco().cep())),
//                MockMvcResultMatchers.jsonPath("$.endereco.estado", Matchers.equalTo(editarDtoIn.endereco().estado())),
//                MockMvcResultMatchers.jsonPath("$.endereco.cidade", Matchers.equalTo(editarDtoIn.endereco().cidade())),
//                MockMvcResultMatchers.jsonPath("$.endereco.bairro", Matchers.equalTo(editarDtoIn.endereco().bairro())),
//                MockMvcResultMatchers.jsonPath("$.endereco.logradouro", Matchers.equalTo(editarDtoIn.endereco().logradouro())),
//                MockMvcResultMatchers.jsonPath("$.endereco.numero", Matchers.equalTo(editarDtoIn.endereco().numero())),
//                MockMvcResultMatchers.jsonPath("$.endereco.complemento", Matchers.equalTo(editarDtoIn.endereco().complemento())))
//            .andDo(MockMvcResultHandlers.print());
//    }

//    @Test
//    @Order(82)
//    @DisplayName("Editar - Capitalizar nome completo")
//    void deveRetornarNomeCompletoCapitalizado_quandoEditar() throws Exception {
//
//        var pessoaEntity = CriadorDeBuilders.gerarPessoaEntityBuilder().build();
//        var pessoaSalva = this.pessoaRepository.save(pessoaEntity);
//
//        var editarDtoIn = CriadorDeBuilders.gerarPessoaEditarDtoInBuilder()
//            .chave(pessoaSalva.getChave())
//            .nome("arie")
//            .sobrenome("van bennekum")
//            .build();
//
//        var nomeCapitalizado = "Arie";
//        var sobrenomeCapitalizado = "Van Bennekum";
//
//        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding(UTF8)
//                .content(TestConverterUtil.converterObjetoParaJson(editarDtoIn))
//                .accept(MediaType.APPLICATION_JSON))
//            .andExpectAll(
//                MockMvcResultMatchers.jsonPath("$.nome", Matchers.equalTo(nomeCapitalizado)),
//                MockMvcResultMatchers.jsonPath("$.sobrenome", Matchers.equalTo(sobrenomeCapitalizado)))
//            .andDo(MockMvcResultHandlers.print());
//    }

//    @Test
//    @Order(83)
//    @DisplayName("Editar - Persistência")
//    void deveRetornarValoresIguaisPersistidos_quandoEditar() throws Exception {
//
//        var pessoaEntity = CriadorDeBuilders.gerarPessoaEntityBuilder().build();
//        var pessoaSalva = this.pessoaRepository.save(pessoaEntity);
//
//        var editarDtoIn = CriadorDeBuilders.gerarPessoaEditarDtoInBuilder()
//            .chave(pessoaSalva.getChave())
//            .nome("arie")
//            .sobrenome("van bennekum")
//            .build();
//
//        var nomeCapitalizado = "Arie";
//        var sobrenomeCapitalizado = "Van Bennekum";
//
//        var resposta = mockMvc.perform(MockMvcRequestBuilders.put(END_POINT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding(UTF8)
//                .content(TestConverterUtil.converterObjetoParaJson(editarDtoIn))
//                .accept(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andDo(MockMvcResultHandlers.print())
//            .andReturn();
//
//        var responseBody = resposta.getResponse().getContentAsString();
////        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        var pessoaDtoOut = objectMapper.readValue(responseBody, PessoaCadastrarDtoOut.class);
//
//        var pessoaPersistida = this.pessoaRepository.findByCpf(editarDtoIn.cpf()).get();
//
//        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        Assertions.assertNotNull(pessoaPersistida.getId());
//        Assertions.assertNotNull(pessoaPersistida.getChave());
//        Assertions.assertEquals(nomeCapitalizado, pessoaPersistida.getNome());
//        Assertions.assertEquals(sobrenomeCapitalizado, pessoaPersistida.getSobrenome());
//        Assertions.assertEquals(pessoaDtoOut.getCpf(), pessoaPersistida.getCpf());
//        Assertions.assertEquals(pessoaDtoOut.getSexo(), pessoaPersistida.getSexo().toString());
//        Assertions.assertEquals(pessoaDtoOut.getGenero(), pessoaPersistida.getGenero());
//        Assertions.assertEquals(pessoaDtoOut.getDataNascimento(), pessoaPersistida.getDataNascimento().format(formato));
//        Assertions.assertEquals(pessoaDtoOut.getNivelEducacional(), pessoaPersistida.getNivelEducacional().toString());
//        Assertions.assertEquals(pessoaDtoOut.getNacionalidade(), pessoaPersistida.getNacionalidade());
//        Assertions.assertEquals(pessoaDtoOut.getEstadoCivil(), pessoaPersistida.getEstadoCivil());
//    }

//    @Test
//    @Order(84)
//    @DisplayName("Editar - Http 409 por CPF não único")
//    void deveRetornarHttp409_quandoEditarComCpfNaoUnico() throws Exception {
//
//        var pessoa = CriadorDeBuilders.gerarPessoaEntityBuilder().build();
//        var pessoaSalva = this.pessoaRepository.save(pessoa);
//
//        var editarDtoIn = CriadorDeBuilders.gerarPessoaEditarDtoInBuilder()
//            .chave(pessoaSalva.getChave())
//            .cpf(pessoaEntity.getCpf())
//            .build();
//
//        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding(UTF8)
//                .content(TestConverterUtil.converterObjetoParaJson(editarDtoIn))
//                .accept(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isConflict())
//            .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    @Order(85)
//    @DisplayName("Editar - Http 400 por CPF inválido")
//    void deveRetornarHttp400_quandoEditarComCpfInválido() throws Exception {
//
//        var pessoa = CriadorDeBuilders.gerarPessoaEntityBuilder().build();
//        var pessoaSalva = this.pessoaRepository.save(pessoa);
//
//        var editarDtoIn = CriadorDeBuilders.gerarPessoaEditarDtoInBuilder()
//            .chave(pessoaSalva.getChave())
//            .cpf("88866677733")
//            .build();
//
//        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding(UTF8)
//                .content(TestConverterUtil.converterObjetoParaJson(editarDtoIn))
//                .accept(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isBadRequest())
//            .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    @Order(86)
//    @DisplayName("Editar - Http 400 nome nulo")
//    void deveRetornarHttp400_quandoEditarComNomeNulo() throws Exception {
//
//        var editarDtoIn = CriadorDeBuilders.gerarPessoaEditarDtoInBuilder()
//            .chave(pessoaEntity.getChave())
//            .nome(null)
//            .build();
//
//        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding(UTF8)
//                .content(TestConverterUtil.converterObjetoParaJson(editarDtoIn))
//                .accept(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isBadRequest())
//            .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    @Order(87)
//    @DisplayName("Editar - Http 400 sobrenome vazio")
//    void deveRetornarHttp400_quandoEditarComSobrenomeVazio() throws Exception {
//
//        var editarDtoIn = CriadorDeBuilders.gerarPessoaEditarDtoInBuilder()
//            .chave(pessoaEntity.getChave())
//            .sobrenome(" ")
//            .build();
//
//        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding(UTF8)
//                .content(TestConverterUtil.converterObjetoParaJson(editarDtoIn))
//                .accept(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isBadRequest())
//            .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    @Order(88)
//    @DisplayName("Editar - Http 400 nivel educacional nulo")
//    void deveRetornarHttp400_quandoEditarComNivelEducacionalNulo() throws Exception {
//
//        var editarDtoIn = CriadorDeBuilders.gerarPessoaEditarDtoInBuilder()
//            .chave(pessoaEntity.getChave())
//            .nivelEducacional(null)
//            .build();
//
//        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding(UTF8)
//                .content(TestConverterUtil.converterObjetoParaJson(editarDtoIn))
//                .accept(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isBadRequest())
//            .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    @Order(89)
//    @DisplayName("Editar - Http 400 nacionalidade vazio")
//    void deveRetornarHttp400_quandoEditarComNacionalidadeVazio() throws Exception {
//
//        var editarDtoIn = CriadorDeBuilders.gerarPessoaEditarDtoInBuilder()
//            .chave(pessoaEntity.getChave())
//            .nacionalidade(" ")
//            .build();
//
//        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding(UTF8)
//                .content(TestConverterUtil.converterObjetoParaJson(editarDtoIn))
//                .accept(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isBadRequest())
//            .andDo(MockMvcResultHandlers.print());
//    }




    @Test
    @Order(110)
    @DisplayName("Deletar - Http 204")
    void deveRetornarHttp204_quandoDeletar() throws Exception {

        var pessoaEntity = CriadorDeBuilders.gerarPessoaEntityBuilder().build();
        var pessoaSalva = this.pessoaRepository.save(pessoaEntity);

        mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT.concat("/") + pessoaSalva.getChave()))
            .andExpect(MockMvcResultMatchers.status().isNoContent())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(111)
    @DisplayName("Deletar - apagar no database")
    void deveConfirmarApagarNoDatabase_quandoDeletar() throws Exception {

        var listaAntes = this.pessoaRepository.findAll();
        Assertions.assertEquals(1, listaAntes.size());

        mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT.concat("/") + pessoaEntity.getChave()))
            .andExpect(MockMvcResultMatchers.status().isNoContent())
            .andDo(MockMvcResultHandlers.print());

        var listaDepois = this.pessoaRepository.findAll();
        Assertions.assertEquals(0, listaDepois.size());
    }

    @Test
    @Order(112)
    @DisplayName("Deletar - Http 404")
    void deveRetornarHttp404_quandoDeletar() throws Exception {

        var chaveFalsa = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT.concat("/") + chaveFalsa))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andDo(MockMvcResultHandlers.print());
    }
}

