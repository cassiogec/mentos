/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.negocio;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author leonardo.rocha
 */
@Entity
@Table(name = "VEICULOS")
public class veiculo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codvei")
    private int codigo;    
    @Column(name = "plavei")
    String placa;
    @Column(name = "tipvei")
    private int tipo;
    @Column(name = "capvei")
    private int capacidade;
    @Column(name = "ucavei")
    String uncapac;
    @OneToMany(mappedBy = "veiculo", targetEntity = posicao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     private List<posicao> posicoes;

    public veiculo(String placa, int tipo, int capacidade, String uncapac) {
        this.placa = placa;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.uncapac = uncapac;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.codigo;
        hash = 53 * hash + Objects.hashCode(this.placa);
        hash = 53 * hash + this.tipo;
        hash = 53 * hash + this.capacidade;
        hash = 53 * hash + Objects.hashCode(this.uncapac);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final veiculo other = (veiculo) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (this.capacidade != other.capacidade) {
            return false;
        }
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        if (!Objects.equals(this.uncapac, other.uncapac)) {
            return false;
        }
        return true;
    }

    public veiculo(int codigo, String placa, int tipo, int capacidade, String uncapac) {
        this.codigo = codigo;
        this.placa = placa;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.uncapac = uncapac;
    }

    public veiculo(int codigo) {
        this.codigo = codigo;
    }

    public veiculo() {
    }

    public List<posicao> getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(List<posicao> posicoes) {
        this.posicoes = posicoes;
    }
    
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getUncapac() {
        return uncapac;
    }

    public void setUncapac(String uncapac) {
        this.uncapac = uncapac;
    }
    
    
    
    
}
