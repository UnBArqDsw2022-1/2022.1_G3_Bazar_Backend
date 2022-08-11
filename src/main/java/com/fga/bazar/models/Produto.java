package com.fga.bazar.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fga.bazar.models.composite.ProdutoComponent;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto extends ProdutoComponent implements Serializable {

    @Override
    public float getPreco() {
        return preco;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false)
    private float preco;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "produto-categorias",
            joinColumns = @JoinColumn(name = "produto_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "categoria_id", nullable = false)
    )
    private final List<Categoria> categorias = new ArrayList<>();

    @OneToMany(mappedBy = "id.produto")
    private List<ItemPedido> itens = new ArrayList<>();

    public List<Categoria> getCategorias() {
        return categorias;
    }
    
    public Produto(){

    }

    public Produto(Integer id, String nome, float preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
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



    public void setPreco(float preco) {
        this.preco = preco;
    }

    @JsonIgnore
    public List<ItemPedido> getItens() {
        return itens;
    }

}