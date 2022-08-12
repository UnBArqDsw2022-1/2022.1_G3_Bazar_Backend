package com.fga.bazar.models;

import javax.persistence.*;
import java.io.Serializable;

    @Entity
    @Table(name = "pagamento")
    public abstract class Pagamento implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(nullable = false)
        private String statusPagamento ; // classe do tipo StatusPagamento, está string a nivel de exemplo

        @Column(nullable = false)
        @OneToOne
        @JoinColumn(name = "pedido_id", referencedColumnName = "id")
        private Pedido pedido;

        public abstract String gerarRelatorio();

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getStatusPagamento() {
            return statusPagamento;
        }

        public void setStatusPagamento(String statusPagamento) {
            this.statusPagamento = statusPagamento;
        }

        public Pedido getPedido() {
            return pedido;
        }

        public void setPedido(Pedido pedido) {
            this.pedido = pedido;
        }

    }