/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

import br.com.DAO.PosicaoDAO;
import br.com.DAO.VeiculoDAO;
import br.com.negocio.Posicao;
import br.com.negocio.Veiculo;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author leonardo.rocha
 */
public class Teste_Banco_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        VeiculoDAO dao = new VeiculoDAO();
        PosicaoDAO daopos = new PosicaoDAO();
        
        Veiculo a = dao.consultarVeiculo(12);
        dao.excluir(a);
        //System.out.println("INT: "+d.getCodigo());
        //System.out.println("TAMANHO: " + a.getPosicoes().size());
    }
    
}
