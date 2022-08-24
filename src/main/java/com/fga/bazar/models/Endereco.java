package com.fga.bazar.models;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="Endereco")
public class Endereco implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column( nullable = false  )
    BigInteger cep;

    @Column(nullable = false)
    Integer numero;

    @Column(nullable = false)
    String bairo;

    @Column(nullable = true)
    String complemento;

    @OneToOne
    Usuario usuario;

    public Endereco(BigInteger cep, Integer numero, String bairo, String complemento){
        this.cep = cep;
        this.numero = numero;
        this.bairo = bairo;
        this.complemento = complemento;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getCep() {
        return this.cep;
    }

    public void setCep(BigInteger cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return this.numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairo() {
        return this.bairo;
    }

    public void setBairo(String bairo) {
        this.bairo = bairo;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
