/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.negocio;

import java.io.Serializable;
import java.sql.Array;
import java.util.Calendar;
import java.util.Calendar;
import java.util.Objects;
import static javassist.CtMethod.ConstParameter.integer;
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
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 *
 * @author leonardo.rocha
 */
@Entity
@Table(name = "POSICAO")
public class posicao implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="codvei")
    private veiculo veiculo;
    //@Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "seqpos")
    private Integer codigo;
    @Column(name = "dahpos", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar datahora;
    @Column(name = "latpos")
    private Float latitude;
    @Column(name = "lonpos")
    private Float longitude;

    public posicao(veiculo veiculo, Calendar datahora, Float latitude, Float longitude) {
        this.veiculo = veiculo;
        this.datahora = datahora;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.veiculo);
        hash = 71 * hash + Objects.hashCode(this.codigo);
        hash = 71 * hash + Objects.hashCode(this.datahora);
        hash = 71 * hash + Objects.hashCode(this.latitude);
        hash = 71 * hash + Objects.hashCode(this.longitude);
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
        final posicao other = (posicao) obj;
        if (!Objects.equals(this.veiculo, other.veiculo)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.datahora, other.datahora)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return true;
    }

    
    public posicao(veiculo veiculo, Integer codigo, Calendar datahora, Float latitude, Float longitude) {
        this.veiculo = veiculo;
        this.codigo = codigo;
        this.datahora = datahora;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public posicao(Integer codigo) {
        this.codigo = codigo;
    }

    public posicao() {
    }
    
    public veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Calendar getDatahora() {
        return datahora;
    }

    public void setDatahora(Calendar datahora) {
        this.datahora = datahora;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
    
    
}
