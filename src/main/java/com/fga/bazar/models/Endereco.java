package com.fga.bazar.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private BigInteger cep;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "cidade_id", nullable = false, foreignKey = @ForeignKey(name = "ENDERECO_CIDADE_FK"))
    private Cidade cidade;

    public Endereco() {}

    public Endereco(Integer id, BigInteger cep, Integer numero, String bairro,
                    String complemento, Cidade cidade) {
        this.id = id;
        this.cep = cep;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getCep() {
        return cep;
    }

    public void setCep(BigInteger cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
