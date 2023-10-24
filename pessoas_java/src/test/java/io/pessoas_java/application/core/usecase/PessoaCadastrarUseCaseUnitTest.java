package io.pessoas_java.application.core.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PessoaCadastrarUseCaseUnitTest {

    @InjectMocks
    private PessoaCadastrarUseCase useCase;

    @BeforeEach
    void setUpMocks() throws Exception {

    }
}

