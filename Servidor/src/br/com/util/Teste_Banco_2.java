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
        
        Veiculo f = new Veiculo("AAAAACA",1,0,"AAAAA");
        if (!dao.incluir(f))
            System.out.println("OI");
         f = new Veiculo("AAAAADA",1,0,"AAAAA");
        if (!dao.incluir(f))
            System.out.println("OI");
        List<Veiculo> veiculos = dao.consultarVeiculos();
        System.out.println(veiculos.size());
        
        Veiculo a = dao.consultarVeiculo(12);
        Posicao g = daopos.consultarPosicao(12,9);
        //g.setLatitude(1);
        Posicao d = new Posicao(a,Calendar.getInstance(),new Float(0),new Float(0));      
        daopos.incluir(d);
        //System.out.println("INT: "+d.getCodigo());
        //System.out.println("TAMANHO: " + a.getPosicoes().size());
    }
    
}
