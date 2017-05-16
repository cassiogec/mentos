/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;

import br.com.DAO.veiculoDAO;
import br.com.negocio.Veiculo;
import java.util.List;

/**
 *
 * @author leonardo.rocha
 */
public class Teste_Banco_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        veiculoDAO dao = new veiculoDAO();
        
        List<Veiculo> veiculos = dao.consultarVeiculos();
        System.out.println(veiculos.size());
    }
    
}
