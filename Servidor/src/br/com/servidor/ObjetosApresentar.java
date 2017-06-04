/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;

import java.util.Calendar;

/**
 *
 * @author Usuário
 */
public class ObjetosApresentar {
    
    String placa;
    Calendar datahora;
    String status;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Calendar getDatahora() {
        return datahora;
    }

    public void setDatahora(Calendar datahora) {
        this.datahora = datahora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ObjetosApresentar(String placa, Calendar datahora, String status) {
        this.placa = placa;
        this.datahora = datahora;
        this.status = status;
    }
    
    
}
