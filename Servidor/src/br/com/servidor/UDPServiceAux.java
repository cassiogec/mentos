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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuário
 */
public class UDPServiceAux extends Thread {

    private Integer atualizacao;
    private Long ciclos;

    public void run() {

        while (true) {
            try {
                sleep(atualizacao * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(UDPServiceAux.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<Veiculo> listaveiculos = new VeiculoDAO().consultarVeiculos();
            List<Posicao> posicoescadaveiculo;
            ArrayList<ObjetosApresentar> listaapresentar = new ArrayList<>();

            String placa = null;
            String status = null;
            Calendar datahora = null;
            
            for (Veiculo vei : listaveiculos)
            {
                posicoescadaveiculo = new PosicaoDAO().consultarPosicoesCarro(vei.getCodigo());
                
		if (posicoescadaveiculo.size() > 0)
                {
                    for (Posicao pos : posicoescadaveiculo) {
                        datahora = pos.getDatahora();
                    }

                    if (datahora.DAY_OF_YEAR == Calendar.getInstance().DAY_OF_YEAR)
                    {
                        if (Calendar.getInstance().getTimeInMillis() > (datahora.getTimeInMillis() + ((ciclos * atualizacao) * 1000))) {
                            status = "Fora da Area de cobertura";
                        } else if (Calendar.getInstance().getTimeInMillis() > (datahora.getTimeInMillis() + (atualizacao * 1000))) {
                            status = "Suspeito de estar fora da Area de Cobertura";
                        } else {
                            status = "Dentro da Area de Cobertura";
                        }

                        ObjetosApresentar o = new ObjetosApresentar(vei.getPlaca(), datahora, status);
                        listaapresentar.add(o);
                    }
                }
            }
            
            for (ObjetosApresentar a : listaapresentar){
                System.out.println("Veiculo: " + a.getPlaca() + " Status: " + a.getStatus());
            }
        }
    }

    public UDPServiceAux(Integer timeexecucao, Long timestatus) {
        this.atualizacao = timeexecucao;
        this.ciclos = timestatus;
    }

    public Integer getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(Integer atualizacao) {
        this.atualizacao = atualizacao;
    }

    public Long getCiclos() {
        return ciclos;
    }

    public void setCiclos(Long ciclos) {
        this.ciclos = ciclos;
    }
}
