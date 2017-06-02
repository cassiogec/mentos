/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;


import br.com.servidor.UDPService;
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
        
        int intervaloexecucaothread =30; //Integer.parseInt(args[0]);
        long intervalomudastatus = 5000;//Integer.parseInt(args[1]);
        
        UDPService servUDP = new UDPService();
        servUDP.start();

        UDPServiceAux servAuxUDP = new UDPServiceAux(intervaloexecucaothread,intervalomudastatus);
        servAuxUDP.start();
    }
    
}
