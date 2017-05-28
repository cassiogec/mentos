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
        
        UDPService servUDP = new UDPService();
        servUDP.start();
        
        
//        Integer i = Integer.getInteger(args[0]);
//        UDPServiceAux servAuxUDP = new UDPServiceAux(i);
//        servAuxUDP.start();
    }
    
}
