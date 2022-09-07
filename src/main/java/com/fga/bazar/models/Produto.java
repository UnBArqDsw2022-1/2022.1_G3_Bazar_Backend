package com.fga.bazar.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false)
    private float preco;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "categoria_id", nullable = false)
    )
    private final List<Categoria> categorias = new ArrayList<>();

    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Imagem> imagens = new ArrayList<>();

    @OneToMany(mappedBy = "id.produto")
    @JsonIgnore
    private final List<ItemPedido> itens = new ArrayList<>();

    public Produto() {}

    public Produto(Integer id, String nome, float preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

}
