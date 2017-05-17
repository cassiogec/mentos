/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

import br.com.DAO.posicaoDAO;
import br.com.DAO.veiculoDAO;
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
        veiculoDAO dao = new veiculoDAO();
        posicaoDAO daopos = new posicaoDAO();
        
        List<Veiculo> veiculos = dao.consultarVeiculos();
        System.out.println(veiculos.size());
        
        Veiculo a = dao.consultarVeiculo(1);
        Posicao d = new Posicao(a,Calendar.getInstance(),0,0);
        System.out.println("INT: "+d.getCodigo());
        daopos.incluir(d);
        
    }
    
}
