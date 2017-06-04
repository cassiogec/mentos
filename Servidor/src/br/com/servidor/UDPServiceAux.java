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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
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
        
        while(true){
            try {
                sleep(timeexecucao*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(UDPServiceAux.class.getName()).log(Level.SEVERE, null, ex);
            }

            List <Veiculo> listaveiculos = new VeiculoDAO().consultarVeiculos();
            List <Posicao> posicoescadaveiculo;
            ArrayList <ObjetosApresentar> listaapresentar = new ArrayList<>();
            
            String placa = null;
            String status = null;
            Calendar datahora = null;
            //System.out.println("Tamnha da lista de veiculo"+listaveiculos.size());
            for (Veiculo vei : listaveiculos) {
        
                posicoescadaveiculo = new PosicaoDAO().consultarPosicoesCarro(vei.getCodigo());
                //System.out.println("Veiculo placa "+vei.getPlaca()+" Tamanho da lista de Posicoes "+posicoescadaveiculo.size());
                if(posicoescadaveiculo.size()>0){
                    
                    for (Posicao pos : posicoescadaveiculo) {
                        datahora = pos.getDatahora();
                        System.out.println("Placa "+vei.getPlaca()+" DataHora: "+datahora);
                    }
                   
                    if(datahora.DAY_OF_YEAR == Calendar.getInstance().DAY_OF_YEAR){
                        if(Calendar.getInstance().getTimeInMillis() < datahora.getTimeInMillis()+ timestatus){
                            status = "Suspeito de estar fora da Area de Cobertura";
                        } else {
                            status = "Fora da Area de cobertura";
                        }
                        ObjetosApresentar o = new ObjetosApresentar(vei.getPlaca(), datahora, status);
                        listaapresentar.add(o);
                    }
                    else{
                        System.out.println("Data/Hora da Posicao do Veiculo fora da especificacao trabalho");
                    }
                     
                }    
            }
            
            for (ObjetosApresentar a : listaapresentar){
                
                System.out.println("Veiculo: "+a.getPlaca()+" Status: "+a.getStatus());
                
            }

            System.out.println("Fim da Execucao da Atualizacao de Status dos Veiculos");
        }
        
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

