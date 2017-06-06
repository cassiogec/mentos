/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;

/**
 *
 * @author Rubens Rangel da Silva
 */
public class MainUDPService
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        int  atualizacao = 10; //Integer.parseInt(args[0]);
        long ciclos      = 3;  //Long.parseLong(args[1]);
        
        UDPServiceAux servAuxUDP = new UDPServiceAux(atualizacao, ciclos);
        servAuxUDP.start();
        
        UDPService servUDP = new UDPService();
        servUDP.start();
    }
}
