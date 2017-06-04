/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;


import br.com.servidor.UDPService;
import br.com.servidor.UDPServiceAux;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Rubens Rangel da Silva
 */
public class MainUDPService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        
        int intervaloexecucaothread = 20;//Integer.parseInt(args[0]);
        long intervalomudastatus = 0; //Long.parseLong(args[1]);
        
        UDPServiceAux servAuxUDP = new UDPServiceAux(intervaloexecucaothread,intervalomudastatus);
        servAuxUDP.start();
        
        UDPService servUDP = new UDPService();
        servUDP.start();

    }
    
}
