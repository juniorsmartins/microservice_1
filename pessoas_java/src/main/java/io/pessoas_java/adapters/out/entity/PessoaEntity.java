package io.pessoas_java.adapters.out.entity;

import io.pessoas_java.config.security.entity.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pessoas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@EntityListeners(AuditingEntityListener.class)
public final class PessoaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chave", nullable = false, unique = true, updatable = false)
    private UUID chave;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 50)
    private String sobrenome;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private String dataNascimento;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "nivel_educacional", nullable = false)
    private String nivelEducacional;

    @Column(name = "nacionalidade", nullable = false)
    private String nacionalidade;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", unique = true)
    private UsuarioEntity usuario;

    // Auditoria

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @CreatedBy
    @Column(name = "criado_por")
    private String criadoPor;

    @LastModifiedDate
    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;

    @LastModifiedBy
    @Column(name = "modificado_por")
    private String modificadoPor;
}

