/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

import br.com.DAO.posicaoDAO;
import br.com.DAO.veiculoDAO;
import br.com.negocio.posicao;
import br.com.negocio.veiculo;
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
        
        veiculo f = new veiculo("AAAAACA",1,0,"AAAAA");
        if (!dao.incluir(f))
            System.out.println("OI");
         f = new veiculo("AAAAADA",1,0,"AAAAA");
        if (!dao.incluir(f))
            System.out.println("OI");
        List<veiculo> veiculos = dao.consultarVeiculos();
        System.out.println(veiculos.size());
        
        veiculo a = dao.consultarVeiculo(12);
        posicao g = daopos.consultarPosicao(12,9);
        //g.setLatitude(1);
        posicao d = new posicao(a,Calendar.getInstance(),new Float(0),new Float(0));      
        daopos.incluir(d);
        //System.out.println("INT: "+d.getCodigo());
        //System.out.println("TAMANHO: " + a.getPosicoes().size());
    }
    
}
