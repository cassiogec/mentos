/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.negocio;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author leonardo.rocha
 */
@Entity
@Table(name = "POSICAO")
public class Posicao implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="codvei")
    private Veiculo veiculo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seqpos")
    private int codigo;
    @Column(name = "dahpos", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datahora;
    @Column(name = "latpos")
    private int latitude;
    @Column(name = "lonpos")
    private int longitude;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.veiculo);
        hash = 71 * hash + this.codigo;
        hash = 71 * hash + Objects.hashCode(this.datahora);
        hash = 71 * hash + this.latitude;
        hash = 71 * hash + this.longitude;
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
        final Posicao other = (Posicao) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (this.latitude != other.latitude) {
            return false;
        }
        if (this.longitude != other.longitude) {
            return false;
        }
        if (!Objects.equals(this.veiculo, other.veiculo)) {
            return false;
        }
        if (!Objects.equals(this.datahora, other.datahora)) {
            return false;
        }
        return true;
    }

    public Posicao(Veiculo veiculo, int codigo, Date datahora, int latitude, int longitude) {
        this.veiculo = veiculo;
        this.codigo = codigo;
        this.datahora = datahora;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Posicao(Veiculo veiculo, int codigo) {
        this.veiculo = veiculo;
        this.codigo = codigo;
    }

    public Posicao() {
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
    
    
}
