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
        
        Veiculo a = dao.consultarVeiculo(19);
        //dao.incluir(a);
        Calendar data = new GregorianCalendar();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
        try {
                data.setTime(sd.parse("2017/05/2017"));
        } catch (ParseException e) {
                e.printStackTrace();
        }
        Posicao b = new Posicao(a, Calendar.getInstance(), new Float(1), new Float(1));
        daopos.incluir(b);
        
        Calendar cal = Calendar.getInstance();
        DateFormat f = DateFormat.getDateInstance();
        Date data2 = f.parse("12/01/1995");
        cal.setTime(data2);
        
        Calendar cal2 = Calendar.getInstance();
        DateFormat f2 = DateFormat.getDateInstance();
        Date data3 = f2.parse("27/05/2017");
        cal2.setTime(data3);
        
        List<Posicao> d = daopos.consultarPosicao(19, cal, cal2);
        System.out.println(d.size());
        //System.out.println("INT: "+d.getCodigo());
        //System.out.println("TAMANHO: " + a.getPosicoes().size());
    }
    
}
