package io.micro_texto.adapters.out.entity;

import io.micro_texto.adapters.out.audit.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.io.Serial;
import java.io.Serializable;

@Audited
@Entity
@Table(name = "noticias")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public final class NoticiaEntity extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chapeu", nullable = false, length = 20)
    private String chapeu;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "linha_fina", nullable = false, length = 150)
    private String linhaFina;

    @Lob
    @Column(name = "lide", nullable = false, length = 500)
    private String lide;

    @Lob
    @Column(name = "corpo", nullable = false, length = 5000)
    private String corpo;

    @Column(name = "nome_autor", length = 50)
    private String nomeAutor;

    @Column(name = "fonte", length = 250)
    private String fonte;
}

