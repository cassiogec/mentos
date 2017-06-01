/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Usuário
 */
public class ObjApresentar {
    
    private String placa;
    private Float latitude;
    private Float longitude;
    private Calendar datahora;

    public ObjApresentar() {
    }

    public ObjApresentar(String placa, Float latitude, Float longitude, Calendar datahora) {
        this.placa = placa;
        this.latitude = latitude;
        this.longitude = longitude;
        this.datahora = datahora;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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

    public Calendar getDatahora() {
        return datahora;
    }

    public void setDatahora(Calendar datahora) {
        this.datahora = datahora;
    }


    
}
