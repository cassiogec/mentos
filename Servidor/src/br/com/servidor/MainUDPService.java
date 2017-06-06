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
public class MainUDPService {

    /**
     * @param args the command line arguments
     */
<<<<<<< HEAD
    public static void main(String[] args) throws Exception {
        int intervaloexecucaothread = 6;//Integer.parseInt(args[0]);
        long intervalomudastatus = 10; //Long.parseLong(args[1]);

        UDPServiceAux servAuxUDP = new UDPServiceAux(intervaloexecucaothread, intervalomudastatus);
=======
    public static void main(String[] args) throws Exception{
        int  atualizacao = 10; //Integer.parseInt(args[0]);
        long ciclos      = 3;  //Long.parseLong(args[1]);
        
        UDPServiceAux servAuxUDP = new UDPServiceAux(atualizacao, ciclos);
>>>>>>> 3afc8aaafa5902a63e2b69d8cc44647a66279de6
        servAuxUDP.start();

        UDPService servUDP = new UDPService();
        servUDP.start();
    }
}
