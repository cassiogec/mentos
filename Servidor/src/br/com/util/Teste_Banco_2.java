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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

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
       // Scanner ler = new Scanner(System.in);
        
        Veiculo vei = dao.consultarVeiculo(48);
        
        List<Posicao> a = daopos.consultarPosicoesCarro(48);
        System.out.println("TESTE: "+a.size());
        
        //Veiculo a = dao.consultarVeiculo(19);
        //dao.incluir(a);
       
        
       // List<Posicao> d = daopos.consultarPosicao(19, cal, cal2);
       // System.out.println(d.size());
        //System.out.println("INT: "+d.getCodigo());
        //System.out.println("TAMANHO: " + a.getPosicoes().size());*/
       
    }
    
}
