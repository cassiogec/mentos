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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuário
 */
public class UDPServiceAux extends Thread{
    
    private Integer timeexecucao;
    private Integer timestatus;

    


    
    public void run(){
        try {
            sleep(timeexecucao);
        } catch (InterruptedException ex) {
            Logger.getLogger(UDPServiceAux.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List <Veiculo> listaveiculos = new VeiculoDAO().consultarVeiculos();
        List <Posicao> posicoescadaveiculo;
        Calendar datahora_ultimainsercao = null;
        
        for (Veiculo vei : listaveiculos) {
            posicoescadaveiculo = new PosicaoDAO().consultarPosicoesCarro(vei.getCodigo());
            for (Posicao pos : posicoescadaveiculo) {
                datahora_ultimainsercao = pos.getDatahora();
            }   
            System.out.println("Thread Aux, Placa: "+vei.getPlaca()+" Data/Hota ultima insercao "+datahora_ultimainsercao);
        }
        
        
    }

    public UDPServiceAux(Integer timeexecucao, Integer timestatus) {
        this.timeexecucao = timeexecucao;
        this.timestatus = timestatus;
    }

    public Integer getTimeexecucao() {
        return timeexecucao;
    }

    public void setTimeexecucao(Integer timeexecucao) {
        this.timeexecucao = timeexecucao;
    }

    public Integer getTimestatus() {
        return timestatus;
    }

    public void setTimestatus(Integer timestatus) {
        this.timestatus = timestatus;
    }
    
}
