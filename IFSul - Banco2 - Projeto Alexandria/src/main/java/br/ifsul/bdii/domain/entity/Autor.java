package br.ifsul.bdii.domain.entity;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "autor_id")
    private Long id;

    @Column (name = "nome")
    private String nome;

    @Column (name = "biografia")
    private String biografia;

    @Column (name = "nacionalidade")
    private String nacionalidade;

    @Column (name = "data_nascimento")
    private Date dataNascimento;
    
    @Column (name = "data_morte")
    private Date dataMorte;
     
}
