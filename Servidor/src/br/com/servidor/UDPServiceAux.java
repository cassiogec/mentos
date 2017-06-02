/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;

import br.com.DAO.PosicaoDAO;
import br.com.DAO.VeiculoDAO;
import br.com.negocio.Posicao;
import br.com.negocio.Veiculo;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuário
 */
public class UDPServiceAux extends Thread{
    
    private Integer timeexecucao;
    private Long timestatus;

    public void run(){
        try {
            sleep(timeexecucao);
        } catch (InterruptedException ex) {
            Logger.getLogger(UDPServiceAux.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List <Veiculo> listaveiculos = new VeiculoDAO().consultarVeiculos();
        List <Posicao> posicoescadaveiculo;
        
        String placa = null;
        String status = null;
        Calendar datahora = Calendar.getInstance();
        Calendar datahora_atual = Calendar.getInstance();
        
        for (Veiculo vei : listaveiculos) {
            posicoescadaveiculo = new PosicaoDAO().consultarPosicoesCarro(vei.getCodigo());
            for (Posicao pos : posicoescadaveiculo) {
                datahora = pos.getDatahora();
            }

            if(datahora_atual.getTimeInMillis() < datahora.getTimeInMillis()+ timestatus){
                status = "Veículo Suspeito de estar fora da area de cobertura";
            } else {
                status = "Veículo fora da área de cobertura";
            }
            System.out.println("Placa: "+vei.getPlaca()+" Status: "+status+"Data/Hora "+datahora);
               
        }
        
        System.out.println("executou a segunda thread, Data Hora "+datahora);
        
    }

    public UDPServiceAux(Integer timeexecucao, Long timestatus) {
        this.timeexecucao = timeexecucao;
        this.timestatus = timestatus;
    }

    public Integer getTimeexecucao() {
        return timeexecucao;
    }

    public void setTimeexecucao(Integer timeexecucao) {
        this.timeexecucao = timeexecucao;
    }

    public Long getTimestatus() {
        return timestatus;
    }

    public void setTimestatus(Long timestatus) {
        this.timestatus = timestatus;
    }


    
}


